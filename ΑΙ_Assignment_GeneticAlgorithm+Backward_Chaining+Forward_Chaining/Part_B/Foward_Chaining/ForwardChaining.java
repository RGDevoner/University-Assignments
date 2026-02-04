import java.io.*;
import java.util.*;

public class ForwardChaining {
    public static void main(String[] args) {
        try {

        
            String FileKnowledge = "knowledge_base.txt";     //onoma vasis gnoseos
            System.out.println("Fortosi tis vaseis gnoseos apo to arxeio :  " + FileKnowledge);
            System.out.println("...");
            List<String> facts = new ArrayList<>();
            List<Rule> rules = loadKnowledgeBase(FileKnowledge, facts);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Dose ton pros apodeixi typo:");
            String query = scanner.nextLine().trim();
            boolean result = performForwardChaining(rules, facts, query);

            if (result== true) {
                System.out.println("Alithes, O typos " + query + " apodeixthike.");
            } else {
                System.out.println("Pseudes, O typos " + query + " den apodeixthike.");
            }
        } catch (IOException e) {
            System.err.println("Yparxei provlima me to arxeio: " + e.getMessage());
        }
    }

   
    static class Rule { // Klasi pou anaparista enan kanona Horn
        private final List<String> conditions; // Oi proypotheseis tou kanona
        private final String conclusion;      // To symperasma tou kanona

        Rule(List<String> conditions, String conclusion) {
            this.conditions = new ArrayList<>(conditions);
            this.conclusion = conclusion;
        }

        public List<String> getConditions() {
            return conditions;
        }

        public String getConclusion() {
            return conclusion;
        }
    }

  

    public static List<Rule> loadKnowledgeBase(String fileName, List<String> facts) throws IOException {  // Methodos gia na fortosoume ti vasi gnoseon 
        List<Rule> rules = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("FACT:")) {
                    facts.add(line.substring(5).trim()); // Prosthetoume ena gegonos sti lista
                } else if (line.startsWith("RULE:")) {
                    
                    String[] parts = line.substring(5).split("=>"); 
                    List<String> conditions = Arrays.asList(parts[0].trim().split("&"));
                    String conclusion = parts[1].trim();
                    rules.add(new Rule(conditions, conclusion)); // Prosthetoume enan kanona sti lista
                }
            }
        }
        return rules;
    }


    
    public static boolean performForwardChaining(List<Rule> rules, List<String> facts, String query) {  // Methodos gia na ektelesoume to forward chaining
        List<String> conclusions = new ArrayList<>();; // Simperasmata pou exoume idi kanei
        List<String> ForwardChainingQueue = new ArrayList<>(facts); // Oura gia epexergasia gegonoton

        while (!ForwardChainingQueue.isEmpty()) {
            String fact = ForwardChainingQueue.remove(0);
            
            if (fact.equals(query)) {// An isxyei
                return true;
            }
            for (Rule rule : rules) {
                if (!conclusions.contains(rule.getConclusion()) && facts.containsAll(rule.getConditions())) {
                    // Prosthetoume to symperasma sta gegonota
                    facts.add(rule.getConclusion());
                    ForwardChainingQueue.add(rule.getConclusion());
                    conclusions.add(rule.getConclusion());
                }
            }
        }
        return false; //an ftasei edo, den mporese na apodeixei to zitoumeno
    }

   
    
}