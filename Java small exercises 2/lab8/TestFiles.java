/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

import java.io.*;  

class Test { 

	public static void main(String args[])throws Exception{

		BufferedReader reader= null;
		
      	// open input stream 
		reader = new BufferedReader(new FileReader("products.txt"));
		String line = reader.readLine();
      	    
      	// Reading data from input file
		
		while (line!=null){
          	System.out.println(line);
			line = reader.readLine();
        }    

    }//main  
	
}//Test