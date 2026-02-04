public abstract class PC{
   private  String Logo;
   private String Material;
   void Input(String Logo,String Material){
       this.Logo=Logo;
       this.Material=Material;
       System.out.println(Logo+Material);
   };

    public class HP extends PC{
    String HP(){
        String HP="HP";
        return HP;
    }

    }
    
    }
    