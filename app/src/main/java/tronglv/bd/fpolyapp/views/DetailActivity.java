package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ListNotifyResponseDTO;
import tronglv.bd.fpolyapp.models.Notification;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle, txtContent, txtNamePersonPost, txtTimePost;

    ImageView imgBack;

    Integer indexNotify;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToProfile();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mapping();

        ListNotifyResponseDTO.Notify notification = (ListNotifyResponseDTO.Notify) getIntent().getSerializableExtra("notification");

        indexNotify = getIntent().getIntExtra("indexNotify", -1);

        txtTitle.setText(notification.getTitle());
        txtContent.setText(notification.getContent());
        txtNamePersonPost.setText(notification.getPoster());
        txtTimePost.setText(notification.getCreated_at());

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToProfile();
            }
        });
    }

    public void backToProfile() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 2);
        intent.putExtra("indexNotify", indexNotify);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private void mapping() {
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        txtNamePersonPost = findViewById(R.id.txtNamePersonPost);
        txtTimePost = findViewById(R.id.txtTimePost);
        imgBack = findViewById(R.id.imgBack);

    }
}