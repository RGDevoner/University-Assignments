/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

import java.util.Scanner;

class StudentApp {

	public static void main (String args[]) {
	    

	StudentList Lesson= new StudentList ();
		// Fill your code here
		
		for(;;) {
			System.out.println ("1. Insert Student");
			System.out.println ("2. Lookup Student");
			System.out.println ("3. Display List");
			System.out.println ("0. Exit");
			Scannner in=new Scanner(System.in);
			int x=in.next(); 
			if(x==1){
		    System.out.println("Name of student?");
			String name=in.next();
            System.out.println("ID of student?");
			String rn= in.next();
			ystem.out.println("Grade of student?");
			int grade = in.next();
            Student newStudent(String name,String rn,int grade);
            void InsertStudent(Student newStudent);
			}
			if(x==2){
            System.out.println("What's the ID of student?");
			String RN=in.next();
            void LookupStudent(String RN)
			}
			if(x==3){
			void DisplayList();
			
		}
			
			
			// Fill your code here
			
		
		if (x==0)
		break;
	}
		// for
		
	} //main
	
}//StudentApp
