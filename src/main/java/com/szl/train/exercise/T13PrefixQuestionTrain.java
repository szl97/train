package com.szl.train.exercise;

import java.util.HashMap;

/**
 * Author: Stan sai
 * Date: 2024/2/9 03:54
 * description:
 * 前缀问题：
 * 1、和为k的最长连续子数组
 * 无序，要求和为k，求最大长度
 * leetcode 325题，需付费
 * 2、给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * https://leetcode.cn/problems/subarray-sum-equals-k
 * 3、表现良好的时间段
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * 请你返回「表现良好时间段」的最大长度。
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 * https://leetcode.cn/problems/longest-well-performing-interval
 */
public class T13PrefixQuestionTrain {
    public static int maxSubArrayLen(int[] array, int k) {
        if(array == null || array.length == 0) {
            return 0;
        }
        int sum = 0;
        /*
            key代表0...i位置的累加和, value即index
        */
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//一开始累加和就是0,从start+1...i和为k
        int len = 0;
        for(int i = 0; i < array.length; i++) {
            sum+=array[i];
            if(map.containsKey(sum-k)) {
                int start = map.get(sum-k);//到start位置累加和为sum-k，则从start+1...i和为k
                len = Math.max(len, i - start);
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public int subarraySum(int[] array, int k) {
        if(array == null || array.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for(int i = 0; i < array.length; i++) {
            sum+=array[i];
            if(map.containsKey(sum-k)) {
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public int longestWPI(int[] hours) {
        if(hours == null || hours.length == 0) {
            return 0;
        }
        int[] temp = new int[hours.length];
        for(int i = 0; i < hours.length; i++) {
            temp[i] = hours[i] > 8 ? 1 : -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int ans = 0;
        for(int i = 0; i < temp.length; i++) {
            sum += temp[i];
            if(sum > 0) {
                ans = Math.max(ans, i - map.get(0));
            } else {
                if(map.containsKey(sum-1)) {
                    ans = Math.max(ans, i - map.get(sum-1));
                }
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
