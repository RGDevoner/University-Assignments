
import java.util.*;
public class MTelevision {
    public static void main (String[] args){

        Scanner scanner=new Scanner(System.in);
        String x=scanner.nextLine();
        int y=scanner.nextInt();      
        Television lg=new Television(x,y);
        System.out.println(lg);
        Scanner nextt=new Scanner(System.in);
        String w=nextt.nextLine();
        int z=nextt.nextInt();
        Television sony=new Television(w,z);
        System.out.println(sony);
    if(lg.getChannels()>sony.getChannels()){
    System.out.println(lg.getMarka()+ " einai kaluterh !");
    }
    else if(lg.getChannels()<sony.getChannels()){
 System.out.println(sony.getMarka()+ " einai kaluterh !");
    }
    else{
    System.out.println("einai to idio kales");
    }
}
}