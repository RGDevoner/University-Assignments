import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IMDBLoader {

    public static IMDBDataset loadIMDB(String DATA) throws IOException {
        List<String> trainTexts = new ArrayList<>();
        List<Integer> trainLabels = new ArrayList<>();
        List<String> testTexts = new ArrayList<>();
        List<Integer> testLabels = new ArrayList<>();

        File trainPosDir = new File(DATA + File.separator + "aclImdb" + File.separator + "train" + File.separator + "pos"); //Pernei to directory tou fakelou pos tou train folder
        File trainNegDir = new File(DATA + File.separator + "aclImdb" + File.separator + "train" + File.separator + "neg"); //Pernei to directory tou fakelou neg tou train folder
        File testPosDir = new File(DATA + File.separator + "aclImdb" + File.separator + "test" + File.separator + "pos"); //Pernei to directory tou fakelou pos tou test folder
        File testNegDir = new File(DATA + File.separator + "aclImdb" + File.separator + "test" + File.separator + "neg"); //Pernei to directory tou fakelou neg tou test folder

        // Edo fortonoume ta thetika keimena ekpaideysis
        for (File file : trainPosDir.listFiles()) {
            if (file.isFile()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                trainTexts.add(content);
                trainLabels.add(0);
            }
        }
        // Edo fortonoume ta arnitika keimena ekpaideysis
        for (File file : trainNegDir.listFiles()) {
            if (file.isFile()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                trainTexts.add(content);
                trainLabels.add(1);
            }
        }

        //Edo fortonoume ta thetika keimena dokimis
        for (File file : testPosDir.listFiles()) {
            if (file.isFile()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                testTexts.add(content);
                testLabels.add(0);
            }
        }
        //Edo fortonoume ta arnitika keimena dokimis
        for (File file : testNegDir.listFiles()) {
            if (file.isFile()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                testTexts.add(content);
                testLabels.add(1);
            }
        }

        return new IMDBDataset(trainTexts, trainLabels, testTexts, testLabels); //Epistrefei to Dataset
    }
}

