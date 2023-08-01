package tronglv.bd.fpolyapp.helpers;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import tronglv.bd.fpolyapp.dto.ListNotifyResponseDTO;
import tronglv.bd.fpolyapp.dto.ListSchedulesResponseDTO;
import tronglv.bd.fpolyapp.dto.ListServiceResponseDTO;
import tronglv.bd.fpolyapp.dto.LoginRequestDTO;
import tronglv.bd.fpolyapp.dto.LoginResponseDTO;
import tronglv.bd.fpolyapp.dto.NotifyGetByIdResponseDTO;
import tronglv.bd.fpolyapp.dto.ResponseUpload;
import tronglv.bd.fpolyapp.dto.ScheduleGetByIdResponseDTO;
import tronglv.bd.fpolyapp.dto.ServiceRequestDTO;
import tronglv.bd.fpolyapp.dto.ServiceResponseDTO;


// khai báo các router

public interface IRetrofit {

    @Multipart
    @POST("/api/upload_image.php")
    Call<ResponseUpload> uploadFile(@Part MultipartBody.Part file);

    @POST("/api/insert-service.php")
    Call<ServiceResponseDTO> service(@Body ServiceRequestDTO body);

    @GET("/api/get-services.php")
    Call<ListServiceResponseDTO> getAllServices();

    @POST("/api/login.php")
    Call<LoginResponseDTO> login(@Body LoginRequestDTO body);

    @GET("/api/get-schedules.php")
    Call<ListSchedulesResponseDTO> getAllSchedule(@Query("user_id") int user_id, @Query("type") int type);

    @GET("/api/get-schedule-by-id.php")
    Call<ScheduleGetByIdResponseDTO> getSchedulesById(@Query("id") int id);

    @GET("/api/get-posts.php")
    Call<ListNotifyResponseDTO> getAllNotify(@Query("type") int type);

    @GET("/api/get-notify-by-id.php")
    Call<NotifyGetByIdResponseDTO> getNotifyById(@Query("id") int id);

}
