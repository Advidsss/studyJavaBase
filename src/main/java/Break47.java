/**
 * Created by WangDi on 2017/7/27.
 */
public class Break47 {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            if(i == 4){//return用于结束当前方法,break用于跳出当前循环,继续按照程序流程执行,continue执行下一个循环
//                break;
//                continue;
                return;
            }
        }

        //break会执行到这里
        System.out.println("break与return的区别！");
    }
}
