package tronglv.bd.fpolyapp.models;

public class News {
    private String title;
    private String poster;
    private String time;

    public News(String title, String poster, String time) {
        this.title = title;
        this.poster = poster;
        this.time = time;
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
}
