import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.*;


public class FileReader{
    public static void main(String args[]){
      String[] names={"John","Alex","Bill"};
      
        try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("Pes.txt"));
        writer.write("I don't want a lot for Christmas There is just one thing I need");
        writer.write("\n2nd line YOOOOOOO");
        writer.write("\nStronder Gamiesaiiiiiiii");
        
        for (String name :names){
            writer.write("\n"+name);
        }
        writer.close();
      }catch (IOException e){
          e.printStackTrace();
      }
      try{
          BufferedReader reader=new BufferedReader(new FileReader("Pes.txt"));
          String line;
          
          while((line=reader.readLine()) !=null){
          System.out.println(line);
          reader.close();
        }
          
      }catch(IOException e){
          e.printStackTrace();
      }


    }
}