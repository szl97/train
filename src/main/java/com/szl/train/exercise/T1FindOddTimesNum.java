package com.szl.train.exercise;

import java.util.Random;

/**
 * Author: Stan sai
 * Date: 2024/2/8 15:20
 * description:
 * 异或运算：
 * 数组中有两个出现奇数次的数字。找出它们。
 * 1、a^a = 0
 *    a^(a^b) = b
 * 2、找到最右是1的位：
 *    rightOne = a & (-a)
 *
 */
public class T1FindOddTimesNum {
    public static int[] findTwoOddTimesNum(int[] array) {
        int eor = 0;
        for(int i : array) {
            eor ^= i;
        }
        int rightOne = eor & (-eor);
        int a = 0;
        for(int i : array) {
            if((i & rightOne) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        return new int[] {a ,b};
    }

    public static void main(String[] args) {
        int[] array = new int[198];
        Random random = new Random();
        int random1 = random.nextInt(100);
        int random2 = random.nextInt(100);
        while (random2 == random1) {
            random2 = random.nextInt(100);
        }
        int p = 0;
        for(int i = 0; i < 100; i++) {
            array[p++] = i;
            if(i != random1 && i != random2) {
                array[p++] = i;
            }
        }
        System.out.printf("两个随机数是：%d 和 %d%n", random1, random2);
        int[] result = findTwoOddTimesNum(array);
        System.out.printf("找到的两个数是：%d 和 %d%n", result[0], result[1]);
    }
}
