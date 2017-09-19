package util;

/*
* Created by  WangDi  on 2017/9/18 0018
*/


public class StringUtils {


    //是否为空字符串
    public static boolean isEmpty(String str){

        return (str == null || "".equals(str));
    }

    //是否有长度
    public static boolean hasLength(CharSequence str){

        return (str != null && str.length()>0 );
    }

    //是否含有字符
    public static boolean hasText(CharSequence actStr){

        if(!hasLength(actStr)){

            return false;
        }

        for (int i = 0; i < actStr.length(); i++) {

            if(!Character.isWhitespace(actStr.charAt(i))){
                return true;
            }
        }
        return false;
    }

    //去除所有空格
    public static String trimAllWhiteSpace(String str){

        if(!hasLength(str)){
            return str;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuilder);
        int index = 0;

        while (stringBuilder.length() > 0){

            if(Character.isWhitespace(stringBuilder.charAt(index))){

                stringBuilder.deleteCharAt(index);
            }else {

                index++;
            }
        }

        return str;
    }
}
