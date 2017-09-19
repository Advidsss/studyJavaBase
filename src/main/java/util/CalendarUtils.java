package util;

/*
* Created by  WangDi  on 2017/9/18 0018
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*
  当前SimpleDateFormat类中,每一个字母代表一定的标识:y(年),M(月),d(日),h(1~12小时制上午和下午),H(0~23小时制)
  m(秒),s(秒),S(毫秒),E(星期),D(一年中的第几天),F(一月中第几个星期几),w(一年中第几个星期),W(一月中第几个星期)
  a(上午/下午标记符),k(在一天中 (1~24)),K( 在上午或下午 (0~11)),z(时区)
*/
public class CalendarUtils {

    private static final int oneDay = 86400000;
    private static Calendar calendar;
    private static SimpleDateFormat simpleDateFormat;

    static {

        calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        simpleDateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
    }

    //获取当前时间
    public static Date getDateTime(){

        return calendar.getTime();
    }

    //String转Date
    public static String getDate2String(Date date){

        return simpleDateFormat.format(date);
    }

    //Date转String
    public static Date getString2Date(String stringDate){

        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("不符合当前日期格式要求");
        }

        return date;
    }

    //获取两个日期相差天数
    public static long getDiffDays(Date earlyDate,Date laterDate){

        long diffDays = (laterDate.getTime() - earlyDate.getTime())/oneDay;
        return diffDays + 1;
    }

}
