package com.cloud.id.client;
import com.cloud.id.client.strategy.WorkerIdStrategy;
import com.cloud.id.client.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SidUtils {
    private WorkerIdStrategy workerIdStrategy;
    private  IdWorker idWorker;

    public SidUtils(WorkerIdStrategy workerIdStrategy) {
        this.workerIdStrategy = workerIdStrategy;
        idWorker = new IdWorker(workerIdStrategy.availableWorkerId()) {
            @Override
            public long getEpoch() {
                return Utils.midnightMillis();
            }
        };
    }



    /**
     * 一天最大毫秒86400000，最大占用27比特
     * 27+10+11=48位 最大值281474976710655(15字)，YK0XXHZ827(10字)
     * 6位(YYMMDD)+15位，共21位
     *
     * @return 固定21位数字字符串
     */

    public  String next() {
        long id = idWorker.nextId();
        String yyMMdd = new SimpleDateFormat("yyMMdd").format(new Date());
        return yyMMdd + String.format("%014d", id);
    }


    /**
     * 返回固定16位的字母数字混编的字符串。
     */
    public  String nextShort() {
        long id = idWorker.nextId();
        String yyMMdd = new SimpleDateFormat("yyMMdd").format(new Date());
        return yyMMdd + Utils.padLeft(Utils.encode(id), 10, '0');
    }
}
