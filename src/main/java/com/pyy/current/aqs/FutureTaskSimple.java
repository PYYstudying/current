package com.pyy.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskSimple {
    public static void main(String[] args){
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>(){
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(6000);
                return "callable over!";
            }
        });
        new Thread(futureTask).start();
        String result = "call";
        try {
             result = futureTask.get(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.info("exception:",e);
        }
        log.info("main over!{}",result);
    }
}
