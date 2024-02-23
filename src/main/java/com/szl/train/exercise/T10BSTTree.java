package com.szl.train.exercise;

import com.szl.train.model.TreeNode;

/**
 * Author: Stan sai
 * Date: 2024/2/9 00:21
 * description:验证搜索二叉树
 * https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class T10BSTTree {
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        return getTreeInfo(root).bst;
    }

    public TreeInfo getTreeInfo(TreeNode root) {
        if(root.left == null && root.right == null) {
            return new TreeInfo(root.val, root.val,true);
        }
        int max = root.val;
        int min = root.val;
        if(root.left != null) {
            TreeInfo left = getTreeInfo(root.left);
            if(!left.bst || left.max >= root.val) {
                return new TreeInfo(max, min, false);
            }
            min = left.min;
        }
        if(root.right != null) {
            TreeInfo right = getTreeInfo(root.right);
            if(!right.bst || right.min <= root.val) {
                return new TreeInfo(max, min, false);
            }
            max = right.max;
        }
        return new TreeInfo(max,min,true);
    }

    class TreeInfo{
        int max;
        int min;
        boolean bst;
        public TreeInfo(int max, int min, boolean bst) {
            this.max = max;
            this.min = min;
            this.bst = bst;
        }
    }

}
