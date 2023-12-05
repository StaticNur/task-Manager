package org.example.newTaskManager;

public abstract class ScheduledTask {
    public final ExecutionContext context;
    public ScheduledTask (ExecutionContext context) {
        this.context = context;
    }
    abstract boolean isRecurring();
    void execute() {
        context.execute();
    }
    abstract ScheduledTask nextScheduledTask();
    abstract long getNextExecutionTime();
}
