package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
* Created by  WangDi  on 2017/8/29
*/
public class Animal{


    public static void main(String[] args) {

        List<String> list  = new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");;
        Object [] arrayStr = list.toArray();
        for (Object a:arrayStr) {
            System.out.println(a);
        }
    }
}
