package tronglv.bd.fpolyapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ListNotifyResponseDTO;
import tronglv.bd.fpolyapp.dto.ListProgressResponseDTO;
import tronglv.bd.fpolyapp.dto.ListSchedulesResponseDTO;
import tronglv.bd.fpolyapp.dto.LoginResponseDTO;
import tronglv.bd.fpolyapp.dto.NotifyGetByIdResponseDTO;
import tronglv.bd.fpolyapp.dto.ScheduleGetByIdResponseDTO;
import tronglv.bd.fpolyapp.fragments.NewsFragment;
import tronglv.bd.fpolyapp.fragments.NotificationFragment;
import tronglv.bd.fpolyapp.fragments.NotificationPlusFragment;
import tronglv.bd.fpolyapp.fragments.ProfileFragment;
import tronglv.bd.fpolyapp.fragments.SchedulePlusFragment;
import tronglv.bd.fpolyapp.fragments.ServiceFragment;
import tronglv.bd.fpolyapp.fragments.StudyFragment;
import tronglv.bd.fpolyapp.fragments.TutionFragment;
import tronglv.bd.fpolyapp.helpers.IRetrofit;
import tronglv.bd.fpolyapp.helpers.RetrofitHelper;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.ProgressStudy;
import tronglv.bd.fpolyapp.models.SubjectStudy;
import tronglv.bd.fpolyapp.services.BottomService;

public class MainActivity extends AppCompatActivity {

    private FrameLayout flMain;
    private LinearLayout viewHome, viewNotification, viewSchedule, viewProfile, viewService, bottomBar;
    private ImageView imgHome, imgNotification, imgSchedule, imgProfile, imgService;
    private TextView txtHome, txtNotification, txtSchedule, txtProfile, txtService, txtCancelSignOut;

    private RelativeLayout rlSignOut, rlViewSignOut;

    private Button btnSignOutMain;

    private int selectedTab = 1;

    private Integer indexNotify;
    //Google
    GoogleSignInClient gsc;
    GoogleSignInAccount account;

    IRetrofit iRetrofit;
    ArrayList<ListSchedulesResponseDTO.Schedule> listSchedule;
    ArrayList<ListSchedulesResponseDTO.Schedule> listTestSchedule;
    ArrayList<ListSchedulesResponseDTO.Schedule> listScheduleById;
    ArrayList<ListNotifyResponseDTO.Notify> listNoifys;
    ArrayList<ListNotifyResponseDTO.Notify> listNews;
    ArrayList<ListNotifyResponseDTO.Notify> listTutions;

    ArrayList<ListProgressResponseDTO.Progress> listProgress;


    Intent intentNotify;


    int user_id = -1;

