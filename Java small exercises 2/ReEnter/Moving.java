public abstract class Moving{
    protected String Way;
    protected int Meter;
    public Moving(String Way,int Meter){
    this.Way=Way;
    this.Meter=Meter;
    }
    public abstract void Way(String Way);
    public abstract void Meter(int Meter);
    public String toString(){
        return "The Way to walk is "+Way+" And did these meters "+Meter;
    }
}