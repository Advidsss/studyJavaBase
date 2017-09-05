package time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
* Created by  WangDi  on 2017/8/29
*/
public class CalendarAndDate {

    Calendar calendar = Calendar.getInstance();//获取当前日历的实例

    /*
      当前SimpleDateFormat类中,每一个字母代表一定的标识:y(年),M(月),d(日),h(1~12小时制上午和下午),H(0~23小时制)
      m(秒),s(秒),S(毫秒),E(星期),D(一年中的第几天),F(一月中第几个星期几),w(一年中第几个星期),W(一月中第几个星期)
      a(上午/下午标记符),k(在一天中 (1~24)),K( 在上午或下午 (0~11)),z(时区)
    */
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    boolean testCalendar(){
        calendar.getTime();//获取当前时间
        return true;
    }

    boolean testDate(){
        new Date();//获取当前时间
        return true;
    }
}