    private LoginResponseDTO.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        intentNotify = new Intent(MainActivity.this, DetailActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        int _id = sharedPreferences.getInt("id", -1);
        int status = sharedPreferences.getInt("status", -1);
        String email = sharedPreferences.getString("email", "");
        String avatar = sharedPreferences.getString("avatar", "");
        String student_code = sharedPreferences.getString("student_code", "");
        String birthday = sharedPreferences.getString("birthday", "");
        String address = sharedPreferences.getString("address", "");
        String course = sharedPreferences.getString("course", "");
        String semester = sharedPreferences.getString("semester", "");
        String name = sharedPreferences.getString("name", "");
        int gender = sharedPreferences.getInt("gender", -1);

        user = new LoginResponseDTO.User(_id, status,email, avatar, student_code, birthday, address, course, semester, gender, name);

        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        listSchedule = new ArrayList<>();
        listTestSchedule = new ArrayList<>();
        listScheduleById = new ArrayList<>();
        listNoifys = new ArrayList<>();
        listNews = new ArrayList<>();
        listTutions = new ArrayList<>();
        listProgress = new ArrayList<>();

        txtCancelSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSignOut();
            }
        });

        btnSignOutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);

        Integer index = getIntent().getIntExtra("index", 1);
        selectedTab = index;

        indexNotify = getIntent().getIntExtra("indexNotify", -1);



        if (selectedTab == 2) {
            setSelectedTab2();
            loadNotification();
        }

        if (selectedTab == 4) {
            setSelectedTab4();
            loadService();
        }

        if (selectedTab == 5) {
            setSelectedTab5();
            loadProfile();
        }

    }

    public Integer indexNotify() {
        return indexNotify;
    }

    private void mapping() {

        flMain = findViewById(R.id.flMain);

        viewHome = findViewById(R.id.viewHome);
        viewNotification = findViewById(R.id.viewNotification);
        viewSchedule = findViewById(R.id.viewSchedule);
        viewService = findViewById(R.id.viewService);
        viewProfile = findViewById(R.id.viewProfile);
        bottomBar = findViewById(R.id.bottomBar);


        imgHome = findViewById(R.id.imgHome);
        imgNotification = findViewById(R.id.imgNotification);
        imgSchedule = findViewById(R.id.imgSchedule);
        imgProfile = findViewById(R.id.imgProfile);
        imgService = findViewById(R.id.imgService);

        txtHome = findViewById(R.id.txtHome);
        txtNotification = findViewById(R.id.txtNotification);
        txtSchedule = findViewById(R.id.txtSchedule);
        txtProfile = findViewById(R.id.txtProfile);
        txtService = findViewById(R.id.txtService);
        txtCancelSignOut = findViewById(R.id.txtCancelSignOut);

        btnSignOutMain = findViewById(R.id.btnSignOutMain);
        rlSignOut = findViewById(R.id.rlSignOut);
        rlViewSignOut = findViewById(R.id.rlViewSignOut);

    }

    private void setSelectedTab1() {
        viewHome.setBackgroundResource(R.drawable.background_item_bottomtab);
        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewService.setBackgroundColor(getResources().getColor(android.R.color.transparent));


        imgHome.setImageResource(R.drawable.logo_home_focus);
        imgNotification.setImageResource(R.drawable.logo_notification);
        imgSchedule.setImageResource(R.drawable.logo_schedule);
        imgProfile.setImageResource(R.drawable.logo_profile);
        imgService.setImageResource(R.drawable.logo_service);


        txtHome.setVisibility(View.VISIBLE);
        txtNotification.setVisibility(View.GONE);
        txtSchedule.setVisibility(View.GONE);
        txtProfile.setVisibility(View.GONE);
        txtService.setVisibility(View.GONE);


//                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        viewHome.setPivotX(viewHome.getWidth() / 2f);
        viewHome.setPivotY(viewHome.getHeight() / 2f);
        viewHome.startAnimation(scaleAnimation);
        viewHome.setVisibility(View.VISIBLE);
    }

    private void setSelectedTab2() {
        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewNotification.setBackgroundResource(R.drawable.background_item_bottomtab);
        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewService.setBackgroundColor(getResources().getColor(android.R.color.transparent));


        imgHome.setImageResource(R.drawable.logo_home);
        imgNotification.setImageResource(R.drawable.logo_notification_focus);
        imgSchedule.setImageResource(R.drawable.logo_schedule);
        imgProfile.setImageResource(R.drawable.logo_profile);
        imgService.setImageResource(R.drawable.logo_service);


        txtHome.setVisibility(View.GONE);
        txtNotification.setVisibility(View.VISIBLE);
        txtSchedule.setVisibility(View.GONE);
        txtProfile.setVisibility(View.GONE);
        txtService.setVisibility(View.GONE);


//                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//                        scaleAnimation.setDuration(200);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        viewNotification.setPivotX(viewNotification.getWidth() / 2f);
        viewNotification.setPivotY(viewNotification.getHeight() / 2f);
        viewNotification.startAnimation(scaleAnimation);
        viewNotification.setVisibility(View.VISIBLE);
    }

    private void setSelectedTab3() {
        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewSchedule.setBackgroundResource(R.drawable.background_item_bottomtab);
        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewService.setBackgroundColor(getResources().getColor(android.R.color.transparent));


        imgHome.setImageResource(R.drawable.logo_home);
        imgNotification.setImageResource(R.drawable.logo_notification);
        imgSchedule.setImageResource(R.drawable.logo_schedule_focus);
        imgProfile.setImageResource(R.drawable.logo_profile);
        imgService.setImageResource(R.drawable.logo_service);

        txtHome.setVisibility(View.GONE);
        txtNotification.setVisibility(View.GONE);
        txtSchedule.setVisibility(View.VISIBLE);
        txtProfile.setVisibility(View.GONE);
        txtService.setVisibility(View.GONE);

//                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//                        scaleAnimation.setDuration(200);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        viewSchedule.setPivotX(viewSchedule.getWidth() / 2f);
        viewSchedule.setPivotY(viewSchedule.getHeight() / 2f);
        viewSchedule.startAnimation(scaleAnimation);
        viewSchedule.setVisibility(View.VISIBLE);
    }

    private void setSelectedTab4() {
        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewService.setBackgroundResource(R.drawable.background_item_bottomtab);
        viewProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));


        imgHome.setImageResource(R.drawable.logo_home);
        imgNotification.setImageResource(R.drawable.logo_notification);
        imgSchedule.setImageResource(R.drawable.logo_schedule);
        imgProfile.setImageResource(R.drawable.logo_profile);
        imgService.setImageResource(R.drawable.logo_service_select);

        txtHome.setVisibility(View.GONE);
        txtNotification.setVisibility(View.GONE);
        txtSchedule.setVisibility(View.GONE);
        txtProfile.setVisibility(View.GONE);
        txtService.setVisibility(View.VISIBLE);

