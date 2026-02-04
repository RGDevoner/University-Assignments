import java.util.*;

public class City implements CityInterface, Comparable<City>{
    private int id;
    private String name;
    private int population;
    private int influenzaCases;
    public City(int id,String name,int population,int influenzaCases){
        this.id=id;
        this.name=name;
        this.population=population;
        this.influenzaCases=influenzaCases;
    }
    
    
    public int getID(){
        return this.id;
    }
   
    public String getName(){
        return this.name;
    }
    
    public int getPopulation(){
        return this.population;
    }
  
    public int getInfluenzaCases(){
        return this.influenzaCases;        
    }
  
    public void setID(int id){
        this.id=id;
    }
  
    public void setName(String name){
        this.name=name;
    }
 
    public void setPopulation(int population){
        this.population=population;
    }
  
    public void setInfluenzaCases(int influenzaCases){
        this.influenzaCases=influenzaCases;
    } 
    public double calculateDensity() {
        
        return ((double) influenzaCases / population) * 50000;
    }
    public int compareTo(City city2) {
        double densityC1 = calculateDensity();
        double densityC2 = city2.calculateDensity();
        if (densityC1 != densityC2) {
            return Double.compare(densityC1, densityC2);
        }
        int CompareName = this.name.compareTo(city2.name);
        if ( CompareName!= 0) {
            return CompareName;
        }
        return Integer.compare(this.id, city2.id);
    }

  
}



