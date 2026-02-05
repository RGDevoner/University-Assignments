public class MetricsCalculator {
    public static Metrics computeMetrics(int[] trueLabels, int[] predictedLabels) {
        Metrics metrics = new Metrics();
        int tp0 = 0, fp0 = 0, fn0 = 0;
        int tp1 = 0, fp1 = 0, fn1 = 0;
        int n = trueLabels.length;

        for (int i = 0; i < n; i++) {
            int trueLabel = trueLabels[i];
            int pred = predictedLabels[i];

            // Υπολογισμός για κατηγορία 0
            if (pred == 0) {
                if (trueLabel == 0) tp0++;
                else fn0++;
            } else { // pred == 1
                if (trueLabel == 0) fp0++;  // False Positive for class 0
                else tp1++;
            }

            // Υπολογισμός για κατηγορία 1
            if (pred == 1) {
                if (trueLabel == 1) tp1++;
                else fn1++;
            } else { // pred == 0
                if (trueLabel == 1) fp1++;  // False Positive for class 1
                else tp0++;
            }
        }

        // Υπολογισμός μετρικών για την κατηγορία 0 και 1 (Precision, Recall, F1)
        metrics.precision0 = (tp0 + fp0) > 0 ? (double) tp0 / (tp0 + fp0) : 0;
        metrics.recall0 = (tp0 + fn0) > 0 ? (double) tp0 / (tp0 + fn0) : 0;
        metrics.f10 = (metrics.precision0 + metrics.recall0) > 0 ? 2 * metrics.precision0 * metrics.recall0 / (metrics.precision0 + metrics.recall0) : 0;

        metrics.precision1 = (tp1 + fp1) > 0 ? (double) tp1 / (tp1 + fp1) : 0;
        metrics.recall1 = (tp1 + fn1) > 0 ? (double) tp1 / (tp1 + fn1) : 0;
        metrics.f11 = (metrics.precision1 + metrics.recall1) > 0 ? 2 * metrics.precision1 * metrics.recall1 / (metrics.precision1 + metrics.recall1) : 0;

        // Υπολογισμός των μικρών και μακρών μέσων
        int totalTP = tp0 + tp1;
        int totalFP = fp0 + fp1;
        int totalFN = fn0 + fn1;
        metrics.microPrecision = (totalTP + totalFP) > 0 ? (double) totalTP / (totalTP + totalFP) : 0;
        metrics.microRecall = (totalTP + totalFN) > 0 ? (double) totalTP / (totalTP + totalFN) : 0;
        metrics.microF1 = (metrics.microPrecision + metrics.microRecall) > 0 ? 2 * metrics.microPrecision * metrics.microRecall / (metrics.microPrecision + metrics.microRecall) : 0;

        metrics.macroPrecision = (metrics.precision0 + metrics.precision1) / 2.0;
        metrics.macroRecall = (metrics.recall0 + metrics.recall1) / 2.0;
        metrics.macroF1 = (metrics.f10 + metrics.f11) / 2.0;

        return metrics;
    }

}