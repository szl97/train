package com.szl.train.model;

/**
 * Author: Stan sai
 * Date: 2024/2/8 23:39
 * description:
 */
public class TreeNode {
     public int val;
     public TreeNode left;
     public TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
