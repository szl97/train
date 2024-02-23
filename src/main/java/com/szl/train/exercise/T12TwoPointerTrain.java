package com.szl.train.exercise;

import java.util.Arrays;

/**
 * Author: Stan sai
 * Date: 2024/2/9 02:48
 * description:
 * 双指针练习：
 * 1、最小救生艇数量
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit.
 * 返回 承载所有人所需的最小船数
 * 2、接雨水
 * https://leetcode.cn/problems/trapping-rain-water
 *
 */
public class T12TwoPointerTrain {
    public static int numRescueBoats(int[] people, int limit) {
        if(people == null || people.length == 0) {
            return 0;
        }
        if(people.length == 1) {
            return people[0] <= limit ? 1 : -1;
        }
        Arrays.sort(people);
        int L = 0;
        int R = people.length - 1;
        int sum = 0;
        while (L <= R) {
            if(people[L] > limit) {
                return -1;
            }
            int rest = limit;
            if(rest >= people[R]) {
                rest -= people[R--];
            }
            if(R != L && rest >= people[L]) {
                L++;
            }
            sum++;
        }
        return sum;
    }

    public static int trap(int[] height) {
        if(height == null || height.length < 3) {
            return 0;
        }
        int len = height.length;
        int[] L = new int[len];
        int[] R = new int[len];
        L[0] = height[0];
        for(int i = 1; i < len; i++) {
            L[i] = Math.max(L[i-1], height[i]);
        }
        R[len-1] = height[len-1];
        for(int i = len-2; i>=0; i--) {
            R[i] = Math.max(R[i+1], height[i]);
        }
        int sum = 0;
        for(int i = 0; i < len; i++) {
            int minEdge = Math.min(L[i], R[i]);
            if(minEdge > height[i]) {
                sum += (minEdge-height[i]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(""+trap(nums));
    }

}
