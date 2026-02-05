import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataUtils {

    /**
     * Διαχωρίζει τα features και τα labels σε training και development σύνολα,
     * βάσει του ποσοστού trainFraction (π.χ. 0.8 για 80% training και 20% development).
     */
    public static DataSplitPair splitFeaturesAndLabels(int[][] features, int[] labels, double trainFraction) {
        int n = features.length;
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices, new Random());
        int trainSize = (int) (n * trainFraction);
        int[][] trainFeatures = new int[trainSize][];
        int[] trainLabels = new int[trainSize];
        int[][] devFeatures = new int[n - trainSize][];
        int[] devLabels = new int[n - trainSize];

        for (int i = 0; i < trainSize; i++) {
            int idx = indices.get(i);
            trainFeatures[i] = features[idx];
            trainLabels[i] = labels[idx];
        }
        for (int i = trainSize; i < n; i++) {
            int idx = indices.get(i);
            devFeatures[i - trainSize] = features[idx];
            devLabels[i - trainSize] = labels[idx];
        }
        return new DataSplitPair(trainFeatures, trainLabels, devFeatures, devLabels);
    }
}

