package genericity;

/*
* Created by  WangDi  on 2017/10/10 0010
*/


public class TestGeneric {

    public static void main(String[] args) {

        Person<String> p = new Person<String>();
        p.show(4);
    }
}
