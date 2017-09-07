package lambda;

/*
* Created by  WangDi  on 2017/9/5 0005
*/
public class AnimalImpl{

    private Animal a = null;

    public AnimalImpl(){

    }

    public AnimalImpl(Animal animal){

        //传入实际参数
        System.out.println(animal.runDistance(35));
        a = animal;
    }

    public void getDistance(Animal animal){

        //传入实际参数
        System.out.println(animal.runDistance(6));
        System.out.println(animal.runDistance(40));
    }
}
