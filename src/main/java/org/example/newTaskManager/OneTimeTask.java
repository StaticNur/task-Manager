package org.example.newTaskManager;

public class OneTimeTask  extends ScheduledTask {
    private final long executionTime;
    public OneTimeTask(ExecutionContext context, long executionTime) { //  !!!
        super(context);
        this.executionTime = executionTime;
    }
    @Override
    public long getNextExecutionTime() {
        return executionTime;
    }
    @Override
    public boolean isRecurring() {
        return false;
    }
    @Override
    public ScheduledTask nextScheduledTask() {
        return null;
    }
}
