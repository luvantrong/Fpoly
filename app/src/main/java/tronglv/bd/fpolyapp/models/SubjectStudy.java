package tronglv.bd.fpolyapp.models;

public class SubjectStudy {

    private Number id;
    private String nameSubject;
    private String codeSubject;

    private String formStudy;
    private String time;

    public SubjectStudy() {
    }

    public SubjectStudy(Number id, String nameSubject, String codeSubject, String formStudy, String time) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.codeSubject = codeSubject;
        this.formStudy = formStudy;
        this.time = time;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFormStudy() {
        return formStudy;
    }

    public void setFormStudy(String formStudy) {
        this.formStudy = formStudy;
    }
}
