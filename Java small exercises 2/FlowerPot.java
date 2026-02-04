public class FlowerPot{
private  Flower flower;

public FlowerPot(Flower f){
    flower=f;
}

public void add(Flower f){
if(flower== null){
    flower=f;
    
    
}else{
    System.out.println("Exei idi louloudi");
}
}
public void remove(){

}
public Flower getFlower(){
return flower;
}
}