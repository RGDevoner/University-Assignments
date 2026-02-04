import java.io.*;
import java.util.*;
public class Influenza_k {
    public static void main(String[] args) {
        if (args.length != 2) {
           System.err.println("Incorrect input form !");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]); // ekxorisi toy k
        String inputFilePath = args[1]; // ekxorisi toy txt
       

    try{
   
   String line;
    List<City> cities = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
   
   while((line=reader.readLine()) != null){
    String[] data= line.split("\\s+");
    int ID=Integer.parseInt(data[0]);
    String NAME=data[1];
    int POPULATION=Integer.parseInt(data[2]);
    int INFLUENZACASES=Integer.parseInt(data[3]);
     City city=new City(ID,NAME,POPULATION,INFLUENZACASES);//ftiaxnei antikeimeno city me ta dedomena toy input
     cities.add(city);
    }
    reader.close();
    if (cities.size()<k){
    System.err.println("Error: k is higher than cities !");//to k einai megalitero apo tis polis
    }else{
    heapsort(cities);
    System.out.println("The top " + k + " cities are:");//ektiponei tis k polis me mikrotero pososto krousmaton
    for (int i = 0; i < k; i++) {
        System.out.println(cities.get(i).getName());
    }}
}catch (IOException e){
        e.printStackTrace();
    }
    

 
}
public static void heapsort(List<City> cities) {//heapsort
    int s = cities.size();

    for (int i = s / 2 - 1; i >= 0; i--) {
        heapify(cities, s, i);
    }

    for (int i = s - 1; i >= 0; i--) {
        City temp = cities.get(0);
        cities.set(0, cities.get(i));
        cities.set(i, temp);

        heapify(cities, i, 0);
    }
}

public static void heapify(List<City> cities, int s, int i) {//heapify 
    int heap = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if ((left < s) && (cities.get(left).compareTo(cities.get(heap)) > 0)) {
        heap = left;
    }

    if ((right < s) && (cities.get(right).compareTo(cities.get(heap))) > 0) {
        heap = right;
    }

    if (heap != i) {
        City temp = cities.get(i);
        cities.set(i, cities.get(heap));
        cities.set(heap, temp);

        heapify(cities, s, heap);
    }
}
}

