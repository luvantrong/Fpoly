package tronglv.bd.fpolyapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginResponseDTO {
    private boolean status;
    private String message;
    private User user;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(boolean status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
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

    public User  getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class User implements Serializable {
        private int id, status;
        private String email, avatar, student_code, birthday, address, course, semester, name;
        private int gender;

        public User() {
        }

        public User(int id, int status, String email, String avatar, String student_code, String birthday, String address, String course, String semester, int gender, String name) {
            this.id = id;
            this.status = status;
            this.email = email;
            this.avatar = avatar;
            this.student_code = student_code;
            this.birthday = birthday;
            this.address = address;
            this.course = course;
            this.semester = semester;
            this.gender = gender;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getStudent_code() {
            return student_code;
        }

        public void setStudent_code(String student_code) {
            this.student_code = student_code;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}
