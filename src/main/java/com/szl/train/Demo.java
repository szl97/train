package com.szl.train;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

/**
 * Author: Stan sai
 * Date: 2024/2/21 17:29
 * description:
 */
public class Demo {

    //x^n
    public static List<Integer> power(int x, int n) {
        List<Integer> list = new ArrayList<>();
        if(n == 0) {
            list.add(1) ;
            return list;
        }
        int i = 1;
        list.add(x);
        while(i <= n) {
            int add = 0;
            int y = x;
            while (y != 0) {
                for(int j = 0; j < list.size(); j++) {
                    int m1 = list.get(j);
                    int m2 = y % 10;
                    int temp = m1 * m2 + add;
                    list.remove(j);
                    list.add(j, temp % 10);
                    add = temp / 10;
                }
                y /= 10;
            }
            if(add != 0) {
                list.add(add);
            }
            i++;
        }
        return list;
    }

    public static int[] sort(int[] nums) {
        if(nums == null || nums.length < 2) {
            return nums;
        }
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for(int num : nums) {

            while (!stack1.isEmpty() && stack1.peek() > num) {
                stack2.push(stack1.pop());
            }

            while (!stack2.isEmpty() && stack2.peek() < num) {
                stack1.push(stack2.pop());
            }

            stack1.push(num);
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        for(int i = 0; i < nums.length; i++) {
            nums[i] = stack2.pop();
        }
        return nums;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Integer> m = power(2, 10);
        int[] nums = new int[] {7,19,7,6,4,87,65};
        nums = sort(nums);
        for(int num : m) {
            System.out.printf("%d",num);
        }
        System.out.println();
        String s = "adadafsehqhvhqhi";
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(new Record());
        os.flush();
        byte[] bytes = bs.toByteArray();
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(1024);
        os.close();
        bs.close();
        buffer1.put(bytes);
        buffer1.clear();
        byte[] bytes1 = new byte[buffer1.limit() - buffer1.position()];
        buffer1.get(bytes1);
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes1);
        ObjectInputStream oi = new ObjectInputStream(bi);
        Object o = oi.readObject();
        oi.close();
        bi.close();
        System.out.println();
    }

    static class Record implements Serializable {
        public Record() {

        }
        String key = UUID.randomUUID().toString();
        Student student = new Student();
    }

    static class Student implements Serializable {
        public Student() {

        }
        String name = "adhyqhuehuqehuiiu是缺u且飞机";
        long id = 9L;
        String phone = "76278147817893891893189";
        String address = "阿护齿画画后期会期间急哦全集哦放假哦IE去切记哦飞机uhiqehuhui今";
        String[] hobbies = new String[] {"ajjajk", "ajcfhiuqiuiueqiufheqo", "hjqehjhjeqjh"};
    }
}
