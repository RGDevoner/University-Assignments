import java.util.Scanner;
public class Restart{
static Animal animal;
    public static void main(String[] args){
   
   // Animal[] animals= new Animal[10];
    //animals[0]= new Monkey(1,1);
    //animals[1]=new Cat(2,2);
    //System.out.println(animals[0]);
   // System.out.println(animals[1]);


   animal= new Monkey(1,1);
   animal.eat(2);
   animal.drink(1);
   System.out.println(animal);
    }
}


