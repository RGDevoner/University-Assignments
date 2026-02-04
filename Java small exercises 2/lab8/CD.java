/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

class CD extends Item{

    private String artist;
    private int numberOfTracks;

	CD(){
		
	}
    
    CD(String theTitle, String theArtist, int tracks, int time, float price){
        super(theTitle, time,price);
        this.artist = theArtist;
        this.numberOfTracks = tracks;
    }

    String getArtist(){
        return this.artist;
    }
 
 	void setArtist(String artist){
        this.artist = artist;
    }
    
    int getNumberOfTracks(){
        return this.numberOfTracks;
    }

 	void setNumberOfTracks (int numberOfTracks){
        this.numberOfTracks = numberOfTracks;
    }
   
    public String toString(){
    	return 	"CD details :"
				+"\nArtist : " + getArtist()
				+"\nNumber of tracks: " +getNumberOfTracks()
				+"\n"
				+super.toString()
				+"\n**************************";
    }
}