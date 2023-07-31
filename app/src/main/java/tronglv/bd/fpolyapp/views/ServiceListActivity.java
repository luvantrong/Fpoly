package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ListServiceResponseDTO;
import tronglv.bd.fpolyapp.dto.ResponseUpload;
import tronglv.bd.fpolyapp.helpers.IRetrofit;
import tronglv.bd.fpolyapp.helpers.RetrofitHelper;

public class ServiceListActivity extends AppCompatActivity {

    IRetrofit iRetrofit;

    ArrayList<ListServiceResponseDTO.ServiceDTO> listServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_service_list);

        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        listServices = new ArrayList<>();
        iRetrofit.getAllServices().enqueue(getAllServices);
    }

    Callback<ListServiceResponseDTO> getAllServices = new Callback<ListServiceResponseDTO>() {
        @Override
        public void onResponse(Call<ListServiceResponseDTO> call, Response<ListServiceResponseDTO> response) {
            if (response.isSuccessful()) {
                ListServiceResponseDTO responseDTO = response.body();
                if (responseDTO.isStatus()) {
                   listServices.clear();
                   listServices.addAll(responseDTO.getData());
                   Log.d("ABC", listServices.size() +"");
                }
            }
        }

        @Override
        public void onFailure(Call<ListServiceResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };

}