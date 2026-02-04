
/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

import java.util.*;

public class phoneBook {
	
	private ArrayList <Person> persons = new ArrayList <Person>();     
	Scanner in = new Scanner(System.in);
	String ans;
			
	public void addPerson(Person p){
		persons.add (p);
	}
            
	public void listPersons () {
		for (Person p:persons)
			System.out.println (p);			
		System.out.println ("\nNumber of entries : " + persons.size());
	}	
            	
	public void lookUp (String theName) {
		for (Person p:persons)
			if (p.getName().contains(theName))
				System.out.println (p);
	}	
           	
	public void remove (String theName) {
		for (int i=persons.size()-1; i>=0; i--)
			if (persons.get(i).getName().contains(theName)){
				System.out.print(persons.get(i));
				System.out.print("Remove ? (y/n): ");
				ans = in.nextLine();
				if (ans.equals("y")) {
					persons.remove(i);
					System.out.println("Removed ...");
				}
				else System.out.println("Skipped ...");
			}
	}	    
            	
	public void changePhone (String theName, String newPhone) {
		boolean found = false;
        for (Person p:persons)
			if (p.getName().equals(theName)) {
				found = true;
				p.setPhone(newPhone);
				System.out.println ("Phone has been change!");						
		}
		if (!found) System.out.println ("Name not found!");
	}	

} // class phoneBook
