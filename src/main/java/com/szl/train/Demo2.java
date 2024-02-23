package com.szl.train;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Stan sai
 * Date: 2024/2/23 16:42
 * description:
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 *
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 *
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 *
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 */
public class Demo2 {
    public static int maxDifferenceLength(int[] nums, int k) {
        if(nums == null) {
            return 0;
        }
        if(nums.length < 2) {
            return nums.length;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 1;
        map.put(nums[0], 1);
        //f(n) = f(n-k)+1
        for(int i = 1; i < nums.length; i++) {
            int len = map.getOrDefault(nums[i]-k, 0) + 1;
            map.put(nums[i], len);
            result = Math.max(result, len);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,7,8,5,3,4,2,1};
        System.out.println(maxDifferenceLength(nums, -2));
    }
}
