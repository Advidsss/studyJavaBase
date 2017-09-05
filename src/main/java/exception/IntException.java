package exception;

/*
* Created by  WangDi  on 2017/8/22
*/
public class IntException {



    static void f(int i) throws MyException{

        if(i == 4){

            System.out.println("This is 4!");
        }else if(i == 3){

            throw new MyException("3 is wrong!");
        }else {

            throw new MyException("none is wrong!");
        }

    }
}
