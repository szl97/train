package com.szl.train.model;

/**
 * Author: Stan sai
 * Date: 2024/2/9 15:59
 * description:
 */
public class DLinkNode {
    public int key;
    public int val;
    public DLinkNode pre;
    public DLinkNode next;

    public DLinkNode(int key, int val, DLinkNode pre, DLinkNode next) {
        this.key = key;
        this.val = val;
        this.pre = pre;
        this.next = next;
    }

    public DLinkNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
