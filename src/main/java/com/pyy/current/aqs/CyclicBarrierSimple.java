package com.pyy.current.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSimple {
    private static class Soder implements Runnable{

        private CyclicBarrier cyclicBarrier;
        private String name;

        public Soder(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name+"集合完毕！");
                cyclicBarrier.await();
                System.out.println(name+"开始工作！");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static class Word implements Runnable{

        private boolean flag;

        public Word(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag){
                System.out.println("所有士兵完成任务！");
            }else {
                System.out.println("所有士兵集合完毕！");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,new Word(flag));
        for (int i = 0; i < 10; i++) {
            new Thread(new Soder(cyclicBarrier,"士兵"+i)).start();
        }
    }
}

