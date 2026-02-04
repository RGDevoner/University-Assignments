
public class main{

    public static void main(String[] args){
       int i=5;
      
       for (int y=0;y<5;y++){
        
        Farm f=new Farm(i);
        i=i+4-y;
    }
}
}