package tronglv.bd.fpolyapp.dto;

import java.util.ArrayList;

public class ListServiceResponseDTO {
    private boolean status;
    private ArrayList<ServiceDTO> data;

    public ListServiceResponseDTO() {
    }

    public ListServiceResponseDTO(boolean status, ArrayList<ServiceDTO> data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<ServiceDTO> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceDTO> data) {
        this.data = data;
    }

    public class ServiceDTO {
        private int id, user_id;
        private String name_service, code_course, class_name, day, teacher, phone, reason, file;

        public ServiceDTO() {
        }

        public ServiceDTO(int id, int user_id, String name_service, String code_course, String class_name, String day, String teacher, String phone, String reason, String file) {
            this.id = id;
            this.user_id = user_id;
            this.name_service = name_service;
            this.code_course = code_course;
            this.class_name = class_name;
            this.day = day;
            this.teacher = teacher;
            this.phone = phone;
            this.reason = reason;
            this.file = file;
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

        public String getName_service() {
            return name_service;
        }

        public void setName_service(String name_service) {
            this.name_service = name_service;
        }

        public String getCode_course() {
            return code_course;
        }

        public void setCode_course(String code_course) {
            this.code_course = code_course;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}
