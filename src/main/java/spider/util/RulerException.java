package spider.util;
/*
* Created by  WangDi  on 2017/8/31
*/

import spider.Ruler;

//自定义业务异常
public class RulerException extends Exception{

    public RulerException(){
        super();
    }

    public RulerException(String msg){

        super(msg);
    }

    public RulerException(String msg,Throwable cause){

        super(msg,cause);
    }

    public RulerException(Throwable cause){

        super(cause);
    }

}
