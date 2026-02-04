import java.util.Scanner;
public class MFlower{
    
    private static  Flower anafora;
    public static void main(String[] args){
    
    int i;
    Scanner in=new Scanner(System.in);
    String fl=in.nextLine();    
    Flower flower=new Flower(fl);
    
    for(i=0;i<9;i++){
    flower.water();
    }
    
    System.out.println(flower);
   
 
}
}