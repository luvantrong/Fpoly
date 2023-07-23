package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.fragments.ProfileFragment;

public class EditProfile extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToProfile();
    }

    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mapping();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToProfile();
            }
        });

    }

    private void mapping() {
        imgBack = findViewById(R.id.imgBack);
    }

    public void backToProfile() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 5);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}