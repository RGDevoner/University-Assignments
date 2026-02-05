public class DataSplitPair {
    public int[][] trainFeatures;
    public int[] trainLabels;
    public int[][] devFeatures;
    public int[] devLabels;

    public DataSplitPair(int[][] trainFeatures, int[] trainLabels, int[][] devFeatures, int[] devLabels) {
        this.trainFeatures = trainFeatures;
        this.trainLabels = trainLabels;
        this.devFeatures = devFeatures;
        this.devLabels = devLabels;
    }
}
