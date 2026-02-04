/*
	Name: Alexandros Makrygiannis
	Student Number:3210271
*/

import java.util.Scanner;
import java.lang.Math;

public class App3{
public static void main(String[] args){
float a,b,c,D;
Scanner scanner= new Scanner(System.in);
System.out.println("Enter the first number:");
a=scanner.nextFloat();
System.out.println("Enter the second number:");
b=scanner.nextFloat();
System.out.println("Enter the third number:");
c=scanner.nextFloat();
D=(b*b-4*a*c);
if (D<0){
    System.out.println("Enter the first number:"+" "+a);
    System.out.println("Enter the second number:"+" "+b);
    System.out.println("Enter the third number:"+" "+c);
    System.out.println("There are no real values for the quadratic equation");
}else{

double z=Math.pow(D,0.5);
double x1=(b-z)/2*a;
double x2=(b+z)/2*a;
System.out.println("Enter the first number:"+" "+a);
    System.out.println("Enter the second number:"+" "+b);
    System.out.println("Enter the third number:"+" "+c);
   System.out.println("The first solution is :"+"     "+x1);
   System.out.println("The second solution is:"+"     "+x2);
}







}


}
