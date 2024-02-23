package com.szl.train.model;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

/**
 * Author: Stan sai
 * Date: 2024/2/9 20:09
 * description:前缀树
 */
public class Trie {
    private Node root;
    public Trie() {
        root = new Node();
    }
    public void insert(String word) {
        if(word == null || word.isEmpty()) {
            return;
        }
        char[] chars = word.toCharArray();
        Node cur = root;
        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';
            Node next = cur.getNext(index);
            if(next == null) {
                next = new Node();
                cur.setNext(index, next);
            }
            ++next.pass;
            if(i == chars.length -1) {
                ++next.end;
            }
            cur = next;
        }
    }

    public int countWordEqualTo(String word) {
        Node node = getNode(word);
        return node == null ? 0 : node.end;
    }

    public int countWordStartingWith(String word) {
        Node node = getNode(word);
        return node == null ? 0 : node.pass;
    }

    public void erase(String word) {
        if(word == null || word.isEmpty()) {
            return;
        }
        char[] chars = word.toCharArray();
        Node cur = root;
        Node[] passes = new Node[chars.length];
        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';
            Node next = cur.getNext(index);
            if(next == null || (i == chars.length-1 && next.end == 0)) {
                return;
            }
            passes[i] = next;
            cur = next;
        }
        for (Node pass : passes) {
            --pass.pass;
        }
        --passes[chars.length-1].end;
    }

    private Node getNode(String word) {
        if(word == null || word.isEmpty()) {
            return null;
        }
        char[] chars = word.toCharArray();
        Node cur = root;
        for(char c : chars) {
            int index = c - 'a';
            cur = cur.getNext(index);
            if(cur == null) {
                return null;
            }
        }
        return cur;
    }

    class Node {
        int pass;
        int end;
        Node[] next;
        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new Node[26];
        }
        public Node getNext(int index) {
            return next[index];
        }
        public void setNext(int index, Node next) {
            this.next[index] = next;
        }
    }
}
