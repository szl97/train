package com.szl.train.exercise;

import com.szl.train.model.UnionFind;

/**
 * Author: Stan sai
 * Date: 2024/2/9 18:10
 * description:并查集练习
 * 1、省份数量 https://leetcode.cn/problems/number-of-provinces/description/
 * 2、岛屿问题 https://leetcode.cn/problems/number-of-islands/description/
 * 3、情侣换座位 https://leetcode.cn/problems/couples-holding-hands/description/
 */
public class T15UnionFindTrain {
    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null) {
            return 0;
        }
        int len = isConnected.length;
        if(len <= 1) {
            return len;
        }
        UnionFind unionFind = new UnionFind(len);
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(i != j && isConnected[i][j] == 1) {
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.sets;
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int h = grid.length;
        int w = grid[0].length;
        int size = h*w;
        UnionFind unionFind = new UnionFind(size);
        int lands = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(grid[i][j] == '1') {
                    int index = i * w + j;
                    if(j != w-1 && grid[i][j+1] == '1') {
                        unionFind.union(index, index + 1);
                    }
                    if(i != h-1 && grid[i+1][j] == '1') {
                        unionFind.union(index, index + w);
                    }
                } else {
                    ++lands;
                }
            }
        }
        return unionFind.sets - lands;
    }

    public int minSwapsCouples(int[] row) {
        if(row == null || row.length <= 2) {
            return 0;
        }
        int n = row.length/2;
        UnionFind unionFind = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            int group1 = row[2*i]/2;
            int group2 = row[2*i+1]/2;
            if(group1 != group2) {
                unionFind.union(group1, group2);
            }
        }
        return n - unionFind.sets;
    }
}
