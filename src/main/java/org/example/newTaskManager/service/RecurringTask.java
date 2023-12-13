package org.example.newTaskManager.service;

public class RecurringTask extends ScheduledTask {
    private final long executionTime;
    private final long interval;
    public RecurringTask(ExecutionContext context, long executionTime, long interval) {
        super(context);
        this.executionTime = executionTime;
        this.interval = interval;
    }
    @Override
    public long getNextExecutionTime() {
        return executionTime;
    }
    @Override
    public boolean isRecurring() {
        return true;
    }
    @Override
    public ScheduledTask nextScheduledTask() {
        return new RecurringTask(context, executionTime + interval, interval);
    }
}
