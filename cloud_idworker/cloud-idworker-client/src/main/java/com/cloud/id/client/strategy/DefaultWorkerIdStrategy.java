package com.cloud.id.client.strategy;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.cloud.id.client.feign.IdWorkerFeign;
import com.cloud.id.client.utils.Ip;
import com.cloud.id.client.utils.Utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;


@Slf4j
public class DefaultWorkerIdStrategy implements WorkerIdStrategy {

    private IdWorkerFeign idWorkerFeign;
    static long workerIdBits = 10L;
    static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    static Random random = new SecureRandom();
    String userName = System.getProperty("user.name");
    String ipDotUsername = Ip.ip + "." + userName;
    String ipudotlock = ipDotUsername + ".lock.";
    int workerIdIndex = ipudotlock.length();
    long workerId;
    FileLock fileLock;

    public DefaultWorkerIdStrategy(IdWorkerFeign idWorkerFeign) {
        this.idWorkerFeign=idWorkerFeign;
        workerId = findAvailWorkerId();
        if (workerId >= 0) {
            destroyFileLockWhenShutdown();
            startSyncThread();
        } else {
            syncWithWorkerIdServer();
            workerId = findAvailWorkerId();
            if (workerId < 0) workerId = increaseWithWorkerIdServer();
        }
        if (workerId < 0) workerId = tryToCreateOnIp();
        if (workerId < 0) {
            log.warn("DANGEROUS!!! Try to use random worker id.");
            workerId = tryToRandomOnIp(); // Try avoiding! it could cause duplicated
        }

        if (workerId < 0) {
            log.warn("the world may be ended!");
            throw new RuntimeException("the world may be ended");
        }
    }

    private void destroyFileLockWhenShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                fileLock.destroy();
            }
        });
    }

    private void startSyncThread() {
        new Thread() {
            @Override
            public void run() {
                syncWithWorkerIdServer();
            }
        }.start();
    }

    private long increaseWithWorkerIdServer() {
        ObjectRestResponse<String> res=idWorkerFeign.inc(ipDotUsername);
        if(!res.isSuccess()){
            return  -1L;
        }
        long lid = Long.parseLong(res.getData());
        return checkAvail(lid);
    }

    private long tryToCreateOnIp() {
        long wid = Ip.lip & maxWorkerId;

        return checkAvail(wid);
    }

    private long tryToRandomOnIp() {
        long avaiWorkerId = -1L;
        long tryTimes = -1;

        while (avaiWorkerId < 0 && ++tryTimes < maxWorkerId) {
            long wid = Ip.lip & random.nextInt((int) maxWorkerId);

            avaiWorkerId = checkAvail(wid);
        }
        return avaiWorkerId;
    }

    private long checkAvail(long wid) {
        long availWorkerId = -1L;
        try {
            File idWorkerHome = Utils.createIdWorkerHome();
            new File(idWorkerHome, ipudotlock + String.format("%04d", wid)).createNewFile();
            availWorkerId = findAvailWorkerId();
        } catch (IOException e) {
            log.warn("checkAvail error", e);
        }

        return availWorkerId;
    }

    private void syncWithWorkerIdServer() {
        ObjectRestResponse<String> rest=idWorkerFeign.sync(ipDotUsername,buildWorkerIdsOfCurrentIp());
        if(rest.isSuccess()){
            return;
        }
        String[] syncIdsArr = rest.getData().split(",");
        File idWorkerHome = Utils.createIdWorkerHome();
        for (String syncId : syncIdsArr) {
            try {
                new File(idWorkerHome, ipudotlock + syncId).createNewFile();
            } catch (IOException e) {
                log.warn("create workerid lock file error", e);
            }
        }

    }

    private String buildWorkerIdsOfCurrentIp() {
        StringBuilder sb = new StringBuilder();
        File idWorkerHome = Utils.createIdWorkerHome();
        for (File lockFile : idWorkerHome.listFiles()) {
            // check the format like 10.142.1.151.lock.0001
            if (!lockFile.getName().startsWith(ipudotlock)) continue;

            String workerId = lockFile.getName().substring(workerIdIndex);
            if (!workerId.matches("\\d\\d\\d\\d")) continue;

            if (sb.length() > 0) sb.append(',');
            sb.append(workerId);
        }

        return sb.toString();
    }


    /**
     * Find the local available worker id.
     *
     * @return -1 when N/A
     */
    private long findAvailWorkerId() {
        File idWorkerHome = Utils.createIdWorkerHome();

        for (File lockFile : idWorkerHome.listFiles()) {
            // check the format like 10.142.1.151.lock.0001
            if (!lockFile.getName().startsWith(ipudotlock)) continue;

            String workerId = lockFile.getName().substring(workerIdIndex);
            if (!workerId.matches("\\d\\d\\d\\d")) continue;

            FileLock fileLock = new FileLock(lockFile);
            if (!fileLock.tryLock()) {
                fileLock.destroy();
                continue;
            }

            this.fileLock = fileLock;
            return Long.parseLong(workerId);
        }

        return -1;
    }



    @Override
    public long availableWorkerId() {
        return workerId;
    }


}
