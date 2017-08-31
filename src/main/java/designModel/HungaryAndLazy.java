package designModel;

/*
* Created by  WangDi  on 2017/8/29
*/
public class HungaryAndLazy {

    private static HungaryAndLazy hungary = new HungaryAndLazy();
    private static HungaryAndLazy lazy = null;
    private static HungaryAndLazy lazy1 = null;
    private static HungaryAndLazy lazy2 = null;

    public static HungaryAndLazy getHungary(){//线程安全

        return hungary;
    }

    public static HungaryAndLazy getLazy(){//线程不安全

        if(lazy == null){
            lazy = new HungaryAndLazy();
        }
        return lazy;
    }

    public static synchronized HungaryAndLazy getLazy1(){//线程同步(同步方法)(安全)

        if(lazy1 == null){
            lazy1 = new HungaryAndLazy();
        }
        return lazy1;
    }

    public static HungaryAndLazy getLazy2(){//线程同步(同步代码块)(安全)

        synchronized(HungaryAndLazy.class){

            if(lazy2 == null){
                lazy2 = new HungaryAndLazy();
            }
        }
        return lazy2;
    }
}
