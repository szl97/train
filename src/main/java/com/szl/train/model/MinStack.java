package com.szl.train.model;

import java.util.Stack;

/**
 * Author: Stan sai
 * Date: 2024/2/18 23:25
 * description:
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 */
public class MinStack {

    private Stack<Integer> dataStack;

    private Stack<Integer> minStack;

    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        if(minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
        dataStack.push(val);
    }

    public void pop() {
        if(dataStack.isEmpty()) {
            return;
        }
        int val = dataStack.pop();
        if(minStack.peek() == val) {
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
