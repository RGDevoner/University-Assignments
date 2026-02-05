import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ορίστε το baseDir που περιέχει τον φάκελο aclImdb (π.χ. αν η δομή είναι data/aclImdb, τότε baseDir = "data")
        String data = "data";

        try {
            // Φόρτωση δεδομένων
            IMDBDataset dataset = IMDBLoader.loadIMDB(data);
            List<String> trainTexts = dataset.getTrainTexts();
            List<Integer> trainLabelsList = dataset.getTrainLabels();
            List<String> testTexts = dataset.getTestTexts();
            List<Integer> testLabelsList = dataset.getTestLabels();

            // Κατασκευή λεξιλογίου από τα training δεδομένα
            int n = 75;    // Απορρίπτουμε τις πιο συχνές λέξεις
            int k = 1250;    // Απορρίπτουμε τις πιο σπάνιες λέξεις
            int m = 800;    // Μέγεθος λεξιλογίου
            java.util.Map<String, Integer> vocabulary = VocabularyBuilder.buildVocabulary(trainTexts, trainLabelsList, n, k, m);

            // Δημιουργία feature matrix και labels array για τα training και test δεδομένα
            int[][] trainFeatures = FeatureBuilder.buildFeatureMatrix(trainTexts, vocabulary);
            int[] trainLabels = new int[trainLabelsList.size()];
            for (int i = 0; i < trainLabelsList.size(); i++) {
                trainLabels[i] = trainLabelsList.get(i);
            }

            int[][] testFeatures = FeatureBuilder.buildFeatureMatrix(testTexts, vocabulary);
            int[] testLabels = new int[testLabelsList.size()];
            for (int i = 0; i < testLabelsList.size(); i++) {
                testLabels[i] = testLabelsList.get(i);
            }


            // Διαχωρισμός των training δεδομένων σε training (80%) και development (20%)
            DataSplitPair split = DataUtils.splitFeaturesAndLabels(trainFeatures, trainLabels, 0.8);
            int[][] trainSetFeatures = split.trainFeatures;
            int[] trainSetLabels = split.trainLabels;
            int[][] devSetFeatures = split.devFeatures;
            int[] devSetLabels = split.devLabels;


            // Πειράματα Learning Curves:
            // Δοκιμάζουμε διαφορετικά ποσοστά του training set (π.χ., 10%, 20%, 40%, 60%, 80%, 100%)
            double[] fractions = {0.1, 0.2, 0.4, 0.6, 0.8, 1.0};


            // Naive Bayes
            NaiveBayesClassifier nb = new NaiveBayesClassifier(0.1);
            nb.train(trainFeatures, trainLabels);

            System.out.println("Naive Bayes");
            runLearningCurveExperiment4NaiveBayes(fractions,trainSetFeatures,trainSetLabels,devSetFeatures,devSetLabels);
            int[] prediction4NaiveBayes = nb.predict(testFeatures);
            Metrics testMetrics = MetricsCalculator.computeMetrics(testLabels, prediction4NaiveBayes);
            System.out.println(testMetrics.toString());

            // Logistic Regression
            LogisticRegression model = new LogisticRegression(m, 0.1, 0.01, 1000);
            model.train(trainFeatures, trainLabels);

            System.out.println("Logistic Regression");
            runLearningCurveExperiment4LogisticRegression(fractions,trainSetFeatures,trainSetLabels,devSetFeatures,devSetLabels,m);
            int[] prediction4LogisticRegression = model.predict(testFeatures);
            testMetrics = MetricsCalculator.computeMetrics(testLabels, prediction4LogisticRegression);
            System.out.println(testMetrics.toString());

            // Random Forest
            int numTrees = 1000;
            int maxDepth = 10;
            int minSamplesSplit = 5;
            int numFeaturesToConsider = (int) Math.sqrt(m);

            RandomForest forest = new RandomForest(numTrees, maxDepth, minSamplesSplit, numFeaturesToConsider);
            forest.train(trainFeatures, trainLabels);

            System.out.println("Random Forest");
            runLearningCurveExperiment4RandomForest(fractions,trainSetFeatures,trainSetLabels,devSetFeatures,devSetLabels,m);
            int[] prediction4Forest = forest.predict(testFeatures);
            testMetrics = MetricsCalculator.computeMetrics(testLabels, prediction4Forest);
            System.out.println(testMetrics.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void runLearningCurveExperiment4NaiveBayes(double[] fractions, int[][] trainSetFeatures, int[] trainSetLabels,int[][] devSetFeatures, int[] devSetLabels) {
        System.out.println("Fraction\tTrain Micro F1   Dev Micro F1");
        for(double frac: fractions ) {
            int subsetSize = (int)(trainSetFeatures.length * frac);

            // Επιλογή υποσυνόλου από το trainSetFeatures και τα αντίστοιχα labels
            List<int[]> featureList = new ArrayList<>();
            List<Integer> labelList = new ArrayList<>();
            for (int i = 0; i < trainSetFeatures.length; i++) {
                featureList.add(trainSetFeatures[i]);
                labelList.add(trainSetLabels[i]);
            }
            java.util.Collections.shuffle(featureList);
            int[][] trainSubsetFeatures = featureList.subList(0, subsetSize).toArray(new int[subsetSize][]);

            int[] trainSubsetLabels = new int[subsetSize];
            for (int i = 0; i < subsetSize; i++) {
                trainSubsetLabels[i] = labelList.get(i);
            }

            NaiveBayesClassifier nb = new NaiveBayesClassifier(0.1);
            nb.train(trainSubsetFeatures, trainSubsetLabels);

            int[] trainPredictions = nb.predict(trainSubsetFeatures);
            Metrics trainMetrics = MetricsCalculator.computeMetrics(trainSubsetLabels, trainPredictions);


            int[] devPredictions = nb.predict(devSetFeatures);
            Metrics devMetrics = MetricsCalculator.computeMetrics(devSetLabels, devPredictions);


            // Εκτύπωση των αποτελεσμάτων (χρησιμοποιούμε τα micro F1 ως περίληψη)
            System.out.printf("%.2f\t\t%.3f\t\t%.3f\n", frac, trainMetrics.microF1, devMetrics.microF1);
        }
    }

    public static void runLearningCurveExperiment4LogisticRegression(double[] fractions, int[][] trainSetFeatures, int[] trainSetLabels,int[][] devSetFeatures, int[] devSetLabels,int m) {
        System.out.println("Fraction\tTrain Micro F1   Dev Micro F1");
        for(double frac: fractions ) {
            int subsetSize = (int)(trainSetFeatures.length * frac);

            // Επιλογή υποσυνόλου από το trainSetFeatures και τα αντίστοιχα labels
            List<int[]> featureList = new ArrayList<>();
            List<Integer> labelList = new ArrayList<>();
            for (int i = 0; i < trainSetFeatures.length; i++) {
                featureList.add(trainSetFeatures[i]);
                labelList.add(trainSetLabels[i]);
            }
            java.util.Collections.shuffle(featureList);
            int[][] trainSubsetFeatures = featureList.subList(0, subsetSize).toArray(new int[subsetSize][]);

            int[] trainSubsetLabels = new int[subsetSize];
            for (int i = 0; i < subsetSize; i++) {
                trainSubsetLabels[i] = labelList.get(i);
            }

            LogisticRegression model = new LogisticRegression(m, 0.1, 0.01, 1000);
            model.train(trainSubsetFeatures, trainSubsetLabels);

            int[] trainPredictions = model.predict(trainSubsetFeatures);
            Metrics trainMetrics = MetricsCalculator.computeMetrics(trainSubsetLabels, trainPredictions);


            int[] devPredictions = model.predict(devSetFeatures);
            Metrics devMetrics = MetricsCalculator.computeMetrics(devSetLabels, devPredictions);

            // Εκτύπωση των αποτελεσμάτων (χρησιμοποιούμε τα micro F1 ως περίληψη)
            System.out.printf("%.2f\t\t%.3f\t\t%.3f\n", frac, trainMetrics.microF1, devMetrics.microF1);
        }
    }

    public static void runLearningCurveExperiment4RandomForest(double[] fractions, int[][] trainSetFeatures, int[] trainSetLabels,int[][] devSetFeatures, int[] devSetLabels,int m) {
        System.out.println("Fraction\tTrain Micro F1   Dev Micro F1");
        for (double frac : fractions) {
            int subsetSize = (int)(trainSetFeatures.length * frac);

            // Επιλογή υποσυνόλου από το trainSetFeatures και τα αντίστοιχα labels
            List<int[]> featureList = new ArrayList<>();
            List<Integer> labelList = new ArrayList<>();
            for (int i = 0; i < trainSetFeatures.length; i++) {
                featureList.add(trainSetFeatures[i]);
                labelList.add(trainSetLabels[i]);
            }
            java.util.Collections.shuffle(featureList);
            int[][] trainSubsetFeatures = featureList.subList(0, subsetSize).toArray(new int[subsetSize][]);

            int[] trainSubsetLabels = new int[subsetSize];
            for (int i = 0; i < subsetSize; i++) {
                trainSubsetLabels[i] = labelList.get(i);
            }

            // Ορισμός υπερπαραμέτρων για το Random Forest
            int numTrees = 1000;
            int maxDepth = 10;
            int minSamplesSplit = 5;
            int numFeaturesToConsider = (int) Math.sqrt(m);
            RandomForest forest = new RandomForest(numTrees, maxDepth, minSamplesSplit, numFeaturesToConsider);
            forest.train(trainSubsetFeatures, trainSubsetLabels);


            int[] trainPredictions = forest.predict(trainSubsetFeatures);
            Metrics trainMetrics = MetricsCalculator.computeMetrics(trainSubsetLabels, trainPredictions);


            int[] devPredictions = forest.predict(devSetFeatures);
            Metrics devMetrics = MetricsCalculator.computeMetrics(devSetLabels, devPredictions);


            System.out.printf("%.2f\t\t%.3f\t\t%.3f\n", frac, trainMetrics.microF1, devMetrics.microF1);
        }
    }
}

