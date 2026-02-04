/*
	Name: Alexandros Makrygiannis
	Student Number:3210271
*/

import java.util.Scanner;

class Method{
public int number(int n,int plus){
    for(int i=1;i<=n;i++){
        plus=plus*i;
    }
    return plus;
}

}

public class App1{
        public static void main(String[] args){
        int n,plus=1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number n:");
        n=scanner.nextInt();
        Method ob=new Method();
        plus=ob.number(n,plus);
        
        System.out.println("!n is:"+plus);
    }

   
}