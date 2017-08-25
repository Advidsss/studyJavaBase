package exception;

/*
* Created by  WangDi  on 2017/8/22
*/
public class TestException {

    public static void main(String[] args) {

        try {
            f(3);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    static void f(int i) throws MyException{

        System.out.println("This is f!");
        if(i == 4){
            throw new MyException("4 is wrong!");
        }else if(i == 3){
            throw new MyException("3 is wrong!");
        }

    }
}
