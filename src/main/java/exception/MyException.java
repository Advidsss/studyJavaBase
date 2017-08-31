package exception;

/*
* Created by  WangDi  on 2017/8/22
*/
public class MyException extends Exception {

    public MyException(){

    }

    public MyException(String msg){

        super(msg);
    }

    public MyException(String msg,Throwable cause){

        super(msg,cause);
    }

    public MyException(Throwable cause){

        super(cause);
    }
}
