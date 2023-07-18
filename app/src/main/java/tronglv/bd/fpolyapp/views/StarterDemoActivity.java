package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import tronglv.bd.fpolyapp.R;

public class StarterDemoActivity extends AppCompatActivity {

    LinearLayout btnLoginStarter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_demo);

        btnLoginStarter = findViewById(R.id.btnLoginStarter);

        btnLoginStarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(StarterDemoActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
