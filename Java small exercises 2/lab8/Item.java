/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

class Item {
	
   	private String title;
   	private int playingTime;   
   	private float price; 
   	
    Item() {   	
	}
    
    Item(String theTitle, int time, float price) {
    	this.title = theTitle;
        this.playingTime = time;
        this.price = price;
    }

	void setTitle(String title) {
		this.title = title;
	}
	
    String getTitle() {
        return this.title;
    } 
   
   	int getplayingTime() {
        return this.playingTime;
    }
    
   	public Float getPrice() {
        return this.price;
    }
     
    void setPrice(Float price){
         this.price=price;
    }
    
   	void setplayingTime(int  playingTime) {
         this.playingTime=playingTime;
    }
    
    public String toString() {
        return 	"title: " + getTitle() 
				+ " (" + getplayingTime() + " mins)"
				+ "\nPrice: "+ getPrice();
    }
}