/**
 * Created by WangDi on 2017/7/27.
 */
public class Break47 {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            if(i == 4){//return用于结束当前方法，break用于跳出当前循环，继续执行该方法循环下面的语句
//                break;
                return;
            }
        }

        System.out.println("break与return的区别！");
    }
}
