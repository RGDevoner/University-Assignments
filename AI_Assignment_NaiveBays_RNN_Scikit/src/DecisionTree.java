import java.util.*;

public class DecisionTree {

    private class Node {
        boolean isLeaf;
        int featureIndex;   // η θέση του χαρακτηριστικού που χρησιμοποιείται για το split
        Node left, right;   // τα παιδιά (0: όταν το χαρακτηριστικό έχει τιμή 0, 1: όταν έχει τιμή 1)
        int prediction;     // αν είναι leaf αποθηκεύει την πρόβλεψη (0 ή 1)

        public Node(boolean isLeaf, int prediction) {
            this.isLeaf = isLeaf;
            this.prediction = prediction;
        }
    }

    private Node root;
    private int maxDepth;
    private int minSamplesSplit;
    private int numFeaturesToConsider; // για το τυχαίο υποσύνολο χαρακτηριστικών σε κάθε split
    private Random rand = new Random();

    public DecisionTree(int maxDepth, int minSamplesSplit, int numFeaturesToConsider) {
        this.maxDepth = maxDepth;
        this.minSamplesSplit = minSamplesSplit;
        this.numFeaturesToConsider = numFeaturesToConsider;
    }

    // Μέθοδος εκπαίδευσης που δέχεται το feature matrix X και τα αντίστοιχα labels y
    public void train(int[][] X, int[] y) {
        root = buildTree(X, y, 0);
    }

    // Αναδρομική κατασκευή δέντρου
    private Node buildTree(int[][] X, int[] y, int depth) {

        if (isPure(y) || depth >= maxDepth || y.length < minSamplesSplit) {
            int majority = majorityClass(y);
            return new Node(true, majority);
        }

        // Επιλογή τυχαίου υποσυνόλου χαρακτηριστικών για το split
        int numFeatures = X[0].length;
        List<Integer> features = new ArrayList<>();
        for (int i = 0; i < numFeatures; i++) {
            features.add(i);
        }
        Collections.shuffle(features, rand);
        List<Integer> featuresToConsider = features.subList(0, Math.min(numFeaturesToConsider, numFeatures));

        double bestGain = 0;
        int bestFeature = -1;
        for (int feature : featuresToConsider) {
            double gain = informationGain(X, y, feature);
            if (gain > bestGain) {
                bestGain = gain;
                bestFeature = feature;
            }
        }

        // Αν δεν βρέθηκε χρήσιμο split, επιστρέφουμε ένα φύλλο με την πλειοψηφία των labels
        if (bestFeature == -1) {
            int majority = majorityClass(y);
            return new Node(true, majority);
        }

        // Διαχωρισμός του dataset με βάση το καλύτερο χαρακτηριστικό (με binary τιμές)
        List<int[]> leftXList = new ArrayList<>();
        List<Integer> leftYList = new ArrayList<>();
        List<int[]> rightXList = new ArrayList<>();
        List<Integer> rightYList = new ArrayList<>();

        for (int i = 0; i < X.length; i++) {
            if (X[i][bestFeature] == 0) {
                leftXList.add(X[i]);
                leftYList.add(y[i]);
            } else {
                rightXList.add(X[i]);
                rightYList.add(y[i]);
            }
        }

        // Αν ένα από τα splits είναι κενό, επιστρέφουμε φύλλο
        if (leftXList.size() == 0 || rightXList.size() == 0) {
            int majority = majorityClass(y);
            return new Node(true, majority);
        }

        int[][] leftX = leftXList.toArray(new int[leftXList.size()][]);
        int[][] rightX = rightXList.toArray(new int[rightXList.size()][]);
        int[] leftY = leftYList.stream().mapToInt(i -> i).toArray();
        int[] rightY = rightYList.stream().mapToInt(i -> i).toArray();

        Node node = new Node(false, -1);
        node.featureIndex = bestFeature;
        node.left = buildTree(leftX, leftY, depth + 1);
        node.right = buildTree(rightX, rightY, depth + 1);
        return node;
    }

    // Βοηθητικές μέθοδοι

    // Έλεγχος αν όλα τα στοιχεία του πίνακα y έχουν την ίδια τιμή
    private boolean isPure(int[] y) {
        int first = y[0];
        for (int label : y) {
            if (label != first)
                return false;
        }
        return true;
    }

    // Επιστρέφει την πλειοψηφία της κλάσης στα δεδομένα y
    private int majorityClass(int[] y) {
        int count0 = 0, count1 = 0;
        for (int label : y) {
            if (label == 0)
                count0++;
            else
                count1++;
        }
        return count0 >= count1 ? 0 : 1;
    }

    // Υπολογισμός εντροπίας για τα labels y
    private double entropy(int[] y) {
        int count0 = 0, count1 = 0;
        for (int label : y) {
            if (label == 0)
                count0++;
            else
                count1++;
        }
        int total = y.length;
        double p0 = (double) count0 / total;
        double p1 = (double) count1 / total;
        double entropy = 0;
        if (p0 > 0)
            entropy -= p0 * (Math.log(p0) / Math.log(2));
        if (p1 > 0)
            entropy -= p1 * (Math.log(p1) / Math.log(2));
        return entropy;
    }

    // Υπολογισμός Information Gain για το συγκεκριμένο χαρακτηριστικό
    private double informationGain(int[][] X, int[] y, int feature) {
        double baseEntropy = entropy(y);
        List<Integer> leftIndices = new ArrayList<>();
        List<Integer> rightIndices = new ArrayList<>();
        for (int i = 0; i < X.length; i++) {
            if (X[i][feature] == 0)
                leftIndices.add(i);
            else
                rightIndices.add(i);
        }
        if (leftIndices.size() == 0 || rightIndices.size() == 0)
            return 0;

        int[] leftY = new int[leftIndices.size()];
        int[] rightY = new int[rightIndices.size()];
        for (int i = 0; i < leftIndices.size(); i++)
            leftY[i] = y[leftIndices.get(i)];
        for (int i = 0; i < rightIndices.size(); i++)
            rightY[i] = y[rightIndices.get(i)];

        double leftEntropy = entropy(leftY);
        double rightEntropy = entropy(rightY);
        double weightedEntropy = ((double) leftY.length / y.length) * leftEntropy +
                ((double) rightY.length / y.length) * rightEntropy;
        return baseEntropy - weightedEntropy;
    }

    // Μέθοδος πρόβλεψης για ένα δείγμα χαρακτηριστικών x
    public int predict(int[] x) {
        Node node = root;
        while (!node.isLeaf) {
            if (x[node.featureIndex] == 0)
                node = node.left;
            else
                node = node.right;
        }
        return node.prediction;
    }
}
