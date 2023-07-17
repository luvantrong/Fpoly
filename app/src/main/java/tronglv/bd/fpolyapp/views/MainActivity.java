package tronglv.bd.fpolyapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.fragments.ProfileFragment;
import tronglv.bd.fpolyapp.models.User;
import tronglv.bd.fpolyapp.services.BottomService;

public class MainActivity extends AppCompatActivity {

    private FrameLayout flMain;
    private LinearLayout viewHome, viewNotification, viewSchedule, viewProfile;
    private ImageView imgHome, imgNotification, imgSchedule, imgProfile;
    private TextView txtHome, txtNotification, txtSchedule, txtProfile;

    private int selectedTab = 1;

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

    }

    private void mapping() {

        flMain = findViewById(R.id.flMain);

        viewHome = findViewById(R.id.viewHome);
        viewNotification = findViewById(R.id.viewNotification);
        viewSchedule = findViewById(R.id.viewSchedule);
        viewProfile = findViewById(R.id.viewProfile);


        imgHome = findViewById(R.id.imgHome);
        imgNotification = findViewById(R.id.imgNotification);
        imgSchedule = findViewById(R.id.imgSchedule);
        imgProfile = findViewById(R.id.imgProfile);

        txtHome = findViewById(R.id.txtHome);
        txtNotification = findViewById(R.id.txtNotification);
        txtSchedule = findViewById(R.id.txtSchedule);
        txtProfile = findViewById(R.id.txtProfile);
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

    //Thay đổi tab menu bottom
    public void onCLickList(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.viewHome:
                Intent intentHome = new Intent(this, BottomService.class);
                intentHome.setAction(BottomService.BOTTOM_SERVICE_HOME);
                startService(intentHome);
                break;
            case R.id.viewNotification:
                Intent intentNotification = new Intent(this, BottomService.class);
                intentNotification.setAction(BottomService.BOTTOM_SERVICE_NOTIFICATION);
                startService(intentNotification);
                break;
            case R.id.viewSchedule:
                Intent intentSchedule = new Intent(this, BottomService.class);
                intentSchedule.setAction(BottomService.BOTTOM_SERVICE_SCHEDULE);
                startService(intentSchedule);
                break;
            case R.id.viewProfile:
                Intent intentProfile = new Intent(this, BottomService.class);
                intentProfile.setAction(BottomService.BOTTOM_SERVICE_PROFILE);
                startService(intentProfile);
                break;
        }
    }

    //Tín hiệu Bottom Service
    private BroadcastReceiver bottomReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("data");
            switch (result) {
                case "home": {
                    if (selectedTab != 1) {
                        
                        viewHome.setBackgroundResource(R.drawable.background_item_bottomtab);
                        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        imgHome.setImageResource(R.drawable.logo_home_focus);
                        imgNotification.setImageResource(R.drawable.logo_notification);
                        imgSchedule.setImageResource(R.drawable.logo_schedule);
                        imgProfile.setImageResource(R.drawable.logo_profile);

                        txtHome.setVisibility(View.VISIBLE);
                        txtNotification.setVisibility(View.GONE);
                        txtSchedule.setVisibility(View.GONE);
                        txtProfile.setVisibility(View.GONE);
                        

                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        viewHome.startAnimation(scaleAnimation);
                        selectedTab = 1;
                        break;
                    }

                }
                case "notification": {
                    if (selectedTab != 2) {
                        
                        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewNotification.setBackgroundResource(R.drawable.background_item_bottomtab);
                        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        imgHome.setImageResource(R.drawable.logo_home);
                        imgNotification.setImageResource(R.drawable.logo_notification_focus);
                        imgSchedule.setImageResource(R.drawable.logo_schedule);
                        imgProfile.setImageResource(R.drawable.logo_profile);

                        txtHome.setVisibility(View.GONE);
                        txtNotification.setVisibility(View.VISIBLE);
                        txtSchedule.setVisibility(View.GONE);
                        txtProfile.setVisibility(View.GONE);

                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        viewNotification.startAnimation(scaleAnimation);
                        selectedTab = 2;
                        break;
                    }

                }
                case "schedule": {
                    if (selectedTab != 3) {

                        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewSchedule.setBackgroundResource(R.drawable.background_item_bottomtab);
                        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        imgHome.setImageResource(R.drawable.logo_home);
                        imgNotification.setImageResource(R.drawable.logo_notification);
                        imgSchedule.setImageResource(R.drawable.logo_schedule_focus);
                        imgProfile.setImageResource(R.drawable.logo_profile);

                        txtHome.setVisibility(View.GONE);
                        txtNotification.setVisibility(View.GONE);
                        txtSchedule.setVisibility(View.VISIBLE);
                        txtProfile.setVisibility(View.GONE);

                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        viewSchedule.startAnimation(scaleAnimation);
                        selectedTab = 3;
                        break;
                    }
                }
                case "profile": {

                    User user = new User();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flMain, ProfileFragment.newInstance())
                            .commit();

                    if (selectedTab != 4) {
                        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));                        viewProfile.setBackgroundResource(R.drawable.background_item_bottomtab);
                        viewProfile.setBackgroundResource(R.drawable.background_item_bottomtab);

                        imgHome.setImageResource(R.drawable.logo_home);
                        imgNotification.setImageResource(R.drawable.logo_notification);
                        imgSchedule.setImageResource(R.drawable.logo_schedule);
                        imgProfile.setImageResource(R.drawable.logo_profile_focus);

                        txtHome.setVisibility(View.GONE);
                        txtNotification.setVisibility(View.GONE);
                        txtSchedule.setVisibility(View.GONE);
                        txtProfile.setVisibility(View.VISIBLE);


                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        viewProfile.startAnimation(scaleAnimation);
                        selectedTab = 4;
                        break;
                    }
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentBottom = new IntentFilter(BottomService.BOTTOM_SERVICE_EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(bottomReceiver, intentBottom);

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bottomReceiver);
    }
}