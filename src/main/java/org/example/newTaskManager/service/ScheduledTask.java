package org.example.newTaskManager.service;

import org.example.newTaskManager.objects.Entity;

public abstract class ScheduledTask {
    public final ExecutionContext context;
    public ScheduledTask (ExecutionContext context) {
        this.context = context;
    }
    abstract boolean isRecurring();
    Entity execute() {
        return context.execute();
    }
    abstract ScheduledTask nextScheduledTask();
    public abstract long getNextExecutionTime();
}
