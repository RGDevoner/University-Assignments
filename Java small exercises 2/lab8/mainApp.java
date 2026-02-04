/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

class mainApp{

	public static void main(String[] args){

		StoreFileR Store = new StoreFileR();

		Store.loadFile("products.txt");

		System.out.println("-----------------------------------------------");

		for (int i=0; i < Store.size();i++)
			System.out.println(Store.get(i));
		
	}
}