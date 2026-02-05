import java.util.List;


// Edo periexei ta dedwmena ekpaideusis kai dokimis

public class IMDBDataset {
    private List<String> trainTexts;
    private List<Integer> trainLabels;
    private List<String> testTexts;
    private List<Integer> testLabels;

    public IMDBDataset(List<String> trainTexts, List<Integer> trainLabels,
                       List<String> testTexts, List<Integer> testLabels) {
        this.trainTexts = trainTexts;
        this.trainLabels = trainLabels;
        this.testTexts = testTexts;
        this.testLabels = testLabels;
    }

    public List<String> getTrainTexts() {
        return trainTexts;
    }

    public List<Integer> getTrainLabels() {
        return trainLabels;
    }

    public List<String> getTestTexts() {
        return testTexts;
    }

    public List<Integer> getTestLabels() {
        return testLabels;
    }
}
