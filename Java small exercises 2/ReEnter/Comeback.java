public class Animal{

    protected int food;
    protected int thirst;


public Animal(int food,int thirst){
  this.food=food;
 this.thirst=thirst;
}

public void eat(int amount){
    food=food+amount;

}
public void drink(int amount){
    thirst=thirst+amount;
}

public String toString(){
    return "Food: "+food+" Thrist "+thirst;
}
}
    