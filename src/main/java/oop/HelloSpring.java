package oop;

/*
* Created by  WangDi  on 2017/10/12 0012
*/
public class HelloSpring implements HelloInterface {

    @Override
    public void sayHello() {

        System.out.println("hello world!");
    }

    @Override
    public void run() {
        System.out.println("dog run!");
    }

    @Override
    public String eat() {

        return "cat eat!";
    }
}
