package com.pyy.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureSimple {
    static class MyTask implements Callable<String>{
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(6000);
            return "game over!";
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyTask());
        log.info("do something in main");
        Thread.sleep(3000);
        String result = future.get();
        log.info("main over!:{}",result);
    }
}
