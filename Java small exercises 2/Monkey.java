public class Monkey extends Animal{
private boolean isClimbing;


public Monkey(int f,int t){
    super(f,t);
    isClimbing=false;
}
public void ClimbDown(){
    isClimbing=false;
}
public String toString(){
    return super.toString()+"Climbing :"+isClimbing;
}
public void eat(int ammount){
    food +=ammount/2;
}
public void drink(int ammount){
    food+=ammount/2;
}




}