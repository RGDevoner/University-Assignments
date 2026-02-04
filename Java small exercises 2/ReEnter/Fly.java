public class Fly extends Moving{
    public Fly(String Way,int Meter){
        super(Way,Meter);

    }
    public void Way(String Way){
     Way="FlyingMode";
     System.out.println(Way);
    }
    public void  Meter(int Meter){
    Meter=0;
    System.out.println(Meter);
    }
    public String toString(){
        return super.toString();
    }
}