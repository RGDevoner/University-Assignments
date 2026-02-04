import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class DynamicInfluenza_k_withPQ {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect input form!");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]); // Assignment of k
        String inputFilePath = args[1]; // Allocating the input file

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            PriorityQueue<City> cityQueue = new PriorityQueue<>(k);

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] data = line.split("\\s+");

                // Check for the number of fields in the line
                if (data.length != 4) {
                    System.err.println("Error in line " + lineNumber + ": Invalid data format");
                    System.exit(1);
                }

                try {
                    int ID = Integer.parseInt(data[0]);
                    String NAME = data[1];
                    int POPULATION = Integer.parseInt(data[2]);
                    int INFLUENZACASES = Integer.parseInt(data[3]);

                    City city = new City(ID, NAME, POPULATION, INFLUENZACASES);
                    cityQueue.add(city);

                    if (lineNumber % 5 == 0) {
                        printTopCities(cityQueue, k);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error in line " + lineNumber + ": Invalid numeric data");
                    System.exit(1);
                }
            }

        
            
            
            

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void printTopCities(PriorityQueue<City> cityQueue, int k) {
        System.out.println("Top " + k + " cities at this point:");
        int count = 0;
        while (!cityQueue.isEmpty() && count < k) {
            City city = cityQueue.poll();
            System.out.println(city.getName());
            count++;
        }
        
        
    }
}