/*
Name:Alexandros Makrygiannis 
Student Number: 3210271
*/

import java.util.Scanner;


public class App4{
public static void main(String[] args){

int x,i1=1,i2=1,i3;
System.out.printf("Enter number:");
Scanner p=new Scanner(System.in);
x=p.nextInt();
while(i1<=x){
    
        i3=i2;
        i2=i1;
        
    
    
System.out.println("Fibonacci number "+i1);

i1=i3+i2;
}
if (i2==x){
System.out.println(x+" "+"is a fibonacci number ");


}else{
    System.out.println(x+" "+"is not a fibonacci number ");
}
}


}