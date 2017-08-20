/*
* Created by  WangDi  on 2017/8/3
*/
public class TestClassInitialize {

    static double i1;
    char c;
    double i;

/*
*静态域随着类的加载而加载
* 非静态域在对象被创建的过程中,构造器对其进行初始化
*
* */
    public static void main(String[] args) {
        System.out.println(i1);

        TestClassInitialize testClassInitialize = new TestClassInitialize();

        System.out.println(testClassInitialize.c);
        System.out.println(testClassInitialize.i);
    }
}
