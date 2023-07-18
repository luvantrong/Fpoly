package tronglv.bd.fpolyapp.models;

public class ProgressStudy {

    private Integer id;
    private String nameSubject;
    private Integer learn;
    private Integer absent;

    private Integer sumLearn;

    public ProgressStudy(Integer id, String nameSubject, Integer learn, Integer absent, Integer sumLearn) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.learn = learn;
        this.absent = absent;
        this.sumLearn = sumLearn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Integer getLearn() {
        return learn;
    }

    public void setLearn(Integer learn) {
        this.learn = learn;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getSumLearn() {
        return sumLearn;
    }

    public void setSumLearn(Integer sumLearn) {
        this.sumLearn = sumLearn;
    }
}
