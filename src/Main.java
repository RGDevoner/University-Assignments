import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Lesson> lessons = parseLessons("lessons.txt");
        ArrayList<Teacher> teachers = parseTeachers("teachers.txt", lessons);

        for(Lesson lesson : lessons) {
            lesson.setTeacher(findTeacher(teachers, lesson));
        }

        ArrayList<Lesson> lessonsGradeA = getLessons(lessons,"A");
        ArrayList<Lesson> lessonsGradeB = getLessons(lessons,"B");
        ArrayList<Lesson> lessonsGradeC = getLessons(lessons,"C");

        GeneticAlgorithm algorithm = new GeneticAlgorithm(lessonsGradeA, lessonsGradeB, lessonsGradeC);
        Chromosome solution = algorithm.run(1000, 0.05, 10000, 0);

        String schedule = createSchedule(lessons,solution);
        try {
            FileWriter writer = new FileWriter("schedule.txt");

            try {
                writer.write(schedule);
                System.out.println("Content written to file successfully");
            } catch (Throwable var13) {
                try {
                    writer.close();
                } catch (Throwable var12) {
                    var13.addSuppressed(var12);
                }

                throw var13;
            }

            writer.close();
        } catch (IOException var14) {
            IOException e = var14;
            e.printStackTrace();
        }

    }
    public static ArrayList<Lesson> parseLessons(String filename) throws IOException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" / ");
                if (parts.length < 4) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    lessons.add(new Lesson(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line (number format): " + line);
                }
            }
        }
        return lessons;
    }

    public static ArrayList<Teacher> parseTeachers(String filename, ArrayList<Lesson> lessons) throws IOException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" / ");
                if (parts.length < 5) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

                String teacherCode = parts[0];
                String teacherName = parts[1];
                String[] lessonCodes = parts[2].split(" , ");
                int dailyHours = Integer.parseInt(parts[3]);
                int weeklyHours = Integer.parseInt(parts[4]);

                Lesson[] teacherLessons = findLessons(lessonCodes, lessons);
                teachers.add(new Teacher(teacherCode, teacherName, teacherLessons, dailyHours, weeklyHours));
            }
        }
        return teachers;
    }
    public static Lesson[] findLessons(String[] lessonData, ArrayList<Lesson> lessons) {
        Lesson[] lessonsCode = new Lesson[lessonData.length];
        for(int i = 0; i < lessonData.length; i++) {
            for (int j = 0; j < lessons.size(); j++) {
                if (lessonData[i].equals(lessons.get(j).getCode())) {
                    lessonsCode[i] = lessons.get(j);
                }
            }
        }
        return lessonsCode;
    }
    public static Teacher findTeacher(ArrayList<Teacher> teachers,Lesson lesson) {
        for(Teacher teacher : teachers){
            for(Lesson lesson1 : teacher.getLessons()){
                if(lesson1.equals(lesson)){
                    return teacher;
                }
            }
        }
        return null;
    }
    public static ArrayList<Lesson> getLessons(ArrayList<Lesson> lessons, String grade) {
        ArrayList<Lesson> newLessons = new ArrayList<>();
        for(Lesson lesson : lessons){
            if(lesson.getGrade().equals(grade)){
                for(int i = 0; i < lesson.getWeeklyHours(); i++){
                    newLessons.add(lesson);
                }
            }
        }
        return newLessons;
    }
    public static String createSchedule(ArrayList<Lesson> lessons, Chromosome solution) {
        StringBuilder output = new StringBuilder();
        Lesson[][] lessonAA = new Lesson[9][35];

        // Γέμισμα του πίνακα lessonAA
        for (Lesson lesson : lessons) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 35; j++) {
                    if (solution.getGenes()[i][j].getCodeLesson().equals(lesson.getCode()))
                        lessonAA[i][j] = lesson;
                }
            }
        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] classes = {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};
        int hoursPerDay = 7;
        int columnWidth = 25;

        output.append("Schedule:\n");
        output.append("-".repeat(10 + (columnWidth + 3) * hoursPerDay)).append("\n");


        for (int i = 0; i < 9; i++) {
            String className = classes[i];
            output.append("Class: ").append(className).append("\n");

            for (int dayIndex = 0; dayIndex < days.length; dayIndex++) {
                String dayName = days[dayIndex];


                output.append(String.format("%-10s |", dayName));

                for (int hour = 0; hour < hoursPerDay; hour++) {
                    int lessonIndex = dayIndex * hoursPerDay + hour;

                    if (lessonAA[i][lessonIndex] != null) {
                        String lessonInfo = String.format("Teacher: %s, %s",
                                lessonAA[i][lessonIndex].getCode(),
                                lessonAA[i][lessonIndex].getTeacher().getName(),
                                lessonAA[i][lessonIndex].getName());
                        output.append(String.format(" %-" + columnWidth + "s |", lessonInfo));
                    } else {
                        output.append(String.format(" %-" + columnWidth + "s |", "No Lesson"));
                    }
                }
                output.append("\n");
            }
            output.append("-".repeat(15 + (columnWidth + 3) * hoursPerDay)).append("\n");
        }

        return output.toString();
    }
}