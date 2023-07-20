package tronglv.bd.fpolyapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.fragments.NewsFragment;
import tronglv.bd.fpolyapp.fragments.NotificationFragment;
import tronglv.bd.fpolyapp.fragments.NotificationPlusFragment;
import tronglv.bd.fpolyapp.fragments.ProfileFragment;
import tronglv.bd.fpolyapp.fragments.SchedulePlusFragment;
import tronglv.bd.fpolyapp.fragments.StudyFragment;
import tronglv.bd.fpolyapp.fragments.TutionFragment;
import tronglv.bd.fpolyapp.models.News;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.ProgressStudy;
import tronglv.bd.fpolyapp.models.Schedule;
import tronglv.bd.fpolyapp.models.SubjectStudy;
import tronglv.bd.fpolyapp.models.Tution;
import tronglv.bd.fpolyapp.models.User;
import tronglv.bd.fpolyapp.services.BottomService;

public class MainActivity extends AppCompatActivity {

    private FrameLayout flMain;
    private LinearLayout viewHome, viewNotification, viewSchedule, viewProfile, bottomBar;
    private ImageView imgHome, imgNotification, imgSchedule, imgProfile;
    private TextView txtHome, txtNotification, txtSchedule, txtProfile, txtCancelSignOut;

    private RelativeLayout rlSignOut, rlViewSignOut;

    private Button btnSignOutMain;

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
        bottomBar = findViewById(R.id.bottomBar);


        imgHome = findViewById(R.id.imgHome);
        imgNotification = findViewById(R.id.imgNotification);
        imgSchedule = findViewById(R.id.imgSchedule);
        imgProfile = findViewById(R.id.imgProfile);

        txtHome = findViewById(R.id.txtHome);
        txtNotification = findViewById(R.id.txtNotification);
        txtSchedule = findViewById(R.id.txtSchedule);
        txtProfile = findViewById(R.id.txtProfile);
        txtCancelSignOut = findViewById(R.id.txtCancelSignOut);

        btnSignOutMain = findViewById(R.id.btnSignOutMain);
        rlSignOut = findViewById(R.id.rlSignOut);
        rlViewSignOut = findViewById(R.id.rlViewSignOut);


