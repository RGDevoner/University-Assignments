import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{
    private ArrayList<Teacher> teachers;

    private ArrayList<Lesson> lessonsA;
    private ArrayList<Lesson> lessonsB;
    private ArrayList<Lesson> lessonsC;

    private Subject[][] genes;

    private int fitness;

    Chromosome(ArrayList<Lesson> lessonsA,ArrayList<Lesson> lessonsB, ArrayList<Lesson> lessonsC) {
        this.genes = new Subject[9][lessonsA.size()];

        this.lessonsA = lessonsA;
        this.lessonsB = lessonsB;
        this.lessonsC = lessonsC;

        this.teachers = new ArrayList<>();
        getTeachers();

        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            days.add(i);
        }

        for(int classroom = 0; classroom < 3; classroom++) {
            Collections.shuffle(days);
            for (int i = 0; i < 35; i++) {
                this.genes[classroom][days.get(i)] = new Subject(lessonsA.get(i).getTeacher().getCode(), lessonsA.get(i).getCode());
            }
        }
        for(int classroom = 3; classroom < 6; classroom++) {
            Collections.shuffle(days);
            for (int i = 0; i < 35; i++) {
                this.genes[classroom][days.get(i)] = new Subject(lessonsB.get(i).getTeacher().getCode(), lessonsB.get(i).getCode());
            }
        }
        for(int classroom = 6; classroom < 9; classroom++) {
            Collections.shuffle(days);
            for (int i = 0; i < 35; i++) {
                this.genes[classroom ][days.get(i)] = new Subject(lessonsC.get(i).getTeacher().getCode(), lessonsC.get(i).getCode());
            }
        }
        this.calculateFitness();
    }
    Chromosome(ArrayList<Lesson> lessonsA,ArrayList<Lesson> lessonsB, ArrayList<Lesson> lessonsC, Subject[][] genes) {
        this.genes = new Subject[9][lessonsA.size()];

        this.lessonsA = lessonsA;
        this.lessonsB = lessonsB;
        this.lessonsC = lessonsC;

        this.teachers = new ArrayList<>();
        getTeachers();

        for(int classroom = 0; classroom < 9; classroom++) {
            for(int hour = 0; hour < 35; hour++) {
                this.genes = genes;
            }
        }
        this.calculateFitness();
    }
    private void getTeachers() {
        for(Lesson lesson : this.lessonsA){
            if(!inArray(lesson.getTeacher()))
                this.teachers.add(lesson.getTeacher());
        }
        for(Lesson lesson : this.lessonsB){
            if(!inArray(lesson.getTeacher()))
                this.teachers.add(lesson.getTeacher());
        }
        for(Lesson lesson : this.lessonsC){
            if(!inArray(lesson.getTeacher()))
                this.teachers.add(lesson.getTeacher());
        }
    }
    private boolean inArray(Teacher teacher) {
        for(Teacher t : this.teachers) {
            if(t.getCode().equals(teacher.getCode()))
                return true;
        }
        return false;
    }


    void calculateFitness() {
        int threats = 0;

        //Περιορισμος 1: Να ολοκληρωνονται οι ωρες του μαθηματος σε μια ημερα
        boolean[][] weeklyHours;
        for(int classroom = 0; classroom < 3; classroom++) {
            for (Lesson lesson : this.lessonsA) {
                if (lesson.getWeeklyHours() > 1) {
                    weeklyHours = getWeeklyHours(lesson, classroom);
                    for (int i = 0; i < 5; i++) {
                        if (sumHours(weeklyHours[i]) == lesson.getWeeklyHours()) {
                            threats++;
                            break;
                        }
                    }
                }
            }
        }
        for(int classroom = 3; classroom < 6; classroom++) {
            for (Lesson lesson : this.lessonsB) {
                if (lesson.getWeeklyHours() > 1) {
                    weeklyHours = getWeeklyHours(lesson, classroom);
                    for (int i = 0; i < 5; i++) {
                        if (sumHours(weeklyHours[i]) == lesson.getWeeklyHours()) {
                            threats++;
                            break;
                        }
                    }
                }
            }
        }
        for(int classroom = 6; classroom < 9; classroom++) {
            for (Lesson lesson : this.lessonsC) {
                if (lesson.getWeeklyHours() > 1) {
                    weeklyHours = getWeeklyHours(lesson, classroom);
                    for (int i = 0; i < 5; i++) {
                        if (sumHours(weeklyHours[i]) == lesson.getWeeklyHours()) {
                            threats++;
                            break;
                        }
                    }
                }
            }
        }
        int[] hours;
        int[] dailyHours;
        int consecutiveHoursCount;
        for(Teacher teacher : this.teachers) {
            hours = getHours(teacher);
        //Περιορισμος 2: Ενας καθηγητης δεν μπορει να διδασκει σε 2 ή περισσοτερες ταξεις ταυτόχρονα
            for (int hour = 0; hour < 35; hour++) {
                if (hours[hour] > 1) {
                    threats += hours[hour] - 1;
                    hours[hour] = 1;
                }
            }
        //Περιορισμος 3: Να μην παραβιαζονται τα dailyhours του καθηγητη
            dailyHours = getDailyHours(hours);
            for (int day = 0; day < 5; day++) {
                if (dailyHours[day] > teacher.getDailyHours()) {
                    threats += dailyHours[day] - teacher.getDailyHours();
                }
            }
        //Περιορισμος 4: Οι δασκαλοι να διδασκουν 3 ωρες (ή παραπανω) συνεχόμενα
            for (int day = 0; day < 5; day++) {
                consecutiveHoursCount = 0;
                for (int hour = 0; hour < 7; hour++) {
                    if (hours[day*7+hour] == 1)
                        consecutiveHoursCount++;
                    else
                        consecutiveHoursCount = 0;
                    if (consecutiveHoursCount > 2) {
                        threats++;
                    }
                }
            }
        }
        this.fitness = threats;
    }
    private boolean[][] getWeeklyHours(Lesson lesson, int classroom) {
        boolean[][] weeklyHours = new boolean[5][7];
        for(int i =  0; i < 5; i++) {
            for(int j =  0; j < 7; j++) {
                weeklyHours[i][j] = false;
            }
        }
        int day;
        int hour;
        for (int i = 0; i < 35; i++) {
            if (this.genes[classroom][i].getCodeLesson().equals(lesson.getCode())) {
                day = i / 7;
                hour = i / 5;
                weeklyHours[day][hour] = true;
            }
        }
        return weeklyHours;
    }
    private int sumHours(boolean[] weeklyHours) {
        int sum = 0;
        for (boolean weeklyHour : weeklyHours) {
            if (weeklyHour)
                sum++;
        }
        return sum;
    }
