package tronglv.bd.fpolyapp.dto;

public class ResponseUpload {
   private boolean status;
   private String url;
   private String namefile;

    public ResponseUpload() {
    }

    public ResponseUpload(boolean status, String url, String namefile) {
        this.status = status;
        this.url = url;
        this.namefile = namefile;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }
}
