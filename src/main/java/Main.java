
import util.CalendarUtils;

import java.lang.reflect.Array;
import java.util.*;

/*
* Created by  WangDi  on 2017/8/3
*/
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String str = scanner.nextLine();
        String str1 = scanner1.nextLine();

        int length = Math.min(str.length(),str1.length());
        int pos = 0;

        while (pos < length){
            if(0 != (str.charAt(pos)^str1.charAt(pos))){
                break;
            }
            pos++;
        }

        System.out.println(str.substring(pos));
        System.out.println(str1.substring(pos));
    }

}
