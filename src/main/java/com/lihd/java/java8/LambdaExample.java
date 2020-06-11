package com.lihd.java.java8;

public class LambdaExample {


    public static void main(String[] args) {

        LambdaExample tester = new LambdaExample();

        // 类型声明
        MathOperation addition = Integer::sum;

        // 不用类型声明
        MathOperation subtraction = (int a, int b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> a * b;

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + addition.operation(1, 10));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }


    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
