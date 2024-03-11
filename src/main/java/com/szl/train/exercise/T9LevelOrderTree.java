package com.szl.train.exercise;

import com.szl.train.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Stan sai
 * Date: 2024/2/8 23:40
 * description:
 * 二叉树的遍历、判断是否是完全二叉树
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class T9LevelOrderTree {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < curLevelSize; i++) {
                TreeNode cur = queue.removeFirst();
                list.add(cur.val);
                if(cur.left != null) {
                    queue.addLast(cur.left);
                }
                if(cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        boolean mustLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.removeFirst();
            if(cur.left == null && cur.right != null) {
                return false;
            }
            if(mustLeaf && cur.left != null) {
                return false;
            }
            if(cur.left == null || cur.right == null) {
                mustLeaf = true;
            }
            if(cur.left != null) {
                queue.addLast(cur.left);
            }
            if(cur.right != null) {
                queue.addLast(cur.right);
            }
        }
        return true;
    }


}
