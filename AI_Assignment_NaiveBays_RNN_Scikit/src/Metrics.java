public class Metrics {
    public double precision0;
    public double recall0;
    public double f10;

    public double precision1;
    public double recall1;
    public double f11;

    public double microPrecision;
    public double microRecall;
    public double microF1;

    public double macroPrecision;
    public double macroRecall;
    public double macroF1;

    @Override
    public String toString() {
        return String.format("Class 0 -> Precision: %.3f, Recall: %.3f, F1: %.3f\n" +
                        "Class 1 -> Precision: %.3f, Recall: %.3f, F1: %.3f\n" +
                        "Micro Avg -> Precision: %.3f, Recall: %.3f, F1: %.3f\n" +
                        "Macro Avg -> Precision: %.3f, Recall: %.3f, F1: %.3f\n",
                precision0, recall0, f10,
                precision1, recall1, f11,
                microPrecision, microRecall, microF1,
                macroPrecision, macroRecall, macroF1);
    }
}

