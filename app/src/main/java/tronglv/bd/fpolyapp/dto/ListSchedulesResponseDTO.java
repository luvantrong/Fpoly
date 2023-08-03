package tronglv.bd.fpolyapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ListSchedulesResponseDTO {
    private boolean status;
    private String messages;
    private ArrayList<Schedule> data;

    public ListSchedulesResponseDTO() {
    }

    public ListSchedulesResponseDTO(boolean status, String messages, ArrayList<Schedule> data) {
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public ArrayList<Schedule> getData() {
        return data;
    }

    public void setData(ArrayList<Schedule> data) {
        this.data = data;
    }

    public class Schedule implements Serializable {
            private int id, user_id, type;
            private String room, date, time, course_name ,class_name, teacher_name,address, meet;

        public Schedule() {
        }

        public Schedule(int id, int user_id, int type, String room, String date, String time, String course_name, String class_name, String teacher_name, String address, String meet) {
            this.id = id;
            this.user_id = user_id;
            this.type = type;
            this.room = room;
            this.date = date;
            this.time = time;
            this.course_name = course_name;
            this.class_name = class_name;
            this.teacher_name = teacher_name;
            this.address = address;
            this.meet = meet;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMeet() {
            return meet;
        }

        public void setMeet(String meet) {
            this.meet = meet;
        }
    }
}
