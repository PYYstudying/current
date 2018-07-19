package com.pyy.current.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
public class JodaTimeSimple {
    private static int threadTotal = 5000;
    private static int requestTotal = 200;
    //private static DateTimeFormatter dateTimeformatter = DateTimeFormat.forPattern("yyyyMMdd");
    private static java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy:MM:dd");


    private  static void add(int i){

        log.info("{},{}",i, LocalDateTime.now().format(dateTimeFormatter));
    }

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(requestTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadTotal; i++) {
            final int count = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add(count);
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

    }
}
