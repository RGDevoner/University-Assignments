/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

class DVD extends Item{

	private String director;
    private String movieStar;
	
	DVD(){
	}
	
    DVD(String theTitle, String theDirector, String star, int time, float price){
        super(theTitle, time,price);
        this.director = theDirector;
        this.movieStar = star;
    }
   
    String getDirector(){
        return this.director;
    }
     
    void setDirector (String director){
        this.director=director;
    }
    
	String getStar(){
        return this.movieStar;
    } 
    	
    	
    void setStar( String movieStar){
        this.movieStar = movieStar;
    }	
    
    public String toString(){
    	return "DVD details :"
				+ "\ndirector: " + getDirector()
				+ "\nStar : " + getStar()+"\n"
				+ super.toString()
				+ "\n**************************";
    }
}