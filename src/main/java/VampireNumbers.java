/*
* Created by  WangDi  on 2017/8/8
*
* 吸血鬼数字：位数为偶数，可由一对数字相乘，这种数字包含乘积一半位数的数字，并且由两个0结尾的数字不允许
*/
public class VampireNumbers {//一脸懵逼^_^

    static int a(int i){

        //千位上的数字
        return i / 1000;
    }

    static int b(int i){

        //百位上的数字
        return (i%1000)/100;
    }

    static int c(int i){

        //十位上的数字
        return ((i%1000)%100)/10;
    }

    static int d(int i){

        //个位上的数字
        return ((i%1000)%100)%10;
    }

    static int com(int i,int j){

        return (i * 10) + j;
    }

    static void productTest(int i,int m,int n){

        if(m * n == i) System.out.println(i + " = " + m + " * " + n);
    }

    public static void main(String[] args) {

        for (int i = 1001;i < 9999;i++){

            productTest(i, com(a(i), b(i)), com(c(i), d(i)));
            productTest(i, com(a(i), b(i)), com(d(i), c(i)));
            productTest(i, com(a(i), c(i)), com(b(i), d(i)));
            productTest(i, com(a(i), c(i)), com(d(i), b(i)));
            productTest(i, com(a(i), d(i)), com(b(i), c(i)));
            productTest(i, com(a(i), d(i)), com(c(i), b(i)));
            productTest(i, com(b(i), a(i)), com(c(i), d(i)));
            productTest(i, com(b(i), a(i)), com(d(i), c(i)));
            productTest(i, com(b(i), c(i)), com(d(i), a(i)));
            productTest(i, com(b(i), d(i)), com(c(i), a(i)));
            productTest(i, com(c(i), a(i)), com(d(i), b(i)));
            productTest(i, com(c(i), b(i)), com(d(i), a(i)));

        }
    }
}
