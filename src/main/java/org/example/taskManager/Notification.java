package org.example.taskManager;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Notification implements Runnable{
    private List<Entity> entityList;
    private Lock lock;

    public Notification(List<Entity> entityList, Lock lock) {
        this.entityList = entityList;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
            for (Entity entity: entityList){
                if(!entity.getFinished()){
                    lock.lock();
                    checkTimeAndPrint(entity);
                    lock.unlock();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void checkTimeAndPrint(Entity entity){
        Date dateNow = Date.from(Instant.now());
        long difference = entity.getDate().getTime() - dateNow.getTime();
        int hour =  (int)(difference / (60 * 60 * 1000)); // миллисекунды / (60мин * 60сек * 1000мс)
        //System.out.println("hour = " + hour);
        if(hour == 0){
            System.out.println("скоро нужно выполнить: " + entity.getTask());
        }
    }
}
