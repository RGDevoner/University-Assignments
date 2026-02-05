import java.util.*;

public class RandomForest {
    private List<DecisionTree> trees;
    private int numTrees;
    private int maxDepth;
    private int minSamplesSplit;
    private int numFeaturesToConsider;
    private Random rand = new Random();

    public RandomForest(int numTrees, int maxDepth, int minSamplesSplit, int numFeaturesToConsider) {
        this.numTrees = numTrees;
        this.maxDepth = maxDepth;
        this.minSamplesSplit = minSamplesSplit;
        this.numFeaturesToConsider = numFeaturesToConsider;
        trees = new ArrayList<>();
    }

    // Εκπαίδευση του Random Forest: δημιουργούμε numTrees δέντρα, το καθένα εκπαιδεύεται σε bootstrap δείγμα
    public void train(int[][] X, int[] y) {
        int n = X.length;
        for (int i = 0; i < numTrees; i++) { //θα τρέχει για κάθε δένδρο που θέλουμε να δημιουργήσουμε
            int[][] sampleX = new int[n][]; //Bootstrap δείγματος
            int[] sampleY = new int[n];
            for (int j = 0; j < n; j++) {
                int index = rand.nextInt(n);
                sampleX[j] = X[index];
                sampleY[j] = y[index];
            }
            DecisionTree tree = new DecisionTree(maxDepth, minSamplesSplit, numFeaturesToConsider);
            tree.train(sampleX, sampleY);
            trees.add(tree);
        }
    }

    // Πρόβλεψη για ένα δείγμα x με ψηφοφορία πλειοψηφίας
    public int predict(int[] x) {
        int votes0 = 0;
        int votes1 = 0;
        for (DecisionTree tree : trees) {
            int pred = tree.predict(x);
            if (pred == 0)
                votes0++;
            else
                votes1++;
        }
        return votes0 >= votes1 ? 0 : 1;
    }

    // Πρόβλεψη για ένα σύνολο δειγμάτων
    public int[] predict(int[][] X) {
        int[] predictions = new int[X.length];
        for (int i = 0; i < X.length; i++) {
            predictions[i] = predict(X[i]);
        }
        return predictions;
    }
}
