package tronglv.bd.fpolyapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import tronglv.bd.fpolyapp.R;

public class MainActivity extends AppCompatActivity {

    private TextView txtHelloWorld;

    //Google
    GoogleSignInClient gsc;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);

        txtHelloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

    }

    private void mapping() {
        txtHelloWorld = findViewById(R.id.txtHelloWorld);
    }

    private void signOut() {
        if (account != null) {
            gsc.signOut().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            });
        }
    }
}