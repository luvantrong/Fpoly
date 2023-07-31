package tronglv.bd.fpolyapp.helpers;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import tronglv.bd.fpolyapp.dto.ResponseUpload;
import tronglv.bd.fpolyapp.dto.ServiceRequestDTO;
import tronglv.bd.fpolyapp.dto.ServiceResponseDTO;


// khai báo các router

public interface IRetrofit {

    @Multipart
    @POST("/api/upload_image.php")
    Call<ResponseUpload> uploadFile(@Part MultipartBody.Part file);

    @POST("/api/insert-service.php")
    Call<ServiceResponseDTO> service(@Body ServiceRequestDTO body);
}
