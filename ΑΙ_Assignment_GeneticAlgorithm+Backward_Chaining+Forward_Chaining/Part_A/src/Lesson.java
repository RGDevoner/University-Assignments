class Lesson {
    private String code;
    private String name;
    private String grade;
    private int weeklyHours;

    private Teacher teacher;

    Lesson(String code, String name, String grade, int weeklyHours) {
        this.code = code;
        this.name = name;
        this.grade = grade;
        this.weeklyHours = weeklyHours;
    }

    Lesson(String code, String name, String grade, int weeklyHours, Teacher teacher) {
        this.code = code;
        this.name = name;
        this.grade = grade;
        this.weeklyHours = weeklyHours;

        this.teacher = teacher;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }
    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public Teacher getTeacher() {return teacher;}
    public void setTeacher(Teacher teacher) {this.teacher = teacher;}
}
