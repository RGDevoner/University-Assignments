import java.util.*;
import java.util.stream.Collectors;


// Se afto to class kataskevazetai to leksilogio vasi twn etiketwn kai twn keimenwn ekpaidesis,ipologizontais tin sixnotita emfanisis kathe leksis,
// epita aporriptontas tis n pio sixnes kai tis k pio spanies lekseis kai me after poy apomenoyn epilegoume tis m lekseis poy exoyn gia emas
// to megalitero pliroforiako kerdos

public class VocabularyBuilder {


    // Kataskeuazei to leksilogio

    // texts //edo einai i lasta me ta keimena ekpedeusis Λίστα κειμένων εκπαίδευσης.
    //labels //Edo einai i lista epiketwn poy pernoyn timi 0 h 1 analogos gia ta keimena.
    // n //n pio sixnes lekseis poy aporiptoyme.
    //k //k pio spanies leksis poy aporriptoyme.
    // m //megethos leksilogioy opoy aftes oi leksis exoyn to megalitero kerdos pliroforias
    //return //map gia to leksilogio.

    public static Map<String, Integer> buildVocabulary(List<String> texts, List<Integer> labels, int n, int k, int m) {
        Map<String, Integer> docFreq = new HashMap<>();

        Map<String, int[]> docFreqByClass = new HashMap<>(); // Gia index 0 = 0 kai gia index 1 = 1

        int totalDocs = texts.size();
        int countClass0 = 0;
        int countClass1 = 0;


        for (int i = 0; i < texts.size(); i++) {
            String text = texts.get(i).toLowerCase();
            int label = labels.get(i);
            if (label == 0) countClass0++;
            else countClass1++;

            Set<String> words = new HashSet<>(Arrays.asList(text.split("\\W+"))); //Gia na apofigoyne diples emfaniseis mesa sto idio keimeno.
            for (String word : words) {
                if (word.isEmpty()) continue;
                docFreq.put(word, docFreq.getOrDefault(word, 0) + 1);
                int[] counts = docFreqByClass.getOrDefault(word, new int[2]);
                counts[label] += 1;
                docFreqByClass.put(word, counts);
            }
        }

        double p0 = (double) countClass0 / totalDocs;
        double p1 = (double) countClass1 / totalDocs;
        double entropy = 0.0;
        if (p0 > 0) { //Edo ipologizoyme tinsinoliki entropoiia tis klasis
            entropy -= p0 * log2(p0);
        }
        if (p1 > 0) {
            entropy -= p1 * log2(p1);
        }

        // Fthinousa taksinomisi twn leksewn analoga to document frequency
        List<String> wordsSortedByFreq = docFreq.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).map(Map.Entry::getKey).collect(Collectors.toList());

        // Edo aporriptoume tis n kai k lekseis
        Set<String> candidateWords = new HashSet<>(docFreq.keySet());
        if (n > 0 && n < wordsSortedByFreq.size()) {
            List<String> topN = wordsSortedByFreq.subList(0, n);
            candidateWords.removeAll(topN);
        }
        List<String> wordsSortedByFreqAsc = new ArrayList<>(docFreq.keySet());
        wordsSortedByFreqAsc.sort(Comparator.comparingInt(docFreq::get));
        if (k > 0 && k < wordsSortedByFreqAsc.size()) {
            List<String> bottomK = wordsSortedByFreqAsc.subList(0, k);
            candidateWords.removeAll(bottomK);
        }

        //Ypologismos kerdous pliroforias gia kathe upopsifia leksi.
        Map<String, Double> infoGainMap = new HashMap<>();
        for (String word : candidateWords) {
            int df = docFreq.get(word); // Se poses egrafes exei emfanistei i leksi
            double pw = (double) df / totalDocs; //H pithanotita na iparksei i leksi se ena egrafo
            double pNotW = 1 - pw; //Antistrofos, edo ipologizetai i pithanotita na mhn iparxei i leksi se ena eggrafo
            int[] classCounts = docFreqByClass.get(word);
            int df0 = classCounts[0]; // Mia leksi,se posa apo ta reviews ta opoia einai thetika emfanizetai?
            int df1 = classCounts[1]; //Mia leksi,se posa apo ta reviews ta opoia einai arnitika emfanizetai?

            // Entropoiia gia ta egrafa sta opoia iparxei i leksi

            double p0GivenW;
            double p1GivenW;

            if (df > 0) {
                p0GivenW = (double) df0 / df; // Ipologizei tin pithanotita na einai klasi 0 (thetiko) otan df > 0
                p1GivenW = (double) df1 / df; // Ipologizei tin pithanotita na einai klasi 1 (arnitiko) otan df > 0
            } else {
                p0GivenW = 0.0; // An df einai 0, tote to apotelesma einai 0
                p1GivenW = 0.0; // An df einai 0, tote to apotelesma einai 0
            }
            double entropyW = 0;
            if (p0GivenW > 0)
                entropyW -= p0GivenW * log2(p0GivenW);
            if (p1GivenW > 0)
                entropyW -= p1GivenW * log2(p1GivenW);

            // Entropoiia gia ta egrafa sta opoia DEN iparxei i leksi
            int notDf = totalDocs - df;
            int not0 = countClass0 - df0;
            int not1 = countClass1 - df1;
            double p0GivenNotW;
            double p1GivenNotW;

            if (notDf > 0) {
                p0GivenNotW = (double) not0 / notDf; // Ipologizei tin pithanotita na einai klasi 0 otan notDf > 0
                p1GivenNotW = (double) not1 / notDf; // Ipologizei tin pithanotita na einai klasi 1 otan notDf > 0
            } else {
                p0GivenNotW = 0.0; // An notDf einai 0, tote to apotelesma einai 0
                p1GivenNotW = 0.0; // An notDf einai 0, tote to apotelesma einai 0
            }

            double entropyNotW = 0;
            if (p0GivenNotW > 0) entropyNotW -= p0GivenNotW * log2(p0GivenNotW);
            if (p1GivenNotW > 0) entropyNotW -= p1GivenNotW * log2(p1GivenNotW);

            double conditionalEntropy = pw * entropyW + pNotW * entropyNotW;
            double ig = entropy - conditionalEntropy;
            infoGainMap.put(word, ig); //Afto edo einai to map poy periexei tin pliroforia kerdoys
        }

        //Edo ginetai ftinousa taksinomsis gia to to kerdos tis pliroforias twn top m leksewn
        List<String> sortedByIG = infoGainMap.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<String> selectedWords = sortedByIG.stream().limit(m).collect(Collectors.toList()); // Οι m λέξεις που επιλέγονται

        // Edoo dimioyrgite to leksilogio poy sto map exei tin morfi <leksi,index(0/1)>
        Map<String, Integer> vocabulary = new HashMap<>();
        int index = 0;
        for (String word : selectedWords) {
            vocabulary.put(word, index++);
        }
        return vocabulary;
    }

    private static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}

