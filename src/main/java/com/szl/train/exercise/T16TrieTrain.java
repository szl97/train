package com.szl.train.exercise;

import com.szl.train.model.Trie;

/**
 * Author: Stan sai
 * Date: 2024/2/9 20:09
 * description:前缀树
 * leetcode中第二个链接要求实现的更完整，题目需要收费
 * https://leetcode.cn/problems/implement-trie-prefix-tree/description/
 * https://leetcode.cn/problems/implement-trie-ii-prefix-tree/description/
 * 前缀和后缀搜索：
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * 实现 WordFilter 类：
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 * https://leetcode.cn/problems/prefix-and-suffix-search
 * 创建一个前缀树+一个后缀树,记录经过的数组元素位置即可
 */
public class T16TrieTrain {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("szl");
        trie.insert("hxx");
        trie.insert("szil");
        trie.insert("hxii");
        trie.insert("szpo");
        trie.insert("szlpii");
        trie.insert("hxp");
        trie.insert("hxx");
        trie.insert("hxxso");
        trie.insert("szl");
        System.out.printf("%5d", trie.countWordEqualTo("szl"));//2
        System.out.printf("%5d", trie.countWordStartingWith("szl"));//3
        System.out.printf("%5d", trie.countWordEqualTo("hxx"));//2
        System.out.printf("%5d", trie.countWordStartingWith("hxx"));//3
        System.out.printf("%5d", trie.countWordStartingWith("sz"));//5
        System.out.printf("%5d\n", trie.countWordStartingWith("hx"));//5

        trie.erase("szlgr");
        trie.erase("hxxpi");
        trie.erase("szlpil");
        trie.erase("szlpii");
        trie.erase("szl");
        trie.erase("hxii");

        System.out.printf("%5d", trie.countWordEqualTo("szl"));//1
        System.out.printf("%5d", trie.countWordStartingWith("szl"));//1
        System.out.printf("%5d", trie.countWordEqualTo("hxx"));//2
        System.out.printf("%5d", trie.countWordStartingWith("hxx"));//3
        System.out.printf("%5d", trie.countWordStartingWith("sz"));//3
        System.out.printf("%5d\n", trie.countWordStartingWith("hx"));//4
    }
}
