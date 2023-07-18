package tronglv.bd.fpolyapp.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import tronglv.bd.fpolyapp.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout lEdtEmail, lEdtPassword;
    private TextInputEditText edtEmail, edtPassword;

    private Button btnLogin, btnLoginGoogle;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Đăng nhập google
    GoogleSignInClient gsc;
    GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapping();

        showHintEdtLayout();

        //Đăng nhập google

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(LoginActivity.this, gso);
        //Kiểm tra login Google
        account = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if (account != null) {
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }

        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent googleIntent = gsc.getSignInIntent();
                googleLauncher.launch(googleIntent);
            }
        });

    }

    private void mapping() {
        lEdtEmail = findViewById(R.id.lEdtEmail);
        edtEmail = findViewById(R.id.edtEmail);

        lEdtPassword = findViewById(R.id.lEdtPassword);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLoginGoogle = findViewById(R.id.btnLoginGoolge);
    }

    private void showHintEdtLayout() {
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && edtEmail.getText().toString().isEmpty()) {
                    lEdtEmail.setHint("Jonedoe@gmail.com");
                } else {
                    lEdtEmail.setHint("");
                }
            }
        });

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && edtEmail.getText().toString().isEmpty()) {
                    lEdtPassword.setHint("*******");
                } else {
                    lEdtPassword.setHint("");
                }
            }
        });
    }

    ActivityResultLauncher<Intent> googleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String email = account.getEmail();
                        Log.d(">>>TAG", "onActivityResult: " + email);
                        //Chuyển qua màn hình MainActivity
                        if (account != null) {
                            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }
                    } catch (Exception e) {
                        Log.d(">>>TAG", "onActivityResult error: " + e.getMessage());
                    }
                }
            }
    );
}