package com.szl.train.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Stan sai
 * Date: 2024/2/18 18:34
 * description:滑动窗口问题
 * 1、给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 2、给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
 * https://leetcode.cn/problems/minimum-window-substring
 */
public class T17SlideWindow {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        Map<Character, Integer> times = new HashMap<>();
        char[] chars = s.toCharArray();
        int max = 0;
        int L = 0;
        int R = 1;
        times.put(chars[L], 1);
        while (L <= R && R < chars.length) {
            times.put(chars[R], times.getOrDefault(chars[R], 0) + 1);
            while (times.get(chars[R]) > 1) {
                times.put(chars[L], times.get(chars[L]) - 1);
                ++L;
            }
            int len = R - L + 1;
            max = Math.max(len, max);
            ++R;
        }
        return max;
    }

    public static String minWindow(String s, String t) {
        if(t == null || t.isBlank() || s == null || s.isBlank()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        int total = 0;
        for(char c : t.toCharArray()) {
            set.add(c);
            map.put(c, map.getOrDefault(c, 0) + 1);
            total++;
        }
        int L = 0;
        int R = 0;
        char[] chars = s.toCharArray();
        int min = Integer.MAX_VALUE;
        int resultL = -1;
        int resultR = -1;
        while (R <= chars.length) {
            if(total == 0) {
                int len = R-L+1;
                if(len < min) {
                    min = len;
                    resultL = L;
                    resultR = R;
                }
                map.put(chars[L], map.getOrDefault(chars[L], 0) + 1);
                if (set.contains(chars[L]) && map.get(chars[L]) > 0) {
                    total++;
                }
                ++L;
            } else {
                if(R != chars.length) {
                    map.put(chars[R], map.getOrDefault(chars[R], 0) - 1);
                    if (set.contains(chars[R]) && map.get(chars[R]) >= 0) {
                        total--;
                    }
                }
                ++R;
            }
        }
        if(resultL == -1) {
            return "";
        }
        return s.substring(resultL, resultR);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        System.out.println(minWindow(s, "ABC"));
    }
}
