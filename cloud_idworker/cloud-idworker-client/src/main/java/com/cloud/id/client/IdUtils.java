package com.cloud.id.client;
import com.cloud.id.client.strategy.WorkerIdStrategy;


public class IdUtils {
    private WorkerIdStrategy workerIdStrategy;
    private  IdWorker idWorker;

    public IdUtils(WorkerIdStrategy workerIdStrategy) {
        this.workerIdStrategy=workerIdStrategy;
        idWorker = new IdWorker(workerIdStrategy.availableWorkerId());
    }



    public  long next() {
        return idWorker.nextId();
    }

    public  long getWorkerId() {
        return idWorker.getWorkerId();
    }
}
