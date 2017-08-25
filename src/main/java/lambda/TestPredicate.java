package lambda;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

/*
* Created by  WangDi  on 2017/8/24
*/
public class TestPredicate {

    public static void main(String[] args) {

        List<String> listString = new ArrayList<String>();
        listString.add("zhangsan");
        listString.add("lisi");
        listString.add("wanger");
        listString.add("zhangsan");

        int angNum = getNumAng(listString,ele->((String)ele).contains("ang"));//计算包含"ang"的字符串个数

        List<Object> listType = new ArrayList<Object>();
        listType.add(3);
        listType.add("jack");
        listType.add(true);
        listType.add(1.71);
        listType.add(false);
        listType.add((long)1.71);
        listType.add(true);
        listType.add(false);

        //以<boolean,对象类型数量>方式返回
        List<Map<Object,Integer>> list = getBoolean(listType,ssd->((Object)ssd).getClass().isInstance(new Boolean(false)));
        System.out.println(list.get(0).get(true));


    }


    static int getNumAng(Collection collection,Predicate predicate){

        int temp = 0;
        for (Object c:collection){

            if(predicate.test(c)){//包含字符串返回true

                temp++;
            }
        }

        return temp;
    }

    static List<Map<Object,Integer>> getBoolean(Collection collection,Predicate predicate){

        List<Map<Object,Integer>> resultList = new ArrayList<Map<Object,Integer>>();

        int trueNum = 0;
        int falseNum = 0;
        int otherNum = 0;

        Map<Object,Integer> map1 = new HashMap<Object, Integer>();
        Map<Object,Integer> map2 = new HashMap<Object, Integer>();
        Map<Object,Integer> map3 = new HashMap<Object, Integer>();
        for (Object c:collection){

            if(c instanceof Boolean){

                if((boolean)c){

                    trueNum++;
                    map1.put(true,trueNum);

                }else {

                    falseNum++;
                    map2.put(true,falseNum);
                }
            } else{

                otherNum++;
                map3.put("other",otherNum);
            }
        }

        resultList.add(map1);
        resultList.add(map2);
        resultList.add(map3);
        return resultList;
    }
}
