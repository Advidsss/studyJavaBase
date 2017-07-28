import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangDi on 2017/7/27.
 */
public class TestSwitch {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.forEach(str->{

            switch (str){

                case "A":
                    System.out.println("This is A!");
                    break;
                    case "B":
                        System.out.println("This is B!");
                        break;
                    default:
                        System.out.println("This is not A or B!");
                        break;

            }
                }
        );
    }


}
