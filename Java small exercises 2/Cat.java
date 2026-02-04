public class Cat extends Animal{
    public Cat(int fo,int th){

        super(fo,th);
    }
    public void eat(int amount){
        food +=amount/4;
    }
    public void drink(int amount){
        food+=amount/4;
    }
}