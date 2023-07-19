package tronglv.bd.fpolyapp.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.adapters.BasisAdapter;
import tronglv.bd.fpolyapp.adapters.ProgressStudyAdapter;

public class LoginActivity extends AppCompatActivity {

    private Button btnLoginGoogle;

    private TextView txtBasis;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String basisSelected = "";

    //Đăng nhập google
    GoogleSignInClient gsc;
    GoogleSignInAccount account;

    List<String> basis = new ArrayList<>();

    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapping();

        basis.add("FPT Polytechnic Hồ Chí Minh");
        basis.add("FPT Polytechnic Cần Thơ");
        basis.add("FPT Polytechnic Đà Nẵng");
        basis.add("FPT Polytechnic Tây Nguyên");
        basis.add("FPT Polytechnic Hà Nội");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(R.layout.dialog_select_basis, null);
        builder.setView(customLayout);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.background_dialog_basis);
        RecyclerView rvBasis = customLayout.findViewById(R.id.rvBasis);
        RecyclerView.LayoutManager layoutManagerProgress = new LinearLayoutManager(this);
        rvBasis.setLayoutManager(layoutManagerProgress);
        BasisAdapter basisAdapter = new BasisAdapter(this, basis);
        rvBasis.setAdapter(basisAdapter);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(drawable);
        alertDialog.setCanceledOnTouchOutside(false);
        txtBasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                alertDialog.getWindow().setLayout(710, 650);
            }
        });

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
            overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
            finish();
        }

        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(basisSelected.length() == 0){
                    Toast.makeText(LoginActivity.this, "Bạn chưa chọn cơ sở", Toast.LENGTH_SHORT).show();
                }else {
                    Intent googleIntent = gsc.getSignInIntent();
                    googleLauncher.launch(googleIntent);
                }
            }
        });
    }

    private void mapping() {
        btnLoginGoogle = findViewById(R.id.btnLoginGoolge);
        txtBasis = findViewById(R.id.txtBasis);
    }

    public void setTextBasis(String text) {
        txtBasis.setText(text);
        basisSelected = text;
        alertDialog.dismiss();
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
                            overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                            finish();
                        }
                    } catch (Exception e) {
                        Log.d(">>>TAG", "onActivityResult error: " + e.getMessage());
                    }
                }
            }
    );
}