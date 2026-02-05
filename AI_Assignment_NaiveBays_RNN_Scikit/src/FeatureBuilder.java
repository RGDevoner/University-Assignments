import java.util.*;

public class FeatureBuilder {

    //Edo ginetai enas disdiastatos pinakas gia ta keimena poy einai megethous m+1, oi m theseis einai to binary feature vector(exei 1 an yparxei, 0 diaforetika)
    // kai i teleftaia thesi exei periexei to label 1 gia arnitika h 0 gia thetima reveiws
    //texts = lista keimenwn
    //vocabulary = leksilogio
    //epistefei disdiastaton pinakas haraktirwn

    public static int[][] buildFeatureMatrix(List<String> texts, Map<String, Integer> vocabulary) {
        int m = vocabulary.size();
        int n = texts.size();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            // Edo eksagetai to binary feature vector gia to keimeno
            int[] features = extractFeatures(texts.get(i), vocabulary);
            // Edo Antigrafontai oi xaraktires tis grammis toy pinaka
            System.arraycopy(features, 0, matrix[i], 0, m);
        }
        return matrix;
    }

    private static int[] extractFeatures(String text, Map<String, Integer> vocabulary) {
        int[] features = new int[vocabulary.size()];
        String lowerText = text.toLowerCase();
        //Edo ginontai tokens diaspazontas ta se leksis xrisimopoiontas mh alfarithmitikous xaraktires os diaxoristikes.
        Set<String> wordsInText = new HashSet<>(Arrays.asList(lowerText.split("\\W+")));
        for (String word : wordsInText) {
            if (vocabulary.containsKey(word)) {
                int idx = vocabulary.get(word);
                features[idx] = 1;
            }
        }
        return features;
    }
}