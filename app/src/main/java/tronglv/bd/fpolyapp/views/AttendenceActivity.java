package tronglv.bd.fpolyapp.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ResponseUpload;
import tronglv.bd.fpolyapp.dto.ServiceRequestDTO;
import tronglv.bd.fpolyapp.dto.ServiceResponseDTO;
import tronglv.bd.fpolyapp.helpers.IRetrofit;
import tronglv.bd.fpolyapp.helpers.RetrofitHelper;

public class AttendenceActivity extends AppCompatActivity {

    private ImageView imgBack, imgCalendar;

    private String name_service = "Khôi phục điểm danh";
    private int user_id = -1;
    private TextInputEditText edtCourse, edtClass, edtDate, edtTeacher, edtPhone, edtDescription;
    private String file = "";

    private TextView txtFile, txtNameFile;

    private Button btnRegister;

    final int REQUESTCODE_READ_EXTERNAL_STORAGE = 120;
    IRetrofit iRetrofit;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear, mMonth, mDay;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        mapping();

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        user_id = sharedPreferences.getInt("id", -1);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToService();
            }
        });

        txtFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isPermissionAllowed = ActivityCompat.checkSelfPermission(
                        AttendenceActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (isPermissionAllowed) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    selectCapture.launch(intent);
                } else {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUESTCODE_READ_EXTERNAL_STORAGE);
                }
            }
        });

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(AttendenceActivity.this,
                        0, chooseDate, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code_course = edtCourse.getText().toString();
                String class_name = edtClass.getText().toString();
                String day = edtDate.getText().toString();
                String teacher = edtTeacher.getText().toString();
                String phone = edtPhone.getText().toString();
                String reason = edtDescription.getText().toString();

                if(code_course.isEmpty() || class_name.isEmpty() || day.isEmpty() || teacher.isEmpty()
                || phone.isEmpty() || reason.isEmpty() || file.length() == 0) {
                    Toast.makeText(AttendenceActivity.this, "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                ServiceRequestDTO serviceResponseDTO = new ServiceRequestDTO(name_service, code_course, class_name, day, teacher, phone, reason, file, user_id);
                iRetrofit.service(serviceResponseDTO).enqueue(serviceResponseDTOCallback);
            }
        });
    }

    private void mapping() {
        imgBack = findViewById(R.id.imgBack);
        edtCourse = findViewById(R.id.edtCourse);
        edtClass = findViewById(R.id.edtClass);
        edtDate = findViewById(R.id.edtDate);
        edtTeacher = findViewById(R.id.edtTeacher);
        edtPhone = findViewById(R.id.edtPhone);
        edtDescription = findViewById(R.id.edtDescription);
        txtNameFile = findViewById(R.id.txtNameFile);
        txtFile = findViewById(R.id.txtFile);
        btnRegister = findViewById(R.id.btnRegister);
        imgCalendar = findViewById(R.id.imgCalendar);
    }

    public void backToService() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 4);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    ActivityResultLauncher<Intent> selectCapture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    Uri uri = intent.getData();

                    try (InputStream inputStream = getContentResolver().openInputStream(uri)) {
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        String filePath = cursor.getString(column_index);
                        cursor.close();
                        File file = new File(filePath);
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                        iRetrofit.uploadFile(body).enqueue(uploadImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Callback<ResponseUpload> uploadImage = new Callback<ResponseUpload>() {
                    @Override
                    public void onResponse(Call<ResponseUpload> call, Response<ResponseUpload> response) {
                        if (response.isSuccessful()) {
                            ResponseUpload upload = response.body();
                            if (upload.isStatus()) {
                                txtNameFile.setText(upload.getNamefile());
                                file = "http://10.0.2.2:6789/" + upload.getUrl();
                                Log.d("file", file);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUpload> call, Throwable t) {
                        Log.d(">>> login", "onFailure: " + t.getMessage());
                    }
                };


            }
    );

    DatePickerDialog.OnDateSetListener chooseDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edtDate.setText(sdf.format(c.getTime()));
        }
    };

    Callback<ServiceResponseDTO> serviceResponseDTOCallback = new Callback<ServiceResponseDTO>() {
        @Override
        public void onResponse(Call<ServiceResponseDTO> call, Response<ServiceResponseDTO> response) {
            if (response.isSuccessful()) {
                ServiceResponseDTO serviceResponseDTO = response.body();
                if (serviceResponseDTO.isStatus()) {
                    Toast.makeText(AttendenceActivity.this, "Đăng ký dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AttendenceActivity.this, ServiceListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                    finish();
                } else {
                    Toast.makeText(AttendenceActivity.this, "Đăng ký dịch vụ không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ServiceResponseDTO> call, Throwable t) {
            Log.d(">>> service", "onFailure: " + t.getMessage());
        }
    };

}