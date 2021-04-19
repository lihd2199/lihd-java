package com.lihd.java.concurrent;

/**
 * @author: li_hd
 * @date: 2020-08-20 10:18
 **/
public class SampleForVolatile2 {

    public volatile long sum = 0;

    public int add(int a, int b) {
        int temp = a + b;
        sum += temp;
        return temp;
    }

    public static void main(String[] args) {
        SampleForVolatile2 test = new SampleForVolatile2();

        int sum = 0;

        for (int i = 0; i < 1000000; i++) {
            sum = test.add(sum, 1);
        }

        System.out.println("Sum:" + sum);
        System.out.println("Test.sum:" + test.sum);
    }

    // -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly 查看汇编语言

    //  0x00000000038d3105: movsxd  rsi,r8d
    //  0x00000000038d3108: add     rax,rsi
    //  0x00000000038d310b: mov     qword ptr [rsp+30h],rax
    //  0x00000000038d3110: vmovsd  xmm0,qword ptr [rsp+30h]
    //  0x00000000038d3116: vmovsd  qword ptr [rdx+10h],xmm0
    //  0x00000000038d311b: lock add dword ptr [rsp],0h  ;*putfield sum
    //                                                ; - com.lihd.java.concurrent.SampleForVolatile2::add@12 (line 13)


}
