import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {

    private ArrayList<Chromosome> population;
    private ArrayList<Integer> occurrences;

    private ArrayList<Lesson> lessonsA;
    private ArrayList<Lesson> lessonsB;
    private ArrayList<Lesson> lessonsC;

    GeneticAlgorithm(ArrayList<Lesson> lessonsA,ArrayList<Lesson> lessonsB,ArrayList<Lesson> lessonsC){
        this.population = null;
        this.occurrences = null;

        this.lessonsA = lessonsA;
        this.lessonsB = lessonsB;
        this.lessonsC = lessonsC;
    }
    Chromosome run(int populationSize, double mutationProbability, int maxSteps, int minFitness) {
        this.initializePopulation(populationSize);
        Random r = new Random();
        for (int step = 0; step < maxSteps; step++) {
            ArrayList<Chromosome> newPopulation = new ArrayList<>();
            for (int i = 0; i < populationSize / 2; i++) {
                int xIndex = this.occurrences.get(r.nextInt(this.occurrences.size()));
                Chromosome xParent = this.population.get(xIndex);
                int yIndex = this.occurrences.get(r.nextInt(this.occurrences.size()));
                while (xIndex == yIndex) {
                    yIndex = this.occurrences.get(r.nextInt(this.occurrences.size()));
                }

                Chromosome yParent = this.population.get(yIndex);
                Chromosome[] children = this.reproduce(xParent, yParent);

                if (r.nextDouble() < mutationProbability) {
                    children[0].mutate();
                    children[1].mutate();
                }

                newPopulation.add(children[0]);
                newPopulation.add(children[1]);
            }

            this.population = new ArrayList<>(newPopulation);

            Collections.sort(this.population);

            //System.out.println(this.population.get(0).getFitness());

            if (this.population.get(0).getFitness() <= minFitness)
                return this.population.get(0);
            this.updateOccurrences();
        }
        return this.population.get(0);
    }
    void initializePopulation(int populationSize) {
        this.population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            this.population.add(new Chromosome(lessonsA,lessonsB,lessonsC));
        }
        this.updateOccurrences();
    }
    void updateOccurrences() {
        this.occurrences = new ArrayList<>();
        for (int i = 0; i < this.population.size(); i++) {
            for (int j = 0; j < (160 - this.population.get(i).getFitness());j++) {
                this.occurrences.add(i);
            }
        }
    }
    Chromosome[] reproduce(Chromosome x, Chromosome y) {
        Random r = new Random();
        int intersectionPoint1 = r.nextInt(9);
        int intersectionPoint2 = r.nextInt(35);

        Subject[][] firstChild = new Subject[9][35];
        Subject[][] secondChild = new Subject[9][35];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 35; j++) {
                if (i < intersectionPoint1 || (i == intersectionPoint1 && j < intersectionPoint2)) {
                    firstChild[i][j] = x.getGenes()[i][j];
                    secondChild[i][j] = y.getGenes()[i][j];
                } else {
                    firstChild[i][j] = y.getGenes()[i][j];
                    secondChild[i][j] = x.getGenes()[i][j];
                }
            }
        }
        return new Chromosome[]{new Chromosome(lessonsA,lessonsB,lessonsC,firstChild),new Chromosome(lessonsA,lessonsB,lessonsC,secondChild)};
    }
}