//Περιορισμοι των Teachers
    private int[] getHours(Teacher teacher){
        int[] hours = new int[35];
        for(int i = 0 ; i < 35 ; i++){
            hours[i] = 0;
        }
        for (int classroom = 0; classroom < 9; classroom++) {//Για καθε μια απο τις 9 ταξεις του genes
            for (int hour = 0; hour < 35; hour++) {//Για καθε ενα προγραμμα αυτης της ταξης
                if (this.genes[classroom][hour].getCodeTeacher().equals(teacher.getCode())) {
                    hours[hour] += 1;
                }
            }
        }
        return hours;
    }
    private int[] getDailyHours(int[] hours){
        int[] dailyHours = {0,0,0,0,0};
        for(int day = 0; day < 5; day++){
            for(int hour = 0; hour < 7; hour++){
                dailyHours[day] += hours[day*7+hour];
            }
        }
        return dailyHours;
    }


    void mutate(){
        Random r = new Random();
        int classroom = r.nextInt(9);
        int hour1 = r.nextInt(35);
        int hour2 = r.nextInt(35);
        while(hour2 == hour1){
            hour2 = r.nextInt(35);
        }

        Subject tempSubject = this.genes[classroom][hour2]; // switch their indexes
        this.genes[classroom][hour2] = this.genes[classroom][hour1];
        this.genes[classroom][hour1] = tempSubject;

        this.calculateFitness();
    }

    public Subject[][] getGenes() {return this.genes;}
    public void setGenes(Subject[][] genes) {this.genes = genes;}

    public int getFitness() {return this.fitness;}
    public void setFitness(int fitness) {this.fitness = fitness;}

    public String toString() {
        String output = "";
        for(int i = 0; i < 9; i++) {
            for(int hour = 0; hour < 35; hour++) {
                output += "|" + this.genes[i][hour].getCodeTeacher() + " " + this.genes[i][hour].getCodeLesson() + "|";
            }
            output += "\n";
        }
        return output;
    }

    @Override
    public int compareTo(Chromosome x)
    {
        return this.fitness - x.fitness;
    }
}