//                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//                        scaleAnimation.setDuration(200);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        viewService.setPivotX(viewService.getWidth() / 2f);
        viewService.setPivotY(viewService.getHeight() / 2f);
        viewService.startAnimation(scaleAnimation);
        viewService.setVisibility(View.VISIBLE);
    }


    private void setSelectedTab5() {
        viewHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewNotification.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewSchedule.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        viewProfile.setBackgroundResource(R.drawable.background_item_bottomtab);
        viewProfile.setBackgroundResource(R.drawable.background_item_bottomtab);
        viewService.setBackgroundColor(getResources().getColor(android.R.color.transparent));


        imgHome.setImageResource(R.drawable.logo_home);
        imgNotification.setImageResource(R.drawable.logo_notification);
        imgSchedule.setImageResource(R.drawable.logo_schedule);
        imgProfile.setImageResource(R.drawable.logo_profile_focus);
        imgService.setImageResource(R.drawable.logo_service);

        txtHome.setVisibility(View.GONE);
        txtNotification.setVisibility(View.GONE);
        txtSchedule.setVisibility(View.GONE);
        txtService.setVisibility(View.GONE);
        txtProfile.setVisibility(View.VISIBLE);


//                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//                        scaleAnimation.setDuration(200);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        viewProfile.setPivotX(viewProfile.getWidth() / 2f);
        viewProfile.setPivotY(viewProfile.getHeight() / 2f);
        viewProfile.startAnimation(scaleAnimation);
        viewProfile.setVisibility(View.VISIBLE);
    }

    public void signOut() {
        if (account != null) {
            gsc.signOut().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(homeIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    finish();
                }
            });
            rlSignOut.setVisibility(View.GONE);
            bottomBar.setVisibility(View.VISIBLE);
        }
    }

    public void showSignOut() {
        bottomBar.setVisibility(View.GONE);
        rlSignOut.setVisibility(View.VISIBLE);
        rlViewSignOut.setVisibility(View.VISIBLE);
        rlViewSignOut.setAlpha(1);
        rlSignOut.setAlpha(0.7f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f
        );
        scaleAnimation.setDuration(500);
        rlViewSignOut.setPivotX(viewHome.getWidth() / 2f);
        rlViewSignOut.setPivotY(viewHome.getHeight() / 2f);
        rlViewSignOut.startAnimation(scaleAnimation);
    }

    private void hideSignOut() {
        rlSignOut.setVisibility(View.GONE);
        rlViewSignOut.setVisibility(View.GONE);
        bottomBar.setVisibility(View.VISIBLE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.0f,
                1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(300);
        rlViewSignOut.startAnimation(scaleAnimation);
    }

    public void showMenuNotify(LinearLayout ln, FrameLayout fr) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ln.setVisibility(View.VISIBLE);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                ScaleAnimation anim = new ScaleAnimation(
                        0, // fromXScale
                        1, // toXScale
                        1, // fromYScale
                        1, // toYScale
                        Animation.RELATIVE_TO_SELF, 0, // pivotX
                        Animation.RELATIVE_TO_SELF, 0 // pivotY
                );
                anim.setDuration(700);
                ln.startAnimation(anim);
            }
        }, 1000);

        loadNotifyFragment(fr);

    }

    public void showMenuNotify2(LinearLayout ln, FrameLayout fr) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ln.setVisibility(View.VISIBLE);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                ScaleAnimation anim = new ScaleAnimation(
                        0, // fromXScale
                        1, // toXScale
                        1, // fromYScale
                        1, // toYScale
                        Animation.RELATIVE_TO_SELF, 0, // pivotX
                        Animation.RELATIVE_TO_SELF, 0 // pivotY
                );
                anim.setDuration(700);
                ln.startAnimation(anim);
            }
        }, 1000);
        loadNewsFragment(fr);
    }

    public void showMenuNotify3(LinearLayout ln, FrameLayout fr) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ln.setVisibility(View.VISIBLE);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                ScaleAnimation anim = new ScaleAnimation(
                        0, // fromXScale
                        1, // toXScale
                        1, // fromYScale
                        1, // toYScale
                        Animation.RELATIVE_TO_SELF, 0, // pivotX
                        Animation.RELATIVE_TO_SELF, 0 // pivotY
                );
                anim.setDuration(700);
                ln.startAnimation(anim);
            }
        }, 1000);
        loadTutionsFragment(fr);
    }

    Callback<ListProgressResponseDTO> getListProgressCallback = new Callback<ListProgressResponseDTO>() {
        @Override
        public void onResponse(Call<ListProgressResponseDTO> call, Response<ListProgressResponseDTO> response) {
            if (response.isSuccessful()) {
                ListProgressResponseDTO responseDTO = response.body();
                listProgress.clear();
                listProgress.addAll(responseDTO.getData());
                if(selectedTab == 1){
                    loadSubjectStudy();
                }
            }
        }
        @Override
        public void onFailure(Call<ListProgressResponseDTO> call, Throwable t) {
            Log.d(">>> progress", "onFailure: " + t.getMessage());
        }
    };
    private void loadSubjectStudy() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, StudyFragment.newInstance(listProgress, user))
                .commit();
    }

    private void loadSchedulePlus() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, SchedulePlusFragment.newInstance())
                .commit();
    }

    public void loadNotification() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, NotificationPlusFragment.newInstance(user))
                .commit();
    }

    Callback<ListNotifyResponseDTO> getListNotifyCallback = new Callback<ListNotifyResponseDTO>() {
        @Override
        public void onResponse(Call<ListNotifyResponseDTO> call, Response<ListNotifyResponseDTO> response) {
            if (response.isSuccessful()) {
                ListNotifyResponseDTO responseDTO = response.body();
                listNoifys.clear();
                listNoifys.addAll(responseDTO.getData());
            }
        }
        @Override
        public void onFailure(Call<ListNotifyResponseDTO> call, Throwable t) {
            Log.d(">>> schedule", "onFailure: " + t.getMessage());
        }
    };

    Callback<ListNotifyResponseDTO> getListNewCallback = new Callback<ListNotifyResponseDTO>() {
        @Override
        public void onResponse(Call<ListNotifyResponseDTO> call, Response<ListNotifyResponseDTO> response) {
            if (response.isSuccessful()) {
                ListNotifyResponseDTO responseDTO = response.body();
                listNews.clear();
                listNews.addAll(responseDTO.getData());
            }
        }
        @Override
        public void onFailure(Call<ListNotifyResponseDTO> call, Throwable t) {
            Log.d(">>> schedule", "onFailure: " + t.getMessage());
        }
    };

    Callback<ListNotifyResponseDTO> getListTutionCallback = new Callback<ListNotifyResponseDTO>() {
        @Override
        public void onResponse(Call<ListNotifyResponseDTO> call, Response<ListNotifyResponseDTO> response) {
            if (response.isSuccessful()) {
                ListNotifyResponseDTO responseDTO = response.body();
                listTutions.clear();
                listTutions.addAll(responseDTO.getData());
            }
        }
        @Override
        public void onFailure(Call<ListNotifyResponseDTO> call, Throwable t) {
            Log.d(">>> schedule", "onFailure: " + t.getMessage());
        }
    };

    public void loadNotifyFragment(FrameLayout frameLayout) {
        iRetrofit.getAllNotify(0).enqueue(getListNotifyCallback);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), NotificationFragment.newInstance(listNoifys))
                .commit();
    }

    public void loadNewsFragment(FrameLayout frameLayout) {
        iRetrofit.getAllNotify(1).enqueue(getListNewCallback);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), NewsFragment.newInstance(listNews))
                .commit();
    }

    public void loadTutionsFragment(FrameLayout frameLayout) {
        iRetrofit.getAllNotify(2).enqueue(getListTutionCallback);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), TutionFragment.newInstance(listTutions))
                .commit();
    }

    public void loadService() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, ServiceFragment.newInstance(user))
                .commit();
    }

    public void loadProfile() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, ProfileFragment.newInstance(user))
                .commit();
    }

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
            case R.id.viewService:
                Intent intentService = new Intent(this, BottomService.class);
                intentService.setAction(BottomService.BOTTOM_SERVICE_SERVICE);
                startService(intentService);
                break;
            case R.id.viewProfile:
                Intent intentProfile = new Intent(this, BottomService.class);
                intentProfile.setAction(BottomService.BOTTOM_SERVICE_PROFILE);
                startService(intentProfile);
                break;
        }
    }

    public void onCLickListService(int index) {
        switch (index) {
            case 1:
                Intent intent1 = new Intent(MainActivity.this, ServiceListActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case 2:
                Intent intent2 = new Intent(MainActivity.this, AttendenceActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case 3:
                Intent intent3 = new Intent(MainActivity.this, FreeStudyActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent3);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case 4:
                Toast.makeText(this, "Chức năng đang phát triển", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent intent5 = new Intent(MainActivity.this, OtherServiceActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent5);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
        }
    }

    Callback<ScheduleGetByIdResponseDTO> getScheduleById = new Callback<ScheduleGetByIdResponseDTO>() {
        @Override
        public void onResponse(Call<ScheduleGetByIdResponseDTO> call, Response<ScheduleGetByIdResponseDTO> response) {
            if (response.isSuccessful()) {
                ScheduleGetByIdResponseDTO responseDTO = response.body();
                listScheduleById.clear();
                listScheduleById.addAll(responseDTO.getSchedules());
                ListSchedulesResponseDTO.Schedule schedule = listScheduleById.get(0);
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View viewDialog = inflater.inflate(R.layout.item_detail_schedules_v2, null);

                TextView txtAddress = viewDialog.findViewById(R.id.txtAddress);
                TextView txtClass = viewDialog.findViewById(R.id.txtClass);
                TextView txtCodeCourse = viewDialog.findViewById(R.id.txtCodeCourse);
                TextView txtTeacher = viewDialog.findViewById(R.id.txtTeacher);
                TextView txtRoom = viewDialog.findViewById(R.id.txtRoom);
                TextView txtTime = viewDialog.findViewById(R.id.txtTime);
                TextView txtDate = viewDialog.findViewById(R.id.txtDate);
                TextView txtMeet = viewDialog.findViewById(R.id.txtMeet);
                Button btnClose = viewDialog.findViewById(R.id.btnClose);

               txtAddress.setText(schedule.getAddress());
               txtClass.setText(schedule.getClass_name());
               txtCodeCourse.setText(schedule.getCourse_name().substring(schedule.getCourse_name().indexOf("-") + 1));
               txtTeacher.setText(schedule.getTeacher_name());
               txtRoom.setText(schedule.getRoom());
                String slot = "";
                switch (String.valueOf(schedule.getTime())){
                    case "1":
                        slot = "7h30 - 9h30";
                        break;
                    case "2":
                        slot = "9h35 - 11h45";
                        break;
                    case "3":
                        slot = "13h00 - 15h00";
                        break;
                    case "4":
                        slot = "15h15 - 17h15";
                        break;
                    case "5":
                        slot = "17h30 - 19h30";
                        break;
                    case "6":
                        slot = "19h30 - 21h30";
                        break;
                    default:
                        slot = "Tự học";
                        break;
                }
                txtTime.setText(slot);
                txtDate.setText(schedule.getDate());

                String meet = "<b>Link meet: </b>" + schedule.getMeet();
                txtMeet.setText(android.text.Html.fromHtml(meet));
                builder.setView(viewDialog);
                Drawable drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.background_dialog_schedule_show);
                androidx.appcompat.app.AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(drawable);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        }

        @Override
        public void onFailure(Call<ScheduleGetByIdResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };

    public void showDetaiSchedule(int id) {
        iRetrofit.getSchedulesById(id).enqueue(getScheduleById);
    }

    public ArrayList<ListSchedulesResponseDTO.Schedule> getDataSchedule() {
        return listSchedule;
    }

    Callback<ListSchedulesResponseDTO> getListScheduleCallback = new Callback<ListSchedulesResponseDTO>() {
        @Override
        public void onResponse(Call<ListSchedulesResponseDTO> call, Response<ListSchedulesResponseDTO> response) {
            if (response.isSuccessful()) {
                ListSchedulesResponseDTO responseDTO = response.body();
                listSchedule.clear();
                listSchedule.addAll(responseDTO.getData());
            }
        }

        @Override
        public void onFailure(Call<ListSchedulesResponseDTO> call, Throwable t) {
            Log.d(">>> schedule", "onFailure: " + t.getMessage());
        }
    };

    public ArrayList<ListSchedulesResponseDTO.Schedule> getDataTestSchedule() {
        return listTestSchedule;
    }

    Callback<ListSchedulesResponseDTO> getListTestScheduleCallback = new Callback<ListSchedulesResponseDTO>() {
        @Override
        public void onResponse(Call<ListSchedulesResponseDTO> call, Response<ListSchedulesResponseDTO> response) {
            if (response.isSuccessful()) {
                ListSchedulesResponseDTO responseDTO = response.body();
                listTestSchedule.clear();
                listTestSchedule.addAll(responseDTO.getData());
            }
        }
        @Override
        public void onFailure(Call<ListSchedulesResponseDTO> call, Throwable t) {
            Log.d(">>> schedule", "onFailure: " + t.getMessage());
        }
    };

    //Tín hiệu Bottom Service
    private BroadcastReceiver bottomReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("data");
            switch (result) {
                case "home": {
                    loadSubjectStudy();
                    if (selectedTab != 1) {
                        setSelectedTab1();
                        selectedTab = 1;
                        break;
                    }
                }
                case "notification": {
                    loadNotification();
                    if (selectedTab != 2) {
                        setSelectedTab2();
                        selectedTab = 2;
                        break;
                    }
                }
                case "schedule": {
                    loadSchedulePlus();
                    if (selectedTab != 3) {
                        setSelectedTab3();
                        selectedTab = 3;
                        break;
                    }
                }
                case "service": {
                    loadService();
                    if (selectedTab != 4) {
                        setSelectedTab4();
                        selectedTab = 4;
                        break;
                    }
                }
                case "profile": {
                    loadProfile();
                    if (selectedTab != 5) {
                        setSelectedTab5();
                        selectedTab = 5;
                        break;
                    }
                }
            }
        }
    };

    Callback<NotifyGetByIdResponseDTO> getNotifyByIdCallback = new Callback<NotifyGetByIdResponseDTO>() {
        @Override
        public void onResponse(Call<NotifyGetByIdResponseDTO> call, Response<NotifyGetByIdResponseDTO> response) {
            if (response.isSuccessful()) {
                NotifyGetByIdResponseDTO responseDTO = response.body();
                ArrayList<ListNotifyResponseDTO.Notify> notifies = new ArrayList<>();
                notifies.clear();
                notifies.addAll(responseDTO.getNotify());
                ListNotifyResponseDTO.Notify notify = notifies.get(0);
                intentNotify.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentNotify.putExtra("notification", notify);
                startActivity(intentNotify);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
            }
        }

        @Override
        public void onFailure(Call<NotifyGetByIdResponseDTO> call, Throwable t) {
            Log.d(">>> schedule", "onFailure: " + t.getMessage());
        }
    };

    public void handleToDetaiNotify(int id, Integer index) {
        intentNotify.putExtra("indexNotify", index);
        iRetrofit.getNotifyById(id).enqueue(getNotifyByIdCallback);
    }

    public void handleToEditProfile() {
        Intent intent = new Intent(MainActivity.this, EditProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentBottom = new IntentFilter(BottomService.BOTTOM_SERVICE_EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(bottomReceiver, intentBottom);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        user_id = sharedPreferences.getInt("id", -1);
        iRetrofit.getAllSchedule(user_id, 0).enqueue(getListScheduleCallback);
        iRetrofit.getAllSchedule(user_id, 1).enqueue(getListTestScheduleCallback);
        iRetrofit.getAllNotify(0).enqueue(getListNotifyCallback);
        iRetrofit.getAllNotify(1).enqueue(getListNewCallback);
        iRetrofit.getAllNotify(2).enqueue(getListTutionCallback);
        iRetrofit.getAllProgres(user_id).enqueue(getListProgressCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bottomReceiver);
    }
}