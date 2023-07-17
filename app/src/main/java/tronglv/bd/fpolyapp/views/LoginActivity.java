package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import tronglv.bd.fpolyapp.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout lEdtEmail, lEdtPassword;
    private TextInputEditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapping();

        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && edtEmail.getText().toString().isEmpty()) {
                    lEdtEmail.setHint("Jonedoe@gmail.com");
                }else {
                    lEdtEmail.setHint("");
                }
            }
        });

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && edtEmail.getText().toString().isEmpty()) {
                    lEdtPassword.setHint("*******");
                }else {
                    lEdtPassword.setHint("");
                }
            }
        });
    }

    private void mapping() {
        lEdtEmail = findViewById(R.id.lEdtEmail);
        edtEmail = findViewById(R.id.edtEmail);

        lEdtPassword = findViewById(R.id.lEdtPassword);
        edtPassword = findViewById(R.id.edtPassword);
    }
}