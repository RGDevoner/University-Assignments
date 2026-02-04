import java.sql.Array;
import java.util.*;

public class List{
public static void main(String args[]){

Vector<String> Chroma=new Vector<String>();
String[] ChromaList={"Blue","Black","Red","Yellow","Green","Gray","White","Pink","Olive"};

for(int i=0;ChromaList.length>i;i++){
    Chroma.add(ChromaList[i]);
}

System.out.println(Chroma);
}

}