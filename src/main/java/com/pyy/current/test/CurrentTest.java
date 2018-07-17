package com.pyy.current.test;

import com.pyy.current.annontation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadNotSafe
public class CurrentTest {

    private static int threadTotal = 5000;
    private static int requestTotal = 200;
    private static int count = 0;


    private static void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
         final Semaphore semaphore = new Semaphore(requestTotal);
         final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
         ExecutorService executorService = Executors.newCachedThreadPool();
         for (int i = 0; i < threadTotal; i++) {
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:{}",e);
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
         }
         countDownLatch.await();
         executorService.shutdown();
         log.info("execute over:{}",count);
    }

}
