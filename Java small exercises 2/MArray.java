import java.util.Scanner;
public class MArray{
    
    public static void main(String[] args){
    String[] array=new String[10];
    Scanner in=new Scanner(System.in);
    for (int i=0;i<array.length;i++){
    System.out.println("Give 2 char");
        String x=in.nextLine();
        String y=in.nextLine();
    array[i]=(String x,String y);
    }
    for(int i=0;i<array.length;i++){
        System.out.println("array: "+array[i]);
    }
}
}