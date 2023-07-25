package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tronglv.bd.fpolyapp.R;

public class AttendenceActivity extends AppCompatActivity {

    private ImageView imgBack;


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

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToService();
            }
        });
    }

    private void mapping() {
        imgBack = findViewById(R.id.imgBack);
    }

    public void backToService() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 4);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}