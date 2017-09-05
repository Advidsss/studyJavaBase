package exception;

/*
* Created by  WangDi  on 2017/9/5 0005
*/
public class TestException {

    public static void main(String[] args) {

        try {

            TestException.getException();

        } catch (MyException e) {

            e.printStackTrace();
        }

    }

    static void getException() throws MyException{

        IntException.f(3);
    }
}


