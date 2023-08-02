package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.adapters.NotificationAdapter;
import tronglv.bd.fpolyapp.adapters.ServiceAdapter;
import tronglv.bd.fpolyapp.dto.ListServiceResponseDTO;
import tronglv.bd.fpolyapp.dto.ResponseUpload;
import tronglv.bd.fpolyapp.helpers.IRetrofit;
import tronglv.bd.fpolyapp.helpers.RetrofitHelper;
import tronglv.bd.fpolyapp.services.BottomService;

public class ServiceListActivity extends AppCompatActivity {

    IRetrofit iRetrofit;

    RecyclerView rvService;

    ImageView imgBack;

    ArrayList<ListServiceResponseDTO.ServiceDTO> listServices;

    ServiceAdapter adapter;

    int user_id = -1;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        imgBack = findViewById(R.id.imgBack);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        user_id = sharedPreferences.getInt("id", -1);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToService();
            }
        });

        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        listServices = new ArrayList<>();
        rvService = findViewById(R.id.rvService);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ServiceListActivity.this);
        rvService.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(ServiceListActivity.this, listServices);
        rvService.setAdapter(adapter);

    }

    Callback<ListServiceResponseDTO> getAllServices = new Callback<ListServiceResponseDTO>() {
        @Override
        public void onResponse(Call<ListServiceResponseDTO> call, Response<ListServiceResponseDTO> response) {
            if (response.isSuccessful()) {
                ListServiceResponseDTO responseDTO = response.body();
                if (responseDTO.isStatus()) {
                    listServices.clear();
                    listServices.addAll(responseDTO.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onFailure(Call<ListServiceResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };

    public void backToService() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 4);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iRetrofit.getAllServices(user_id).enqueue(getAllServices);
    }

}