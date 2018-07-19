package com.pyy.current.synchoriedContainer;

import com.pyy.current.annontation.ThreadNotSafe;

import java.util.Vector;

@ThreadNotSafe
public class VectorSimple {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        while(true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    vector.remove(i);
                }
            }).start();
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    vector.get(i);
                }
            }).start();
        }
    }
}
