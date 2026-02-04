public class Flower{

   private String name;
   private int growth;
   public Flower(String n){
       name=n;
   }
   public void water(){
       growth++;
   }
   public String toString(){
       return "Flower's name :"+name+" growth :  "+growth;
   }
   public int getGrowth(){
       return growth;
   }
}