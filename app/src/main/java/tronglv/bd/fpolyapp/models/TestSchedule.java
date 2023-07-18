package tronglv.bd.fpolyapp.models;

public class TestSchedule {

    private String date;
    private String room;
    private String session;
    private String subject;
    private String code;
    private String time;
    private String teacher;

    public TestSchedule() {
    }

    public TestSchedule(String date, String room, String session, String subject, String code, String time, String teacher) {
        this.date = date;
        this.room = room;
        this.session = session;
        this.subject = subject;
        this.code = code;
        this.time = time;
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
