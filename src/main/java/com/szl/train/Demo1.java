package com.szl.train;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Stan Sai
 * Date: 2024/2/26 20:58
 * description:
 */
public class Demo1 {
    static class TreeNode {
        public TreeNode() {

        }

        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<Integer> getMostRight(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int i = queue.size();
            TreeNode lastNotNull = null;
            while (i-- > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    lastNotNull = node;
                }
                queue.offer(node == null ? null : node.left);
                queue.offer(node == null ? null : node.right);
            }
            if (lastNotNull != null) {
                result.add(lastNotNull.val);
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, -1, 5, -1, 4};
        //int[] nums = new int[]{1, -1, 3};
        TreeNode root = null;
        TreeNode[] nodes = new TreeNode[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new TreeNode();
            nodes[i].val = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = nodes[i];
            if (node.val == -1) {
                continue;
            }
            if (2 * i + 1 < nums.length && nums[2 * 1 + 1] >= 0) {
                node.left = nodes[2 * i + 1];
            }
            if (2 * i + 2 < nums.length && nums[2 * 1 + 2] >= 0) {
                node.right = nodes[2 * i + 2];
            }
            if (root == null) {
                root = node;
            }
        }
        List<Integer> l = getMostRight(root);
        for(int i : l) {
            System.out.printf("%5d", i);
        }
        System.out.println();
    }
}
