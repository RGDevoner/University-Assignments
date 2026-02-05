public class NaiveBayesClassifier {

    private int numFeatures;
    private double[] classPriors;         // Pithanotis gia kathe klasi 0 h 1
    private double[][] featureProb;       // featureProb[c][j] = P(x_j = 1 | class = c)
    private double alpha;                 // Parametros omalopoiisis
    public NaiveBayesClassifier(double alpha) {
        this.alpha = alpha;
    }


    //Edo ekpedeutetai o taksinomitis.
    //X //disdiastatos pinakas xaraktirwn
    // y// pinkas etiketwn ( 0 h 1)

    public void train(int[][] X, int[] y) {
        int n = X.length;
        numFeatures = X[0].length;
        int count0 = 0, count1 = 0;
        // Edo einai oi pinakes poy katametrane tin emfanisi kathe xaraktiristikoy gia kathe klasi
        int[] featureCount0 = new int[numFeatures];
        int[] featureCount1 = new int[numFeatures];

        for (int i = 0; i < n; i++) {
            if (y[i] == 0) {
                count0++;
                for (int j = 0; j < numFeatures; j++) {
                    if (X[i][j] == 1) {
                        featureCount0[j]++;
                    }
                }
            } else {
                count1++;
                for (int j = 0; j < numFeatures; j++) {
                    if (X[i][j] == 1) {
                        featureCount1[j]++;
                    }
                }
            }
        }

        // Edo ipologizontai oi prior pithanotites
        classPriors = new double[2];
        classPriors[0] = (double) count0 / n;
        classPriors[1] = (double) count1 / n;

        //Edo genetai o ipologismos me laplace smoothing twn pithanotitwn emfanisis kathe leksis dedomenis tis klasis.
        featureProb = new double[2][numFeatures];
        // Gia kathe xaraktiristiko j(tipos Bayes):
        // P(x_j=1|c) = (count + alpha) / (num_docs_in_class + 2*alpha)
        for (int j = 0; j < numFeatures; j++) {
            featureProb[0][j] = (featureCount0[j] + alpha) / (count0 + 2 * alpha);
            featureProb[1][j] = (featureCount1[j] + alpha) / (count1 + 2 * alpha);
        }
    }


    ///Provlepsi etiketas gia ena paradeigma

    public int predict(int[] x) {
        double[] logProb = new double[2];
        for (int c = 0; c < 2; c++) {
            logProb[c] = Math.log(classPriors[c]);
            for (int j = 0; j < numFeatures; j++) {
                if (x[j] == 1) {
                    logProb[c] += Math.log(featureProb[c][j]);
                } else {
                    logProb[c] += Math.log(1 - featureProb[c][j]);
                }
            }
        }
        if (logProb[0] >= logProb[1]) {
            return 0;
        } else {
            return 1;
        }
    }


    //Provlepsi etiketas gia polla paradeigmata


    public int[] predict(int[][] X) {
        int n = X.length;
        int[] predictions = new int[n];
        for (int i = 0; i < n; i++) {
            predictions[i] = predict(X[i]);
        }
        return predictions;
    }
}

