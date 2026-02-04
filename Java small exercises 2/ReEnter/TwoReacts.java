
import java.util.Scanner;

public class TwoReacts{
    static Moving Movement;
   
    public static void main(String args[]){
       
        Scanner in=new Scanner(System.in);
        
        

   
        System.out.println("Swimming or flying?");
        String Way=in.next();
       System.out.println("Meters?");
       int Meter=in.nextInt();
        if( Way=="flying"){
         Movement=new Fly(Way,Meter);
         Movement.Way(Way);
         Movement.Meter(Meter);
        }
        else{
         Movement=new Swim(Way,Meter);
         Movement.Way(Way);
         Movement.Meter(Meter);
        }
        System.out.println(Movement);
        System.out.println("Continue?");
        
        
    
    }
    
    }
