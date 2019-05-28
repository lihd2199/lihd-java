package com.lihd.java.java8;

public class DefaultMethodExample {

    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {


    default void print() {
        System.out.println("我是一默认方法!");
    }

    static void blowHorn() {
        System.out.println("我是一静态方法!!!");
    }
}

interface FourWheeler {

    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

class Car implements Vehicle, FourWheeler {

    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }

}
