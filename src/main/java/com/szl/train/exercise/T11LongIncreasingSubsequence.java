package com.szl.train.exercise;

import java.util.Arrays;

/**
 * Author: Stan sai
 * Date: 2024/2/9 01:08
 * description:最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 进阶：俄罗斯套娃信封
 * 长度排序，长度相同时，高度高的放前面，按照寻找高度寻找最长递增子序列
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 * https://leetcode.cn/problems/russian-doll-envelopes/
 * 可以优化为O(N*logN)
 */
public class T11LongIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            result = Math.max(dp[i], result);
        }
        return result;
    }

    public static int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return 1;
        }
        int[] end = new int[nums.length + 1];
        int result = 1;
        end[1] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(end[result] < nums[i]) {
                end[++result] = nums[i];
            } else {
                int fl = findFirstLargePosition(end, nums[i], result);
                end[fl] = nums[i];
            }
        }
        return result;
    }

    public static int findFirstLargePosition(int[] end, int target, int right) {
        if(end == null || end.length == 0) {
            return -1;
        }
        int L = 1;
        int R = right;
        int p = -1;
        while (L<=R) {
            int mid = L + ((R-L)>>1);
            if(end[mid] == target) {
                return mid;
            } else if(end[mid] > target) {
                p = mid;
                R = mid-1;
            } else {
                L = mid+1;
            }
        }
        return p;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0) {
            return 0;
        }
        if(envelopes.length == 1) {
            return 1;
        }
        Arrays.sort(envelopes, (a, b)->a[0] == b[0] ? b[1]-a[1] : a[0]-b[0]);
        int[] heights = new int[envelopes.length];
        for(int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }
        return lengthOfLIS2(heights);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int s0 = lengthOfLIS(nums);
        int s = lengthOfLIS2(nums);
        System.out.println(""+s0);
        System.out.println(""+s);
    }
}
