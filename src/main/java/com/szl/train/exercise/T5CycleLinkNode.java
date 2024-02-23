package com.szl.train.exercise;

import com.szl.train.model.ListNode;

/**
 * Author: Stan sai
 * Date: 2024/2/8 18:04
 * description:找到第一个入环的位置
 * 快慢指针：第一次相遇后，快指针从head出发
 * 相同速度，与慢指针相遇的点就是入环位置
 */
public class T5CycleLinkNode {
    public static ListNode findFirstCyclePosition(ListNode head){
        if(head == null || head.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
