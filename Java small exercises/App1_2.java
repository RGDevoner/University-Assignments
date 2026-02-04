/*
	Name: Alexandros Makrygiannis
	Student Number:3210271
*/

import java.util.Scanner;

public class App1_2{
        public static void main(String[] args){
        int n,plus=1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number n:");
        n=scanner.nextInt();
        for(int i=1;i<=n;i++){
            plus=plus*i;
        }
        System.out.println("!n is:"+plus);
    }

   
}
