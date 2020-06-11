package com.lihd.java.concurrent.threadcommunication;


/**
 * @program: lihd-java
 * @description: three thread Alternate Printing
 * @author: li_hd
 * @create: 2020-01-15 15:02
 **/
public class AlternatePrinting2 extends Thread {

    private Integer integer;

    private Integer value = 0;

    private AlternatePrinting2(Integer integer) {
        this.integer = integer;
    }

    public static void main(String[] args) {


        AlternatePrinting2 myThread1 = new AlternatePrinting2(0);
        AlternatePrinting2 myThread2 = new AlternatePrinting2(1);
        AlternatePrinting2 myThread3 = new AlternatePrinting2(2);
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }


    @Override
    public void run() {
        while (true) {
            if (value % 3 != integer) {
                Thread.yield();
                continue;
            }
            System.out.println(integer);
            if (value++ == 10000) {
                break;
            }
        }
    }

}
