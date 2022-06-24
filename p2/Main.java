package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    static int max;

    public static void main(String[] args) {
        int arr1[] = {69, 21, 201, 91, 30, 20, 33, 3, 80, 19, 76, 21, 10, 11, 2, 44, 9};
        int arr2[] = {29, 21, 51, 60, 84, 24, 44, 8, 59, 63, 76, 69, 83, 89, 52, 2};
        int arr3[] = new int[100];

        Random rand = new Random();
        for(int i = 0; i < arr3.length; i++){
            int num = rand.nextInt(256-1) + 1;
            arr3[i] = num;
        }

        // Recursive Method
        long start = System.currentTimeMillis();
        System.out.println("Length of Array 1 using Recursive: " + recursiveHelp(arr1,arr1.length-1));
        long end = System.currentTimeMillis();
        long RecTotal1 = end - start;
        start = System.currentTimeMillis();
        System.out.println("Length of Array 2 using Recursive: " + recursiveHelp(arr2,arr2.length-1));
        end = System.currentTimeMillis();
        long RecTotal2 = end - start;
        start = System.currentTimeMillis();
        System.out.println("Length of Array 3 using Recursive: " + recursiveHelp(arr3,arr3.length-1));
        end = System.currentTimeMillis();
        long RecTotal3 = end - start;

        // Dynamic Programming
        start = System.currentTimeMillis();
        System.out.println("Length of Array 1 using Dynamic: " + DynamicProgramming(arr1, arr1.length));
        end = System.currentTimeMillis();
        long DynTotal1 = end - start;
        start = System.currentTimeMillis();
        System.out.println("Length of Array 2 using Dynamic: " + DynamicProgramming(arr2, arr2.length));
        end = System.currentTimeMillis();
        long DynTotal2 = end - start;
        start = System.currentTimeMillis();
        System.out.println("Length of Array 3 using Dynamic: " + DynamicProgramming(arr3, arr3.length));
        end = System.currentTimeMillis();
        long DynTotal3 = end - start;


        System.out.println();
        System.out.println("         Recursive     Dynamic Programming");
        System.out.println("---------------------------------------------------");
        System.out.print("Array 1: ");
        System.out.printf("%-14s", RecTotal1 + "ms");
        System.out.println(DynTotal1 + "ms");
        System.out.print("Array 2: ");
        System.out.printf("%-14s", RecTotal2 + "ms");
        System.out.println(DynTotal2 + "ms");
        System.out.print("Array 3: ");
        System.out.printf("%-14s", RecTotal3 + "ms");
        System.out.println(DynTotal3 + "ms");
    }
    static int recursiveHelp(int arr[], int x){
        max = 1;
        Recursive(arr, x);
        return max;
    }
    static int Recursive(int arr[], int x){
        int end = 0;
        if(x == 0){
            return 0;
        }
        for(int i = 0; i < x; i++){
            int a = Recursive(arr,i);
            if(arr[i] < arr[x] && a+1 > end){
                end = a+1;
            }
        }
        if(max < end)
            max = end;
        return end;
    }

    static int DynamicProgramming(int arr[], int x){
        int set[] = new int[x];
        int len = 0;

        for(int i = 0; i < set.length; i++){
            set[i] = 0;
        }

        for(int i = 1; i < x; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j] && set[i] < set[j] + 1){
                    set[i] = set[j] + 1;
                }
            }
        }

        for(int i = 0; i < x; i++){
            if(len < set[i]){
                len = set[i];
            }
        }
        return len;
    }

}
