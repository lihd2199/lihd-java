package com.lihd.java.designmode;

/**
 * @author lihd
 * @desc 简单工厂类
 */
public class Test4{


    public static void main(String[] args) {

        Test4.getProduct("1").test1();
        Test4.getProduct("1").test2();
        Test4.getProduct("2").test1();
        Test4.getProduct("2").test2();

    }

    public static Product getProduct(String arg){

        Product product = null;

        if(arg.equals("1")){
            product = new ProductImpl1();
        }
        if(arg.equals("2")){
            product = new ProductImpl2();
        }

        return product;

    }


}


/**
 * 工厂模式 Product 抽象类
 */
abstract class Product {

    /*
    抽象出来的方法
     */
    public abstract void test1();


    public void test2(){
        System.out.println("公共方法");
    }

}

/**
 * Product 的具体实现类
 */
class ProductImpl1 extends Product {

    /*
    不同实例实现不同的方法
     */
    @Override
    public void test1() {
        System.out.println("hello world one");
    }
}

/**
 * Product 的具体实现类
 */
class ProductImpl2 extends Product {

    /*
    不同实例实现不同的方法
     */
    @Override
    public void test1() {
        System.out.println("hello world two");
    }
}
