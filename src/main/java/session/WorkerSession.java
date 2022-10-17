package session;


import model.WorkerEntity;

public final class WorkerSession {
    private static WorkerSession instance;

    private WorkerEntity worker;

    public WorkerSession(WorkerEntity worker) {
        this.worker = worker;
    }

    public static WorkerSession getInstance(WorkerEntity worker) {
        if (instance == null) {
            instance = new WorkerSession(worker);
        }
        return instance;
    }

    public static WorkerSession getInstance() {
        return instance;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public void cleanWorkerSession() {
        worker = null;
    }


}