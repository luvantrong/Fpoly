package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.fragments.ProfileFragment;

public class EditProfile extends AppCompatActivity {

    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imgBack = findViewById(R.id.imgBack);


    }
}