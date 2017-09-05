package lambda;

/*
* Created by  WangDi  on 2017/9/5 0005
*/
public class TestAnimal {

    public static void main(String[] args) {

<<<<<<< HEAD
        //animal为形参
        int i = 0;
        AnimalImpl animalImpl = new AnimalImpl((animal)->{

            return animal;
            //return 5;返回一个具体数值
        });

        //a为形参,较好体现
        animalImpl.getDistance((a)->{
            return a;
        });

=======
        AnimalImpl animalImpl = new AnimalImpl();
>>>>>>> 42cad9faa1ec2211bbbde5a2c1ef99541cca0f1b
    }
}
