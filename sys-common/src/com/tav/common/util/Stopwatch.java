/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.util;

/**
 *
 * @author thuannht
 */
import java.util.concurrent.TimeUnit;

/**
 *
 * @author thuannht
 */
public class Stopwatch {

    private final long start;

    /**
     * Hàm khởi tạo
     */
    public Stopwatch() {
        start = System.nanoTime();
    }

    /**
     * Hàm trả lại thời gian thực hiện của nghiệp vụ. Đơn vị là Mili giây.
     * @return 
     */
    public double elapsedTime() {
        long now = System.nanoTime();
        double miliseconds = (double) TimeUnit.MICROSECONDS.convert(now - start, TimeUnit.NANOSECONDS) / 1000.0;
        return miliseconds;
    }

//    /**
//     * Một Ví dụ về sử dụng để đo
//     */
//    public static void main(String[] args) throws InterruptedException {
//        //Bắt đầu đo        
//        Stopwatch timer1 = new Stopwatch();
//
//        //Doan code can do thoi gian
//        int n = 10;
//        double sum1 = 0.0;
//        for (int i = 1; i <= n; i++) {
//            sum1 += Math.sqrt(i);
//        }
//
//        //Ket thuc viec do va Ghi log ra ket qua. Đội DEV ghi log ra file dung Log4j, nội dung Log cần có đủ thong tin: class, method, dòng code
//        System.out.println(timer1.elapsedTime() + "--Miliseconds");
//    }
}
