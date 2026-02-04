
/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

import java.util.*;

public class NotebookApp {
   
   	public static void main(String args[]) {
   		
     	Scanner in = new Scanner(System.in);     
     		
     	Notebook ok= new Notebook();
     
      	boolean done=false;
      		
      	String answer, note;
 
   	 	while ( ! done)	{
   	 			
   	 			System.out.println("\n1. Add note");
   	 			System.out.println("2. Show all notes");
   	 			System.out.println("3. Show one note");
   	 			System.out.println("4. Remove note");
   	 			System.out.println("0. exit");
   	 			System.out.print("\n> ");
   	 			answer = in.nextLine();
   	 			
   	 			if (answer.equals ("1")){
   	 				System.out.print("New Note? ");
   	 				myBook.storeNote(in.nextLine());
   	 			} 
   	 			else if (answer.equals ("2"))
   	 				myBook.showNotes();
   	 			else if (answer.equals ("3")){
   	 				System.out.print("Number of Note? ");
   	 				myBook.showNote(Integer.parseInt(in.nextLine()));
   	 			}
   	 			else if (answer.equals ("4")){
   	 				System.out.print("Number of Note? ");
   	 				myBook.removeNote(Integer.parseInt(in.nextLine()));
   	 			}
   	 			else if (answer.equals ("0")) done = true;
   	 			
   	 	}	
	}
}
   