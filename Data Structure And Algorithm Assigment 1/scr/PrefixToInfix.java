//ACHILLEAS ZOCHIOS p3220273, ALEXANDROS MAKRYGIANNHS p3210271
import java.util.Arrays;
import java.util.Scanner;

public class PrefixToInfix {
   public static void main(String[] args) { //main 
        Scanner scanner = new Scanner(System.in); //scanner
        System.out.print("Enter an arithmetic expression in prefix form (please leave a space between each symbol) : "); 
        String prefix = scanner.nextLine();

        String infixPiece = convertToInfix(prefix);
        String[] check= infixPiece.split(" ");
        if(Arrays.asList(check).contains("(")){
        if (infixPiece != null) {
            System.out.println("Your arithmetic expression in Infix form is : " + infixPiece);
        }}else{
           System.err.println("Invalid prefix expression, please try again. ");
        
        }

        scanner.close();
    }
    public static String convertToInfix(String prefix) {
        String[] PrefixArray = prefix.split(" ");
        StringDoubleEndedQueue s = new StringDoubleEndedQueueImpl();
        int k=PrefixArray.length-1;
        for (int i = k; i >= 0; i=i-1) {
            String value = PrefixArray[i];
            if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
                String n1 = s.removeFirst();
                String n2 = s.removeFirst();
                String infixPiece = "( " + n1 +" "+ value +" "+ n2 + " )";
                s.addFirst(infixPiece);
            } else {
                s.addFirst(value);
            }
        }

        if (s.size() == 1) {
            return s.getFirst();
        } else {
            System.err.println("Invalid prefix expression, please try again. ");
            return null;
        }
    }

    
}