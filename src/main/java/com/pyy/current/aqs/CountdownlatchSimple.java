package com.pyy.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountdownlatchSimple {
    private static int threadTotal = 20;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= threadTotal; i++) {
            final int count = i;
            executorService.execute(() ->{
                test(count);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();
    }
    public static void test(int i){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}",i);
    }
}
