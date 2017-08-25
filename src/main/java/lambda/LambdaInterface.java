package lambda;

/*
* Created by  WangDi  on 2017/8/24
*/
public interface LambdaInterface {

    int getSum(int x,int y);

    default void sum(){//此方法前有default修饰,类实现接口时会自动拥有该方法,可以对该方法进行重写,可同时定义多个
        System.out.println("HaHa,This is Lambda new Function!");
    }

}
