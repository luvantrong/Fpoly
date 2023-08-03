package tronglv.bd.fpolyapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProgressResponseDTO {

    private boolean status;
    private String message;
    private ArrayList<Progress> data;

    public ListProgressResponseDTO() {
    }

    public ListProgressResponseDTO(boolean status, String message, ArrayList<Progress> data) {
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

    public ArrayList<Progress> getData() {
        return data;
    }

    public void setData(ArrayList<Progress> data) {
        this.data = data;
    }

    public class Progress implements Serializable {
        private int id, sum, absent, learn, user_id;
        private String name, code, form;

        public Progress() {
        }

        public Progress(int id, int sum, int absent, int learn, int user_id, String name, String code, String form) {
            this.id = id;
            this.sum = sum;
            this.absent = absent;
            this.learn = learn;
            this.user_id = user_id;
            this.name = name;
            this.code = code;
            this.form = form;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getAbsent() {
            return absent;
        }

        public void setAbsent(int absent) {
            this.absent = absent;
        }

        public int getLearn() {
            return learn;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }
    }
}
