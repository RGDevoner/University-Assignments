import java.util.Scanner;

public class Test{
    public static void main(String arg[]){
       String n=" ";
       String s=" ";
       String a=" ";
       String c=" ";
        Scanner in=new Scanner(System.in);
        System.out.println("name?");
         n=in.nextLine();
        System.out.println("Subname?");
        s=in.nextLine();
        System.out.println("afm");
         a=in.nextLine();
        System.out.println("code?");
         c=in.nextLine();
         TestPolitis NeosPolitis=new TestPolitis();
    NeosPolitis.Politis(n,s,a,c);
         System.out.println(n+s+a+c);
    
    //System.out.println(NeosPolitis.NeosP);//
    }
}