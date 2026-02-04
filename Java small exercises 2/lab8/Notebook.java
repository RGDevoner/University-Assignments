

/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

import java.util.ArrayList;

class Notebook {
	
    private ArrayList<String> notes= new ArrayList<String>();

    void storeNote(String note){
        notes.add(note);
    }

    int numberOfNotes(){
        return notes.size();
    }

    void showNotes(){
		int n = 0;
        for(String note : notes) 
            System.out.println("["+ n++ + "] " + note);
    }
    
    void showNote(int noteNumber){
        if (noteNumber >= 0 && noteNumber < numberOfNotes())
            System.out.println("["+ noteNumber + "] " + notes.get(noteNumber));   
		else System.out.println ("Note not found");
    }
    
    void removeNote(int noteNumber){
        if (noteNumber >= 0 && noteNumber < numberOfNotes()) {
            notes.remove(noteNumber);
			System.out.println ("Note deleted!");
		}
		else System.out.println ("Note not found!");
    }
	
} // class Notebook

