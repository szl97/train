package com.szl.train.exercise;

import com.szl.train.model.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Stan sai
 * Date: 2024/2/8 21:37
 * description: 合并k个升序链表
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 */
public class T7MergeLinkList {
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, Comparator.comparingInt(n->n.val));
        for(ListNode l : lists) {
            if(l != null) {
                heap.add(l);
            }
        }
        ListNode head = null;
        ListNode temp = null;
        while (!heap.isEmpty()) {
           ListNode cur = heap.poll();
           if(head == null) {
               head = cur;
           }
           if(temp != null) {
               temp.next = cur;
           }
           temp = cur;
           if(cur.next != null) {
               heap.add(cur.next);
           }
        }
        return head;
    }
    public static void main(String[] args) {

    }
}
