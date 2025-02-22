package com.cloud.id.client;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
@Slf4j
public class IdWorker {
    protected long epoch = 1387886498127L; // 2013-12-24 20:01:38.127

    protected long workerIdBits = 10L;
    protected long maxWorkerId = -1L ^ (-1L << workerIdBits);
    protected long sequenceBits = 11L;

    protected long workerIdShift = sequenceBits;
    protected long timestampLeftShift = sequenceBits + workerIdBits;
    protected long sequenceMask = -1L ^ (-1L << sequenceBits);

    protected long lastMillis = -1L;

    protected final long workerId;
    protected long sequence = 0L;


    public IdWorker(long workerId) {
        this.workerId = checkWorkerId(workerId);

        log.debug("worker starting. timestamp left shift {}, worker id bits {}, sequence bits {}, worker id {}",
                timestampLeftShift, workerIdBits, sequenceBits, workerId);
    }

    public long getEpoch() {
        return epoch;
    }

    private long checkWorkerId(long workerId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            int rand = new SecureRandom().nextInt((int) maxWorkerId + 1);
            log.warn("worker Id can't be greater than {} or less than 0, use a random {}", maxWorkerId, rand);
            return rand;
        }

        return workerId;
    }

    public synchronized long nextId() {
        long timestamp = millisGen();

        if (timestamp < lastMillis) {
            log.error("clock is moving backwards.  Rejecting requests until {}.", lastMillis);
            throw new InvalidSystemClock(String.format(
                    "Clock moved backwards.  Refusing to generate id for {} milliseconds", lastMillis - timestamp));
        }

        if (lastMillis == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0)
                timestamp = tilNextMillis(lastMillis);
        } else {
            sequence = 0;
        }

        lastMillis = timestamp;
        long diff = timestamp - getEpoch();
        return (diff << timestampLeftShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    protected long tilNextMillis(long lastMillis) {
        long millis = millisGen();
        while (millis <= lastMillis)
            millis = millisGen();

        return millis;
    }

    protected long millisGen() {
        return System.currentTimeMillis();
    }

    public long getLastMillis() {
        return lastMillis;
    }

    public long getWorkerId() {
        return workerId;
    }
}
