package com.szl.train;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Stan Sai
 * Date: 2024/2/27 18:06
 * description:
 */
public class Demo4 {

    public static void multiThreadPrint() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        AtomicInteger seq = new AtomicInteger(0);
        Thread t1 = new Thread(()-> print(50, "a", seq, 0, 1, latch));
        Thread t2 = new Thread(()-> print(50, "l", seq, 2, 3, latch));
        Thread t3 = new Thread(()-> print(50, "i", seq, 4, -1, latch));
        t1.start();
        t2.start();
        t3.start();
        latch.await();
    }

    private static void print(int times, String c, AtomicInteger seq, int pre, int cur, CountDownLatch latch) {
        while (times-- > 0) {
            while (true) {
                if (seq.compareAndSet(pre, cur)) {
                    System.out.printf(c);
                    seq.compareAndSet(cur, cur+1);
                    break;
                }
            }
        }
        System.out.println();
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        multiThreadPrint();
    }
}
