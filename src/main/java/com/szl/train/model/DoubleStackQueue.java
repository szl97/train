package com.szl.train.model;

import java.util.Stack;

/**
 * Author: Stan sai
 * Date: 2024/2/18 23:32
 * description:用栈实现队列
 */
public class DoubleStackQueue {
    private Stack<Integer> stack;
    private Stack<Integer> temp;
    public DoubleStackQueue() {
        stack = new Stack<>();
        temp = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        if(temp.isEmpty()) {
            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }
        }
        int val = temp.pop();
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return val;
    }

    public int peek() {
        if(temp.isEmpty()) {
            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }
        }
        int val = temp.peek();
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return val;
    }

    public boolean empty() {
        return stack.isEmpty() && temp.isEmpty();
    }
}
