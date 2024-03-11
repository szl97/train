package com.szl.train.exercise;

import java.util.Random;

/**
 * Author: Stan sai
 * Date: 2024/2/8 15:20
 * description:
 * 异或运算：
 * 1）一组数中一个出现一次，其余出现3次，找到出现一次的。
 * 2）数组中有一个出现奇数次的数字。找出它。
 * 3）数组中有两个出现奇数次的数字。找出它们。
 * 1、a^a = 0
 *    a^(a^b) = b
 * 2、找到最右是1的位：
 *    rightOne = a & (-a)
 *
 */
public class T1BitOperation {

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt1 = 0;
            for (int x : nums) {
                cnt1 += x >> i & 1;
            }
            ans |= cnt1 % 3 << i;
        }
        return ans;
    }

    public int singleNumber2(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            int tmpA = a;
            a = (a ^ x) & (a | b);
            b = (b ^ x) & ~tmpA;
        }
        return b;
    }


    public int singleNumber3(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            b = (b ^ x) & ~a;
            a = (a ^ x) & ~b;
        }
        return b;
    }


    public static int findOneOddTimesNum(int[] array) {
        int ans  = 0;
        for(int i : array) {
            ans ^= i;
        }
        return ans;
    }
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


    public static boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
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
