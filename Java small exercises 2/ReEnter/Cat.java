public class Cat extends Animal {

    private int IQ;

    public Cat(int fo,int th){
        super(fo,th);
    }
    public void eat(int amount){
        food=food+amount/4;
    }
    public void drink(int amount){
        thirst=thirst+amount/4;  
    }
    public String toString(){
        return super.toString();
    }
}