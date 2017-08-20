
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
* Created by  WangDi  on 2017/8/3
*/
public class Main {

    public static void main(String[] args) {

        Runnable run = new MyRunnable();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();

    }

}

class MyRunnable implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "_____________________" + i);
        }
    }
}