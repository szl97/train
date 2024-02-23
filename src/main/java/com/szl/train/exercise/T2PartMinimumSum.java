package com.szl.train.exercise;

/**
 * Author: Stan sai
 * Date: 2024/2/8 15:52
 * description:
 * 二分法练习
 * 将数组划分为n部分，使每部分之和最小，找出这个最小值
 * [0...数组累加和]上使用二分法
 * 每个中点位置判断和不大于array[mid]时，n个部分是否够用
 * 测试地址：https://leetcode.cn/problems/split-array-largest-sum/
 */
public class T2PartMinimumSum {
    public static int getPartMinimumSum(int[] array, int n) {
        int sum = 0;
        for(int i : array) {
            sum += i;
        }
        int min = sum;
        int l = 0;
        int r = sum;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if(canDivide(array, mid, n)) {
                r = mid - 1;
                min = mid;
            } else {
                l = mid + 1;
            }
        }
        return min;
    }

    public static boolean canDivide(int[] array, int limit, int n) {
        int part = 0;
        int sum = 0;
        for(int i : array) {
            if(i > limit) {
                return false;
            }
            if(sum + i > limit) {
                sum = i;
                ++part;
            } else {
                sum = sum + i;
            }
        }
        return part <= n;
    }

    public static void main(String[] args) {
       System.out.printf("%d", getPartMinimumSum( new int[] {2,3,1,2,4,3}, 5));
    }
}
