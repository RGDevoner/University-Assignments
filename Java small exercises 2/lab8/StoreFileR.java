/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/


import java.io.*;
import java.util.*;

class StoreFileR {

	private ArrayList <Item> StoreProducts = new ArrayList<Item>();

	void loadFile(String data){
		
		BufferedReader reader= null;
		Item product = null;

		try{
			
			reader = new BufferedReader (new FileReader(data));
			String line = reader.readLine();
			
			while (line!=null){
				StringTokenizer st = new StringTokenizer(line,"#");
				String token = st.nextToken();
				if (token.equals("CD")){
					// CD("Four","Led Zeppelin", 6, 55,23.95);
					product = new CD();     
					product.setTitle(st.nextToken());
					((CD) product).setArtist(st.nextToken());
					((CD) product).setNumberOfTracks(Integer.parseInt(st.nextToken()));
					product.setplayingTime(Integer.parseInt(st.nextToken()));
					product.setPrice(Float.parseFloat(st.nextToken()));
				}
				else if (token.equals("DVD")){
					// DVD#Match Point#woody allen#Jonathan Rhys Meyers#90
					product = new DVD();
					product.setTitle(st.nextToken());  
					((DVD)product).setDirector(st.nextToken());
					((DVD)product).setStar(st.nextToken());
					product.setplayingTime(Integer.parseInt(st.nextToken()));
					product.setPrice(Float.parseFloat(st.nextToken()));
				}

				StoreProducts.add (product);
				line = reader.readLine();
				
			} // while
			
			reader.close();
		} // try
		
		catch (IOException e){
			System.err.println("Error Reading File...");
		}
		
	} // loadFile
	
	Item get(int i){
		return StoreProducts.get(i);
	}


	int size()	{
		return StoreProducts.size();
	}

} // StoreFileR
