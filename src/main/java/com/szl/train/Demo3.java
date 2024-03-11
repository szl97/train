package com.szl.train;

/**
 * Author: Stan Sai
 * Date: 2024/2/27 16:55
 * description:
 * 	题目：一个整数序列（没有0），求“序列和”最大的连续子序列。
 * 	其中，“序列和”表示：序列里的所有值的和。
 * 	连续子序列表示：子序列的值在原序列中是连续的一段。
 * 	输入：一个序列。
 * 输出：子序列的开始位置和结束位置。
 */
public class Demo3 {
    //累加和的差值最大
    //f(i,j) + a[j+1]= f(i, j+1)
    //f(i,j) - a[i] = f(i+1, j);
    public int[] maxSumStartAndEnd(int[] nums) {
        if(nums == null || nums.length < 2) {
            return new int[]{0, 0};
        }
        int[] result = new int[2];
        int[] ends = new int[nums.length];
        ends[0] = nums[0];
        for(int i = 1; i < ends.length; i++) {
            ends[i] = ends[i-1]+nums[i];
        }
        int sum = Integer.MIN_VALUE;
        for(int i = 0; i < ends.length; i++) {
            for(int j = i; j < ends.length; j++) {
                int temp;
                if(i == j) {
                    temp = nums[i];
                } else {
                    temp = ends[j] - ends[i];
                }
                if(temp > sum) {
                    sum = temp;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
