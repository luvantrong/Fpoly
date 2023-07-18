package tronglv.bd.fpolyapp.models;

public class Schedule {

    private String codeCourse;
    private String nameCourse;
    private String day;

    private String address;
    private String room;
    private String time;
    private String className;
    private String teacherName;
    private String slot;

    public Schedule() {

    }

    public Schedule(String codeCourse, String nameCourse,
                    String day, String address, String room,
                    String time, String className, String teacherName, String slot) {
        this.codeCourse = codeCourse;
        this.nameCourse = nameCourse;
        this.day = day;
        this.address = address;
        this.room = room;
        this.time = time;
        this.className = className;
        this.teacherName = teacherName;
        this.slot = slot;
    }

    public String getCodeCourse() {
        return codeCourse;
    }

    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}
