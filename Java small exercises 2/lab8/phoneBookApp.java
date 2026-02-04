/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

import java.util.*;

class phoneBookApp {
	
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);     
		phoneBook myBook = new phoneBook();
		boolean done=false;
		String answer, phone, name, address;
		
		while ( ! done)	{
			
   	 		System.out.println("\n1. Add Phone");
   	 		System.out.println("2. List Phones");
   	 		System.out.println("3. Look up");
   	 		System.out.println("4. Remove");
   	 		System.out.println("5. Change Phone");
   	 		System.out.println("0. exit");
   	 		System.out.print("> ");
   	 		answer = in.nextLine();
			
   	 		if (answer.equals ("1")){
   	 			System.out.print("Name? ");
   	 			name = in.nextLine();
   	 			System.out.print("Phone? ");
   	 			phone = in.nextLine();
   	 			System.out.print("Address? ");
   	 			address = in.nextLine();
   	 			Person ob1 = new Person (name, phone, address);
 				myBook.addPerson(ob1) ;
   	 		} 
			
   	 		else if (answer.equals ("2"))
   	 			myBook.listPersons();
			
   	 		else if (answer.equals ("3")){
   	 			System.out.print("Name to Look up? ");
   	 			name = in.nextLine();
   	 			myBook.lookUp(name);
   	 		}
			
   	 		else if (answer.equals ("4")){
   	 			System.out.print("Name to Remove? ");
   	 			name = in.nextLine();
   	 			myBook.remove(name);
   	 		}  
			
   	 		else if (answer.equals ("5")){
   	 			System.out.print("Name ? ");
   	 			name = in.nextLine();
   	 			System.out.print("New Phone? ");
   	 			phone = in.nextLine();
   	 			myBook.changePhone(name,phone);	
   	 		}
			
			else if (answer.equals ("0")) done = true;
			
   	 	} //while
		
	} // main
	
} // class phoneBookApp
   		