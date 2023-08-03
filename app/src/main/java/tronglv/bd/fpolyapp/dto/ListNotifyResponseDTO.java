package tronglv.bd.fpolyapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ListNotifyResponseDTO {

    private boolean status;
    private String message;
    private ArrayList<Notify> data;

    public ListNotifyResponseDTO() {
    }

    public ListNotifyResponseDTO(boolean status, String message, ArrayList<Notify> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Notify> getData() {
        return data;
    }

    public void setData(ArrayList<Notify> data) {
        this.data = data;
    }

    public class Notify implements Serializable {
        private int id, type;
        private String title, content, created_at, poster;

        public Notify() {
        }

        public Notify(int id, int type, String title, String content, String created_at, String poster) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.content = content;
            this.created_at = created_at;
            this.poster = poster;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }
    }
}
