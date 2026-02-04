public class Monkey extends Animal implements WalkOnFeet{
    private boolean isClimbing;

   public Monkey(int f,int t){
        super(f,t);
       // isClimbing = false;
    }
    public void eat(int amount){
    food=food+amount/2;
    }
    public void drink(int amount){
    thirst=thirst+amount/2;   
    }
    
    //public void climb(){
       // isClimbing=true;
   // }
    //public void climbdown(){
      //  isClimbing = false;
    //}
    //public void drink(int amount){
    //    thirst=amount/2;
    //}
    public String toString(){
        return super.toString();
    }
    public void walk(int steps){

    }
    public int distanceTraveled(){
    return 0;
    }
}