package com.szl.train.exercise;

import com.szl.train.model.DLinkNode;

import java.util.HashMap;

/**
 * Author: Stan sai
 * Date: 2024/2/9 15:18
 * description:
 * 实现LRU: 哈希表和双向链表
 */
public class T14LRUCache {
    private final HashMap<Integer, DLinkNode> CACHE;
    private DLinkNode head;
    private DLinkNode tail;
    private final int CAPACITY;//定义为，等于0，则永远为空，小于0可以无限大

    public T14LRUCache(int capacity) {
        this.CACHE = new HashMap<>(capacity);
        this.CAPACITY = capacity;
    }

    public int get(int key) {
        DLinkNode node = CACHE.get(key);
        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(CAPACITY == 0) {
            return;
        }
        if(CACHE.containsKey(key)) {
            DLinkNode cur = CACHE.get(key);
            cur.val = value;
            moveToHead(cur);
            return;
        }
        DLinkNode node = new DLinkNode(key, value);
        CACHE.put(key, node);
        addFirst(node);
        if(CAPACITY < CACHE.size()) {
            removeLast();
        }
    }

    private void moveToHead(DLinkNode cur) {
        if(cur == head) {
            return;
        }
        removeFromLinkedList(cur);
        addFirst(cur);
    }

    private void removeFromLinkedList(DLinkNode cur) {
        if(cur.pre != null) {
            cur.pre.next = cur.next;
        }
        if(cur.next != null) {
            cur.next.pre = cur.pre;
        }
        if(cur == head) {
            head = cur.next;
        }
        if(cur == tail) {
            tail = cur.pre;
        }
        cur.next = null;
        cur.pre = null;
    }

    private void addFirst(DLinkNode cur) {
        if(head == null) {
            head = cur;
            tail = cur;
            return;
        }
        head.pre = cur;
        cur.next = head;
        head = cur;
    }

    private void removeLast() {
        if(tail == null) {
            return;
        }
        if(head == tail) {
            CACHE.remove(tail.key);
            head = null;
            tail = null;
            return;
        }
        CACHE.remove(tail.key);
        DLinkNode temp = tail.pre;
        temp.next = null;
        tail.pre = null;
        tail = temp;
    }
}
