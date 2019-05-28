package com.lihd.java;


public class App {

    public static void main(String[] args) {

        Test6 test2 = new Test6();

        try{
            try {
                test2.test();
            }catch (Exception e){
                System.out.println(e);
                System.out.println("654321");
            }
        }catch (Exception e){
            System.out.println("1234432345432");
        }


        System.out.println("123456789");

    }


}

class Test6{

    protected void test(){

        throw new RuntimeException("fuck");

    }


}