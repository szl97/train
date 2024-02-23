package com.szl.train.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Stan sai
 * Date: 2024/2/18 19:42
 * description:
 * 动态规划
 * 1、最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。如果字符串的反序与原始字符串相同，则该字符串称为回文字符串
 * https://leetcode.cn/problems/longest-palindromic-substring
 * 2、两个数组同位置可以相互交换，严格递增的最小交换次数
 * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing
 * 3、零钱兑换
 * https://leetcode.cn/problems/coin-change
 * 4、戳气球
 * https://leetcode.cn/problems/burst-balloons
 */
public class T18DynamicDesign {
    public static String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }
        int[][] dp = new int[s.length()][s.length()];
        char[] chars = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
            if(i != s.length() - 1) {
                if(chars[i] == chars[i+1]) {
                    dp[i][i+1] = 2;
                } else {
                    dp[i][i+1] = 1;
                }
            }
        }
        for(int i = s.length() - 3; i >=0; i--) {
            for(int j = i + 2; j < s.length(); j++) {
                int p1 = dp[i+1][j];
                int p2 = dp[i][j-1];
                int p3 = chars[i] == chars[j] && dp[i+1][j-1] == j-i-1 ? dp[i+1][j-1] + 2 : dp[i+1][j-1];
                int p = Math.max(p1, Math.max(p2, p3));
                dp[i][j] = p;
            }
        }
        int max = dp[0][s.length() - 1];
        if(max == 0) {
            return s.substring(0,1);
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if(dp[l+1][r-1] == max) {
                ++l;
                --r;
            } else if(dp[l+1][r] == max) {
                ++l;
            } else if(dp[l][r-1] == max) {
                --r;
            } else {
                break;
            }
        }
        return s.substring(l, r+1);
    }
    public static int minSwap(int[] nums1, int[] nums2) {
        if(nums1 == null) {
            return -1;
        }
        if(nums1.length == 1) {
            return 0;
        }
        int[][] dp = new int[nums1.length][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for(int i = 1; i < nums1.length; i++) {
            if(nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1] && nums2[i] > nums1[i-1] && nums1[i] > nums2[i-1]) {
                dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = dp[i][0]+1;
            } else if(nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]) {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1] + 1;
            } else if(nums2[i] > nums1[i-1] && nums1[i] > nums2[i-1]) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i-1][0] + 1;
            } else{
                return -1;
            }
        }
        return Math.min(dp[nums1.length-1][0], dp[nums1.length-1][1]);
    }

    public static int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0;
        while (sum <= amount) {
            int p = Integer.MAX_VALUE;
            for(int num : coins) {
                if(sum >= num && map.containsKey(sum-num)) {
                    p = Math.min(p, map.get(sum-num) + 1);
                    map.put(sum, p);
                }
            }
            sum++;
        }
        return map.getOrDefault(amount, -1);
    }

    //比较复杂，先写出递归
    public static int maxCoins(int[] nums) {
        int[] help = new int[nums.length+2];
        help[0] = 1;
        help[nums.length+1] = 1;
        for(int i = 0; i < nums.length; i++) {
            help[i + 1] = nums[i];
        }
        return hit(help, 1, nums.length);
    }

    //先打爆[l...r]，确保每次调用时l-1和r+1都没打爆
    public static int hit(int[] help, int l, int r) {
        if(l == r) {
            return help[l-1] * help[l] * help[l+1];
        }
        int p1 = hit(help, l+1, r) + help[l-1] * help[l] * help[r+1];//先打爆[l+1...r]，最后打爆l;
        int p2 = hit(help, l, r-1) + help[l-1] * help[r] * help[r+1];//先打爆[l...r-1]，最后打爆r;
        int p = Math.max(p1, p2);
        for(int i = l+1; i < r; i++) {//先打爆[l...i-1]和[i+1...r]，i位置最后打爆
            int p3 = hit(help, l, i-1) + hit(help, i+1, r) + help[l-1] * help[i] * help[r+1];
            p = Math.max(p, p3);
        }
        return p;
    }

    public static int maxCoins2(int[] nums) {
        int[] help = new int[nums.length+2];
        help[0] = 1;
        help[nums.length+1] = 1;
        for(int i = 0; i < nums.length; i++) {
            help[i + 1] = nums[i];
        }
        int[][] dp = new int[help.length][help.length];
        for(int i = 1; i <= nums.length; i++) {
            dp[i][i] = help[i-1] * help[i] * help[i+1];
        }
        for(int i = nums.length; i >= 1; i--) {
            for(int j = i + 1; j < nums.length + 1; j++) {
                int p1 = help[i-1]*help[i]*help[j+1] + dp[i+1][j];
                int p2 = help[i-1]*help[j]*help[j+1] + dp[i][j-1];
                int p = Math.max(p1, p2);
                for(int k = i+1; k < j; k++) {//选择中间位置最后打爆
                    int p3 = dp[i][k-1] + dp[k+1][j] + help[i-1] * help[k] * help[j+1];
                    p = Math.max(p, p3);
                }
                dp[i][j] = p;
            }
        }
        return dp[1][nums.length];
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {0,3,5,8,9};
        int[] nums2 = new int[] {2,1,4,6,9};
        minSwap(nums1, nums2);

        String s = "aacabdkacaa";
        System.out.println(longestPalindrome(s));

        int[] nums = new int[] {2,5,10,1};
        coinChange(nums, 27);

        int[] coins = new int[] {9,76,64};
        System.out.println(maxCoins2(coins));
    }
}
