package com.example.demo.bytecode;

/**
 * @description: 字节码学习类
 * @author: yaohui.wang
 * @since: 2020/10/17 10:07
 */
public class ByteCodeStudy {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 3, 4};
        int a = 0;
        if(true){
            a = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            a++;
        }
        for (int b : arr) {
            a += b;
        }
    }
}
