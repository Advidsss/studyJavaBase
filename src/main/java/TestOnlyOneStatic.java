/*
* Created by  WangDi  on 2017/8/3
*/
public class TestOnlyOneStatic {

    public static void main(String[] args) {

        B b1 = new B();;
        B b2 = new B();

        if(b1.a1 == b2.a1){

            System.out.println("静态对象只会被实例化一次");
        }else{

            System.out.println("静态对象会被实例化多次");
        }

        if(b1.a2 == b2.a2){

            System.out.println("非静态对象只会被实例化一次");
        }else{

            System.out.println("非静态对象会被实例化多次");

        }

    }
}

class A {


}

class B {

    static A a1 = new A();

    A a2 = new A();
}