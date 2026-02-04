import java.io.*;
import java.util.*;

class Rule {
    List<String> conditions; // Lista me proipotheseis (A1, A2, ..., An)
    String conclusion;       // Sumperasma tou kanona (dld to B)

    public Rule(List<String> conditions, String conclusion) {
        this.conditions = conditions;
        this.conclusion = conclusion;
    }
}

public class BackwardChaining {

    private Set<String> facts = new HashSet<>(); // Lista me alithi gegonota
    private List<Rule> rules = new ArrayList<>(); // Lista me tous kanones

    public void loadFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName)); // Anoigma arxeiou gia anagnwsh
        String line;

        while ((line = br.readLine()) != null) { // Diabasma kathe grammhs
            line = line.trim(); // Afairoume kena
            if (line.startsWith("Fact:")) { // An h gramh ksekinaei me "Fact:", bale to sto sunolo facts
                facts.add(line.substring(5).trim()); // Afairoume to  "Fact:" kai bazoume to gegonos
            } 
            else if (line.contains("->")) { // An h grammh exei "->", tote einai kanonas
                String[] parts = line.split("->"); // Xwrizoume se "proipotheseis -> sumperasma"
                String[] conditions = parts[0].split(","); // Xwrizoume tis proipotheseis me basi to "," giati mporei na exei polles
                List<String> conditionList = new ArrayList<>();
                for (String condition : conditions) { // Bazw kathe proipothesi sthn lista
                    conditionList.add(condition.trim());
                }

                String conclusion = parts[1].trim(); // To sumperasma einai to deksi meros tou kanona
                rules.add(new Rule(conditionList, conclusion)); // Dhmiourgoume enan neo kanona kai ton bazoume sthn lista kanonwn
            }
        }
        br.close(); // Kleise arxeio
    }

    public boolean backwardChaining(String query) {
        if (facts.contains(query)) { // An to erwthma einai hdh gnwsto dld einai sta gegonota, tote epestrepse true
            return true;
        }

        for (Rule rule : rules) { // Eksetash olwn twn kanonwn gia na doume an kapoio sumperasma einai auto pou psaxnoume
            if (rule.conclusion.equals(query)) { // An to sumperasma tou kanona einai zhtoumenos
                boolean allConditionsTrue = true; // As upothesoume oti oles oi proipotheseis einai alhtheis

                for (String condition : rule.conditions) {
                    if (!backwardChaining(condition)) { // An kapoia proipothesi den einai alhthis, aporiptoume ton kanona
                        allConditionsTrue = false;
                        break;
                    }
                }

                if (allConditionsTrue) { // An oles oi proipotheseis einai alithis, to sumperasma isxuei
                    facts.add(query); // Prosthesi sumperasmatos sta gegonota
                    return true; // Erwthma apodeixthike
                }
            }
        }
        return false; // Den brethike
    }

    public static void main(String[] args) throws IOException {
        BackwardChaining bc = new BackwardChaining(); // Dimourgia antikeimenou ths klashs

        bc.loadFile("arxeio.txt"); // Fortwse thn basi gnwshs apo to arxeio

        Scanner scanner = new Scanner(System.in); // Zhthse apo ton xrhsth na balei to erwthma
        System.out.print("Enter query: ");
        String query = scanner.nextLine().trim(); // Diabase kai afairese kena
        boolean result = bc.backwardChaining(query);
        System.out.println("Result: " + result);
    }
}