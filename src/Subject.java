class Subject {
    private String codeTeacher;
    private String codeLesson;


    Subject(String codeTeacher,String codeLesson) {
        this.codeLesson = codeLesson;
        this.codeTeacher = codeTeacher;
    }

    public String getCodeTeacher() {return codeTeacher;}
    public void setCodeTeacher(String codeTeacher) {this.codeTeacher = codeTeacher;}

    public String getCodeLesson() {
        return codeLesson;
    }
    public void setCodeLesson(String codeLesson) {
        this.codeLesson = codeLesson;
    }

}