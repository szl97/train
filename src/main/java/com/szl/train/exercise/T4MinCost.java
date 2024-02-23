package com.szl.train.exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Stan sai
 * Date: 2024/2/8 18:44
 * description:
 * 雇佣n名工人的最低成本
 * 有 n 名工人。 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
 * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/description/
 * 算出 单位能力的价格， 排序， 整体的单位能力以大的为准
 * 遍历单位能力，选出此时的n个最小能力员工，即可计算出此单位能力下的最低成本
 */
public class T4MinCost {
    public static double minCostToHireWorkers(int[] quality, int[] wage, int k) {
        List<Worker> list = new ArrayList<>();
        for(int i = 0; i < quality.length; i++) {
            list.add(new Worker(wage[i], quality[i]));
        }
        list.sort(Comparator.comparingDouble(worker -> worker.wpq));
        PriorityQueue<Worker> heap = new PriorityQueue<>(k, (a, b)->b.quality-a.quality);
        double ans = -1;
        for(Worker worker : list) {
            if(heap.size() < k) {
                heap.add(worker);
            } else {
                if (heap.peek().quality > worker.quality) {
                    heap.poll();
                    heap.add(worker);
                }
            }
            if(heap.size() == k){
                int sum = 0;
                for(Worker w : heap) {
                    sum += w.quality;
                }
                double wages = worker.wpq * sum;
                ans = ans < 0 ? wages : Math.min(ans, wages);
            }
        }
        return ans;
    }

   static class Worker {
        int wage;
        int quality;
        double wpq;
        public Worker(int wage, int quality) {
            this.wage = wage;
            this.quality = quality;
            this.wpq = (wage * 1.0) / (quality * 1.0);
        }
   }

   public static void main(String[] args) {
        int[] quality = new int[]{10,20,5};
        int[] wage = new int[]{70,50,30};
        int k = 2;
        System.out.println(""+minCostToHireWorkers(quality, wage, k));
   }
}