        loadSubjectStudy();

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
    }

    public void signOut() {
        if (account != null) {
            gsc.signOut().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(homeIntent);
                    overridePendingTransition(R.anim.anim_enter1, R.anim.anim_exit1);
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
    private void loadSubjectStudy (){
        SubjectStudy subjectStudy = new SubjectStudy(1, "Phát triển cá nhân 2", "PDP201", "Offline", "17 buổi");
        SubjectStudy subjectStudy1 = new SubjectStudy(2, "Quản lý dự án với phần mềm Agile", "MOB104", "Online", "17 buổi");
        SubjectStudy subjectStudy2 = new SubjectStudy(3, "Android Networking", "MOB403", "Offline", "17 buổi");
        SubjectStudy subjectStudy3 = new SubjectStudy(4, "Khởi sự doanh nghiệp", "SYB3012", "Online", "6 buổi");

        ArrayList<SubjectStudy> listSubjectStudy = new ArrayList<SubjectStudy>();
        listSubjectStudy.add(subjectStudy);
        listSubjectStudy.add(subjectStudy1);
        listSubjectStudy.add(subjectStudy2);
        listSubjectStudy.add(subjectStudy3);

        ProgressStudy progressStudy = new ProgressStudy(1, "Phát triển cá nhân 2", 17, 1, 17);
        ProgressStudy progressStudy1 = new ProgressStudy(1, "Quản lý dự án với phần mềm Agile", 6, 5, 17);
        ProgressStudy progressStudy2 = new ProgressStudy(1, "Android Networking", 7, 0, 17);
        ProgressStudy progressStudy3 = new ProgressStudy(1, "Khởi sự doanh nghiệp", 2, 0, 6);

        ArrayList<ProgressStudy> listProgressStudy = new ArrayList<>();
        listProgressStudy.add(progressStudy);
        listProgressStudy.add(progressStudy1);
        listProgressStudy.add(progressStudy2);
        listProgressStudy.add(progressStudy3);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, StudyFragment.newInstance(listSubjectStudy, listProgressStudy))
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
                .replace(R.id.flMain, NotificationPlusFragment.newInstance())
                .commit();
    }

    public void loadNotifyFragment(FrameLayout frameLayout) {
        Notification notification = new Notification("THÔNG BÁO LỊCH HỌC MÔN PDP102 KHOÁ 19.3 \n" +
                "HỌC KỲ SUMMER 2023 (BLOCK 2)", "nhapnh", "15/07/2023 11:08");

        ArrayList<Notification> listNotification = new ArrayList<>();
        listNotification.add(notification);
        listNotification.add(notification);
        listNotification.add(notification);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), NotificationFragment.newInstance(listNotification))
                .commit();
    }

    public void loadNewsFragment(FrameLayout frameLayout) {
        News news = new News("P.CTSV THÔNG BÁO XÁC NHẬN ĐĂNG KÝ \n" +
                "THÀNH CÔNG BHYT ĐỢT 2 - T6/2023", "thunta62", "18/07/2023 10:43");

        ArrayList<News> listNews = new ArrayList<>();
        listNews.add(news);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), NewsFragment.newInstance(listNews))
                .commit();
    }

    public void loadTutionsFragment(FrameLayout frameLayout) {
        Tution tution = new Tution("THÔNG BÁO PHÁT SÁCH GIÁO TRÌNH \n" +
                "HỌC KỲ SUMMER 2023", "lientt", "08/05/2023 09:32");
        Tution tution1 = new Tution("DANH SÁCH SINH VIÊN HOÀN THÀNH \n" +
                "HỌC PHÍ KỲ SUMMER 2023", "lientt", "05/05/2023 10:26");

        ArrayList<Tution> listTution = new ArrayList<>();
        listTution.add(tution);
        listTution.add(tution1);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), TutionFragment.newInstance(listTution))
                .commit();
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

    public void showDetaiSchedule(Schedule schedule){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.dialog_detail_schedule, null);

        TextView txtCodeNameClass = viewDialog.findViewById(R.id.txtCodeNameClass);
        TextView txtNameTeacher = viewDialog.findViewById(R.id.txtNameTeacher);
        TextView txtSlot = viewDialog.findViewById(R.id.txtSlot);
        TextView txtBasis = viewDialog.findViewById(R.id.txtBasis);
        TextView txtLinkMeet = viewDialog.findViewById(R.id.txtLinkMeet);
        Button btnClose = viewDialog.findViewById(R.id.btnClose);

        String t1 = "<b>Lớp: </b>" + schedule.getClassName();
        txtCodeNameClass.setText(android.text.Html.fromHtml(t1));

        String t2 = "<b>Giảng viên: </b>" + schedule.getTeacherName();
        txtNameTeacher.setText(android.text.Html.fromHtml(t2));

        String t3 = "<b>Thời gian: </b>" + schedule.getSlot();
        txtSlot.setText(android.text.Html.fromHtml(t3));

        String t4 = "<b>Giảng đường: </b>" + schedule.getAddress();
        txtBasis.setText(android.text.Html.fromHtml(t4));

        String t5 = "<b>Link meet: </b>" + "https://meet.google.com/top-ynme-hka?authuser=0&pli=1";
        txtLinkMeet.setText(android.text.Html.fromHtml(t5));
        builder.setView(viewDialog);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.background_dialog_schedule_show);
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

    //Tín hiệu Bottom Service
    private BroadcastReceiver bottomReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("data");
            switch (result) {
                case "home": {
                    loadSubjectStudy();
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
                        selectedTab = 1;
                        break;
                    }

                }
                case "notification": {
                    loadNotification();
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
                        selectedTab = 2;
                        break;
                    }

                }
                case "schedule": {
                    loadSchedulePlus();
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