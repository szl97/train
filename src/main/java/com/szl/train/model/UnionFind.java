package com.szl.train.model;

/**
 * Author: Stan sai
 * Date: 2024/2/9 17:53
 * description: 并查集
 */
public class UnionFind {
    public int[] fathers;
    public int[] size;
    public int[] help;
    public int sets;

    public UnionFind(int n) {
        fathers = new int[n];
        size = new int[n];
        help = new int[n];
        sets = n;
        for(int i = 0; i < n; i++) {
            fathers[i] = i;
            size[i] = 1;
        }
    }

    public int findFather(int cur){
        int size = 0;
        while(fathers[cur] != cur) {
            help[size++] = cur;
            cur = fathers[cur];
        }
        while(size > 0) {
            fathers[help[--size]] = cur;
        }
        return cur;
    }


    public boolean isSameSet(int a, int b) {
        return findFather(a) == findFather(b);
    }

    public int union(int a, int b) {
        a = findFather(a);
        b = findFather(b);
        if(a == b) {
            return a;
        }
        if(size[a] >= size[b]) {
            size[a] += size[b];
            fathers[b] = a;
        } else {
            size[b] += size[a];
            fathers[a] = b;
        }
        --sets;
        return fathers[a];
    }
}
