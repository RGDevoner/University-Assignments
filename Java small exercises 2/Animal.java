public abstract class Animal{
protected int food;
protected int thirst;

public Animal(int food,int thirst){
    this.food=food;
    this.thirst=thirst;
}
public abstract void eat(int ammount);
public abstract void drink(int ammount);
public String toString(){

    return "Food:"+food+"Thirst:"+thirst;
}

}