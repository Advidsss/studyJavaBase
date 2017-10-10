package genericity;

/*
* Created by  WangDi  on 2017/9/13 0013
*/


public class Person <T> {

     <T> T show(T t){
         System.out.println(t);
         Object obj = null;
         return (T)obj;
    }
}
