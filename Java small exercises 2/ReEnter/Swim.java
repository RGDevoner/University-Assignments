public class Swim extends Moving{
    public Swim(String Way,int Meter){
        super(Way,Meter);

    }
    public void Way(String Way){
        Way=" SwimMode";
       System.out.println(Way);
       }
       public void Meter(int Meter){
       Meter=1;
       System.out.println(Meter);
       }
       public String toString(){
        return super.toString();
    }
}