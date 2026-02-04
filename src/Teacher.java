public class Teacher {
    private String code;
    private String name;
    private Lesson[] lessons;
    private int dailyHours;
    private int weeklyHours;


    Teacher(String code, String name, Lesson[] lessons, int dailyHours, int weeklyHours){
        this.code = code;
        this.name = name;
        this.lessons = lessons;
        this.dailyHours = dailyHours;
        this.weeklyHours = weeklyHours;
    }

    public  String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
    }
    public  Lesson[] getLessons() {
        return lessons;
    }

    public int getLessonsCount() {
        return lessons.length;
    }

    public int getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(int dailyHours) {
        this.dailyHours = dailyHours;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
