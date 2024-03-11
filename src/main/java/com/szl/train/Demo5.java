package com.szl.train;

import java.util.Stack;

/**
 * Author: Stan Sai
 * Date: 2024/3/1 16:47
 * description:
 */


public class Demo5 {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        head = reverseList(head.next);
        temp.next.next = temp;
        temp.next = null;
        return head;
    }

    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while(head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode temp = stack.pop();
        head = temp;
        while (!stack.isEmpty()) {
            ListNode next = stack.pop();
            temp.next = next;
            next.next = null;
            temp = next;
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
