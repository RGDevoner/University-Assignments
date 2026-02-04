/*
	Name: Alexandros Makrygiannis
	Student Number:3210271
*/

import java.util.Scanner;

public class App2{
public static void main (String[] args){
int Items=0,Sum=0,Neg=0,Pos=0,n,i=0,Av;
int max=1,min=1;
Scanner scanner = new Scanner(System.in);
System.out.println("Give number");
n=scanner.nextInt();
if (n==0){
Items=1;
max=0;
min=0;
}else{

while (n!=0){
    if (n>0){
        Pos=Pos+1;
        i=i+1;
    }
    if (n<0){
        Neg=Neg+1;
        i=i+1;
    }
    if (i==1){
        max=n;
        min=n;
    }else{
        if (n>max){
            max =n;
        }
        if (n<min){
            min =n;
        }
    }
Items=Items+1;
Sum=Sum+n;
System.out.println("Give new number");
n=scanner.nextInt();

}




}
Av=(Sum/Items);
System.out.println("---------------------");
System.out.println("Items:"+Items);
System.out.println("Average:"+Av);
System.out.println("Negative:"+Neg);
System.out.println("Positive:"+Pos);
System.out.println("max:"+ max);
System.out.println("min:"+ min);
System.out.println("---------------------");
}
}