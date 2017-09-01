package spider.util;
/*
* Created by  WangDi  on 2017/9/1
*/


import spider.Ruler;

//用于校验ruler是否符合规则
public class ValidateRuler {

    public static boolean accordRuler(Ruler ruler)throws RulerException{

        String url = ruler.getUrl();
        if(url != null){//此处需要写StringUtil工具

            throw new RulerException("url地址不能为空!");

        }else if(url.startsWith("http://")||url.startsWith("https://")){

            throw new RulerException("url地址不符合规范!,应该以http://或者https://开头!");
        }


        return true;
    }
}
