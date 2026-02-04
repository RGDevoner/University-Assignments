public class Television {
    private String marka;
    private int channels;
    private boolean state;

    public Television(String m,int c){
marka=m;
channels=c;
state=false;
    }
    public void TurnOff(){
        state=false;
    }
    public void TurnOn(){
      state=true;
    }
    public void printState(){
        if(state){
            System.out.println("Its On!");
        }
        else{
            System.out.println("Its Off!");
        }
    }
    public void printStats(){
        System.out.println("Marka:  "+marka+"  Channels:   "+channels);
    }
    public String getMarka() {
        return marka;
    }
    public int getChannels(){
        return channels;
    }
    public void setMarka(String m){
        marka=m;
    }
    public void setChannels(int c){
        channels=c;

    }
    public String toString(){
        return marka+": "+"Channels: "+channels;   
    }
}

