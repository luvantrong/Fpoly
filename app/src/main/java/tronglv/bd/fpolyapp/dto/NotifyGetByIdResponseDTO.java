package tronglv.bd.fpolyapp.dto;

import java.util.ArrayList;

public class NotifyGetByIdResponseDTO {

    private boolean status;
    private String message;
    private ArrayList<ListNotifyResponseDTO.Notify> notify;

    public NotifyGetByIdResponseDTO() {
    }

    public NotifyGetByIdResponseDTO(boolean status, String message, ArrayList<ListNotifyResponseDTO.Notify> notify) {
        this.status = status;
        this.message = message;
        this.notify = notify;
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

    public ArrayList<ListNotifyResponseDTO.Notify> getNotify() {
        return notify;
    }

    public void setNotify(ArrayList<ListNotifyResponseDTO.Notify> notify) {
        this.notify = notify;
    }
}
