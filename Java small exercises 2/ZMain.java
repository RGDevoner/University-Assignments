public class ZMain{
static Animal animal;
    public static void main(String[]  args){

        animal=new Monkey(1,1);
        animal=new Cat(1,1);
        animal.eat(1);
        
        System.out.println(animal);
    }
}