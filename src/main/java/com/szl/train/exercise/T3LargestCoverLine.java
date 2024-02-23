package com.szl.train.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Stan sai
 * Date: 2024/2/8 17:01
 * description:
 * 找出所有区间的最大重合部分的数量
 * 根据左边界排序
 * 遍历线段，比左边界小的出堆，右边界加入堆
 * 此时重叠数为堆中元素个数
 * params: [1,2]代表1-2的区间
 */
public class T3LargestCoverLine {
    public static int finLargestCover(int[][] lines) {
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int covers = 0;
        for(int[] line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            covers = Math.max(covers, heap.size());
        }
        return covers;
    }
}
