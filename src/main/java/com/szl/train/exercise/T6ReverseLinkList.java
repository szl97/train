package com.szl.train.exercise;

import com.szl.train.model.ListNode;

import java.util.Random;

/**
 * Author: Stan sai
 * Date: 2024/2/8 20:07
 * description:反转链表、k个一组反转链表
 * https://leetcode.cn/problems/reverse-linked-list/
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/submissions/501343250/
 */
public class T6ReverseLinkList {
    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        head = reverse(head.next);
        temp.next.next = temp;
        temp.next = null;
        return head;
    }

    public static ListNode reverseByGroup(ListNode head, int k) {
        if(k <= 1) {
            return head;
        }
        ListNode target = getTarget(head, k);
        if(target == null) {
            return head;
        }
        ListNode temp = target.next;
        ListNode result = process(head, 1, k);
        head.next = reverseByGroup(temp, k);
        return result;
    }

    public static ListNode getTarget(ListNode h, int k) {
        ListNode target = h;
        int i = 0;
        while (++i < k && target != null) {
            target = target.next;
        }
        return target;
    }

    public static ListNode process(ListNode head, int times, int k) {
        if(times == k) {
            return head;
        }
        ListNode temp = head;
        head = process(head.next, ++times, k);
        temp.next.next = temp;
        temp.next = null;
        return head;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int len = random.nextInt(20);
        if(len == 0) {
            len++;
        }
        ListNode testData = getTestData(len);
        System.out.println("测试数据如下：");
        ListNode head = testData;
        while (testData != null) {
            System.out.printf("%5d",testData.val);
            testData = testData.next;
        }
        System.out.println();
        System.out.println("反转数据如下：");
        ListNode h = reverse(head);
        while (h != null) {
            System.out.printf("%5d",h.val);
            h = h.next;
        }
        System.out.println();
        int limit = Math.min(len, 7);
        int k = random.nextInt(limit);
        ListNode g = reverseByGroup(getTestData(len), k);
        System.out.println(k+"个一组反转数据如下：");
        while (g != null) {
            System.out.printf("%5d",g.val);
            g = g.next;
        }
        System.out.println();
    }

    public static ListNode getTestData(int len) {
        ListNode tail = new ListNode(len, null);
        ListNode head = tail;
        for(int i = len - 1; i > 0; i--) {
            ListNode listNode = new ListNode(i, head);
            head = listNode;
        }
        return head;
    }
}
