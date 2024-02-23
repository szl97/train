package com.szl.train.exercise;

/**
 * Author: Stan sai
 * Date: 2024/2/8 22:10
 * description:快速排序
 */
public class T8QuickSort {
    public static int[] quickSort(int[] array) {
        if(array == null || array.length < 2) {
            return array;
        }
        partition(array, 0, array.length - 1);
        return array;
    }

    public static void partition(int[] array, int low, int high) {
        if(low >= high) {
            return;
        }
        int smallR = low;
        int largeL = high;
        int i = low + 1;
        while(i <= largeL) {
            if(array[i] == array[smallR]) {
                i++;
            } else if(array[i] < array[smallR]) {
                swap(array, smallR, i);
                smallR++;
                i++;
            } else if(array[i] > array[smallR]) {
                swap(array, largeL, i);
                largeL--;
            }
        }
        partition(array, low, smallR - 1);
        partition(array, largeL + 1, high);
    }
    public static void process(int[] array, int low, int high) {
        if(low >= high) {
            return;
        }
        int temp = low;
        for (int i = low + 1; i <= high; i++) {
            if(array[i] <= array[low]) {
                temp++;
                swap(array, temp, i);
            }
        }
        swap(array, low, temp);
        process(array, low, temp-1);
        process(array, temp+1, high);

    }
    public static void swap(int[] array, int target, int cur) {
        int temp = array[cur];
        array[cur] = array[target];
        array[target] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{8,7,44,8,7,6,16,9,11,5,4,6,1,2,0,5,7,2,13,67,85,0,5,3,8};
        quickSort(array);
        for(int i : array) {
            System.out.printf("%5d", i);
        }
        System.out.println();
    }
}
