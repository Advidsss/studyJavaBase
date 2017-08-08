/*
* Created by  WangDi  on 2017/8/3
*/
public class TestOnlyOneStatic {

    public static void main(String[] args) {


    }
}

class A {


}

class B {

    static A a1 = new A();

    A a2 = new A();
}