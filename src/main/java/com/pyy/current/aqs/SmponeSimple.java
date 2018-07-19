package com.pyy.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SmponeSimple {
    private static int threadTotal = 20;

    public static void main(String[] args) throws Exception {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= threadTotal; i++) {
            final int count = i;
            executorService.execute(() ->{
                try {
                    if(semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)) {
                        test(count);
                        semaphore.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
    public static void test(int i){
        log.info("{}",i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
