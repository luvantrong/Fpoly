package tronglv.bd.fpolyapp.dto;

import java.util.ArrayList;

public class ScheduleGetByIdResponseDTO {
    private boolean status;
    private String message;
    private ArrayList<ListSchedulesResponseDTO.Schedule> schedules;

    public ScheduleGetByIdResponseDTO() {
    }

    public ScheduleGetByIdResponseDTO(boolean status, String message, ArrayList<ListSchedulesResponseDTO.Schedule> schedules) {
        this.status = status;
        this.message = message;
        this.schedules = schedules;
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

    public ArrayList<ListSchedulesResponseDTO.Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<ListSchedulesResponseDTO.Schedule> schedules) {
        this.schedules = schedules;
    }
}
