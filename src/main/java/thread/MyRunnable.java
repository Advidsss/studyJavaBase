package thread;

/*
* Created by  WangDi  on 2017/9/11 0011
*/


public class MyRunnable implements Runnable{

    private int total = 100;

    @Override
    public void run() {


            while (total > 0){

                System.out.println(Thread.currentThread().getName());
                total--;
            }






    }
}
