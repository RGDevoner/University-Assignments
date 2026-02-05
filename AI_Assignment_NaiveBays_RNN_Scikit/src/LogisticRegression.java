import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class LogisticRegression {
    private double[] weights; // Πίνακας βαρών
    private double learningRate; // Ρυθμός μάθησης
    private double lambda; // Συντελεστής ομαλοποίησης
    private int epochs; // Αριθμός επαναλήψεων εκπαίδευσης

    // Κατασκευαστής
    public LogisticRegression(int numFeatures, double learningRate, double lambda, int epochs) {
        this.weights = new double[numFeatures + 1]; // +1 για το bias
        this.learningRate = learningRate;
        this.lambda = lambda;
        this.epochs = epochs;

        // Αρχικοποίηση βαρών με μικρές τυχαίες τιμές
        Random rand = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextDouble() * 0.01; // Μικρές τιμές για καλύτερη σύγκλιση
        }
    }

    // Σιγμοειδής συνάρτηση
    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    // Εκπαίδευση με στοχαστική ανάβαση κλίσης
    public void train(int[][] X, int[] y) {
        int n = X.length; // Αριθμός δειγμάτων
        int m = X[0].length; // Αριθμός χαρακτηριστικών
        Random rand = new Random();

        for (int epoch = 0; epoch < epochs; epoch++) {
            int[] indices = new int[n];
            for (int i = 0; i < n; i++) indices[i] = i;
            shuffleArray(indices, rand); // Σωστό shuffle

            for (int i : indices) {
                double z = weights[0]; // Bias term
                for (int j = 0; j < m; j++) {
                    z += weights[j + 1] * X[i][j];
                }
                double prediction = sigmoid(z);
                double error = y[i] - prediction; // Υπολογισμός σφάλματος

                // Ενημέρωση βαρών
                weights[0] += learningRate * error; // Bias (χωρίς regularization)
                for (int j = 0; j < m; j++) {
                    weights[j + 1] += learningRate * (error * X[i][j] - lambda * weights[j + 1]);
                }
            }
        }
    }

    // Χρήσιμο για να shuffleάρουμε σωστά το array
    private void shuffleArray(int[] array, Random rand) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    // Πρόβλεψη κλάσης για νέο δείγμα
    public int predict(int[] x) {
        double z = weights[0]; // Bias term
        for (int j = 0; j < x.length; j++) {
            z += weights[j + 1] * x[j]; // Υπολογισμός του γραμμικού συνδυασμού
        }
        return sigmoid(z) >= 0.5 ? 1 : 0; // Αν η πιθανότητα είναι >= 0.5, ταξινομείται στην κατηγορία 1
    }

    public int[] predict(int[][] X) {
        int n = X.length;
        int[] predictions = new int[n];
        for (int i = 0; i < n; i++) {
            predictions[i] = predict(X[i]);
        }
        return predictions;
    }
}