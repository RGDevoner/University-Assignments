//ACHILLEAS ZOCHIOS p3220273, ALEXANDROS MAKRYGIANNHS p3210271
import java.util.Scanner;

public class DNAPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Type your DNA combination: ");
        String DNA = scanner.nextLine();
        DNAcheck(DNA);
        scanner.close();
    }
public static void DNAcheck(String DNA){
    StringDoubleEndedQueue p = new StringDoubleEndedQueueImpl();
    char[] ch = DNA.toCharArray();
    String[] DNAlist = new String[ch.length];
    for (int i = 0; i < ch.length; i++) {
        DNAlist[i] = String.valueOf(ch[i]);
        for(int s=0;s<DNAlist.length;s++){
            p.addLast(DNAlist[s]);
        }

    }
        if(DNAlist.length%2==1){
            System.err.println("\n"+ DNA+" is not a wotson-crick complemented palindrome(error:odd number of nucleotides) ");
        }else{
            int k=(DNAlist.length/2);
            int head=0;
            int tail=DNAlist.length-1;
            for(int i=0;k>i;i++){
            if((DNAlist[head].equals("A") && DNAlist[tail].equals("T"))||(DNAlist[head].equals("T") && DNAlist[tail].equals("A"))||(DNAlist[head].equals("C") && DNAlist[tail].equals("G"))||(DNAlist[head].equals("G") && DNAlist[tail].equals("C"))){
                for(int l=0;l<DNAlist.length;l++){
                p.removeFirst();
                p.removeLast();
                }
            head++;
            tail--;
            
        }

            }

            if(p.size()==0){
                System.out.println("\n" + DNA  +" is a wotson-crick complemented palindrome");
            }else{
                System.err.println("\n" + DNA  +" is not a wotson-crick complemented palindrome(error:incorrect nucleotides combination, wrong nucleotides characters or  has spaces between(' 'or'_''))");
            }
        }
}}
        
        

