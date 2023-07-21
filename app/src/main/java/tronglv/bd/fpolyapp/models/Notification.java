package tronglv.bd.fpolyapp.models;

import java.io.Serializable;

public class Notification implements Serializable {
    private String title;
    private String poster;
    private String time;
    private String content;

    public Notification() {
    }

    public Notification(String title, String poster, String time, String content) {
        this.title = title;
        this.poster = poster;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

