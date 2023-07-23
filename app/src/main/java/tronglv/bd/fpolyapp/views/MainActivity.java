package tronglv.bd.fpolyapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.fragments.NewsFragment;
import tronglv.bd.fpolyapp.fragments.NotificationFragment;
import tronglv.bd.fpolyapp.fragments.NotificationPlusFragment;
import tronglv.bd.fpolyapp.fragments.ProfileFragment;
import tronglv.bd.fpolyapp.fragments.SchedulePlusFragment;
import tronglv.bd.fpolyapp.fragments.ServiceFragment;
import tronglv.bd.fpolyapp.fragments.StudyFragment;
import tronglv.bd.fpolyapp.fragments.TutionFragment;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.ProgressStudy;
import tronglv.bd.fpolyapp.models.Schedule;
import tronglv.bd.fpolyapp.models.SubjectStudy;
import tronglv.bd.fpolyapp.models.TestSchedule;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

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

        account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);

        Integer index = getIntent().getIntExtra("index", 1);
        selectedTab = index;

        indexNotify = getIntent().getIntExtra("indexNotify", 1);

        if (selectedTab == 2) {
            setSelectedTab2();
            loadNotification();
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

    private void loadSubjectStudy() {
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
        listProgressStudy.add(progressStudy2);
        listProgressStudy.add(progressStudy1);
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
        Notification notification = new Notification("THÔNG BÁO NHẬN BẰNG TỐT NGHIỆP \n" +
                "(ĐỢT TỐT NGHIỆP THÁNG 06/2023)", "nhapnh", "15/07/2023 11:08", "Địa điểm: Phòng Đào Tạo - Tầng trệt Tòa nhà T (QTSC9) - Công viên phần mềm Quang Trung, Phường Tân Chánh Hiệp, Quận 12.\n" +
                "Thời gian: Bắt đầu ngày 24/07/2023\n" +
                "Thứ 2 đến thứ 6: Sáng : 08h30 – 11h30 và Chiều : 13:30 – 16:30\n" +
                "Thứ 7: Sáng : 08h30 – 11h30\n" +
                "Danh sách sinh viên nhận bằng: DANH SÁCH SINH VIÊN TỐT NGHIỆP ĐỢT THÁNG 06 NĂM 2023\n" +
                "Lưu ý:\n" +
                "Khi nhận bằng : Sinh viên là người trực tiếp đến nhận, mang theo 1 CMND/CCCD bản chính và 1 bản sao có công chứng trong vòng 6 tháng\n" +
                "Bản gốc bằng Tốt nghiệp chỉ được cấp 1 lần, sinh viên mất bản gốc chỉ được cấp lại bản sao\n" +
                "Trường hợp nhận thay phải có ủy quyền bằng văn bản có chứng thực theo quy định của Pháp luật, CMND/CCCD photo công chứng của người ủy quyền và người được ủy quyền (trong 6 tháng)\n" +
                "Bằng Tốt nghiệp sẽ giữ lại trong vòng 1 năm tại trường cơ sở HCM, sau thời gian trên nếu chưa nhận sẽ chuyển về Hà Nội.");

        Notification notification1 = new Notification("[QUAN TRỌNG] YÊU CẦU BỔ SUNG BẰNG TỐT NGHIỆP THPT", "huynh43", "19/07/2023 14:40", "Phòng Đào Tạo thông báo yêu cầu các bạn sinh viên đang thiếu bằng tốt nghiệp THPT vui lòng bổ sung đầy đủ hồ sơ. Nộp bản sao/photo công chứng bằng THPT là yêu cầu bắt buộc để lưu trữ hồ sơ sinh viên trong suốt quá trình học tập đến khi được xét tốt nghiệp. Sau thời hạn bổ sung bên dưới, sinh viên chưa bổ sung bằng THPT sẽ bị đình chỉ học tập mức cao nhất: BUỘC THÔI HỌC\n" +
                "\n" +
                "Hồ sơ nộp: 1 bản sao hoặc photo công chứng (trong vòng 6 tháng) bằng THPT. Trường hợp sinh viên học trung cấp thì có thể nộp bản sao hoặc photo công chứng (trong vòng 6 tháng) bằng tốt nghiệp trung cấp.\n" +
                "\n" +
                "Địa điểm nộp:\n" +
                "\n" +
                "- Cơ sở Nguyễn Kiệm: Phòng Đào Tạo - Tầng 1 - 778/B1 Nguyễn Kiệm, Phường 4, Quận Phú Nhuận, TP.HCM.\n" +
                "\n" +
                "- Cơ sở Quang Trung: Phòng Đào Tạo - Tầng trệt Tòa nhà T (QTSC9) - Công viên phần mềm Quang Trung, Phường Tân Chánh Hiệp, Quận 12, TP.HCM.\n" +
                "\n" +
                "Hạn nộp:31/07/2023\n" +
                "\n" +
                "Sinh viên có thể truy cập TẠI ĐÂY để kiểm tra thông tin cá nhân cũng như thông tin nộp bằng THPT (sinh viên chưa bổ sung bằng THPT sẽ được ghi chú \"Chưa nộp bằng THPT\")");

        Notification notification2 = new Notification("THÔNG BÁO LỊCH HỌC MÔN PDP102 KHÓA 19.3 HỌC KỲ SUMMER 2023 (BLOCK 2)", "kieuntt", "5/07/2023 10:10", "Sinh viên vui lòng check lịch học của mình trên hệ thống Ap.poly.edu.vn trước khi đến lớp.\n" +
                "\n" +
                "(Tại đây)\n" +
                "\n" +
                " \n" +
                "\n" +
                "Mọi thông tin, vui lòng liên hệ phòng Tổ chức và Quản lý Đào tạo –Email:\n" +
                "\n" +
                "daotaofpoly.hcm@fe.edu.vn\n" +
                "\n" +
                "\n" +
                "\n" +
                "Chúc các bạn học tốt !");
        ArrayList<Notification> listNotification = new ArrayList<>();
        listNotification.add(notification);
        listNotification.add(notification1);
        listNotification.add(notification2);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), NotificationFragment.newInstance(listNotification))
                .commit();
    }

    public void loadNewsFragment(FrameLayout frameLayout) {
        Notification news = new Notification("P.CTSV THÔNG BÁO XÁC NHẬN ĐĂNG KÝ \n" +
                "THÀNH CÔNG BHYT ĐỢT 2 - T6/2023", "thunta62", "18/07/2023 10:43", "Hiện tại BHYT đợt 2 – học kỳ Summer năm 2023 đã đăng ký thành công và có thể sử dụng trên ứng dụng VssID các bạn sinh viên có thể vào app VssID hoặc trang baohiemxahoi.gov.vn để tra cứu thông tin. \n" +
                "\n" +
                "  \n" +
                "\n" +
                "Từ năm 2020 BHYT sẽ sử dụng thẻ BHYT điện tử VssID và KHÔNG CẤP thẻ BHYT giấy.\n" +
                "\n" +
                "  \n" +
                "\n" +
                "Bảo hiểm xã hội số (VssID) là ứng dụng trên nền tảng thiết bị di động của BHXH Việt Nam thuộc quyền quản lý của cơ quan BHXH. Với phần mềm này, người tham gia BHXH có thể tra cứu quá trình tham gia bảo hiểm và tiếp cận thông tin dễ dàng, thuận tiện nhất. \n" +
                "\n" +
                "  \n" +
                "\n" +
                "- Lợi ích của việc sử dụng Ứng dụng VssID\n" +
                "\n" +
                " \n" +
                "\n" +
                "+ Tra cứu mã số BHXH\n" +
                "\n" +
                "\n" +
                "\n" +
                "+ Tra cứu quá trình tham gia BHXH, BHYT, BHTN\n" +
                "\n" +
                "\n" +
                "\n" +
                "+ Tra cứu thời gian sử dụng, sử dụng thẻ\n" +
                "\n" +
                " \n" +
                "\n" +
                "Hướng dẫn sử dụng VssID tại đây\n" +
                "Clip hướng dẫn tại đây \n" +
                "Lưu ý: Ứng dụng VssID thuộc quyền quản lý của Cơ quan BHXH Việt Nam. Trong quá trình sử dụng nếu có bất kì lỗi hay thắc mắc nào, vui lòng liên hệ tổng đài hỗ trợ 24/7 Số hotline: 19009068 để được hỗ trợ. ");

        ArrayList<Notification> listNews = new ArrayList<>();
        listNews.add(news);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), NewsFragment.newInstance(listNews))
                .commit();
    }

    public void loadTutionsFragment(FrameLayout frameLayout) {
        Notification tution = new Notification("THÔNG BÁO PHÁT SÁCH GIÁO TRÌNH \n" +
                "HỌC KỲ SUMMER 2023", "lientt", "08/05/2023 09:32", "Phòng Dịch Vụ Sinh Viên thông báo danh sách sinh viên THÔI HỌC TỰ NGUYỆN học kỳ SUMMER 2023 cập nhật ngày 26/05/2023.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Sinh viên sẽ không được xếp lớp học từ tháng 05/2023.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Sinh viên xem danh sách Tại đây\n" +
                "\n" +
                ".\n" +
                "\n" +
                "Mọi thắc mắc, Sinh viên có thể liên hệ với phòng Dịch vụ sinh viên qua các kênh như sau:\n" +
                "\n" +
                " \n" +
                "\n" +
                "- Email: dvsvpoly.hcm@poly.edu.vn\n" +
                "\n" +
                " \n" +
                "\n" +
                "- Link google meet: https://meet.google.com/frw-xyyf-afk \n" +
                "\n" +
                " \n" +
                "\n" +
                "- Hotline: 028.7308.8800\n" +
                "\n" +
                "  (Khung giờ từ 7h00 - 20h30, Từ thứ 2 đến thứ 7)\n" +
                "\n" +
                " \n" +
                "\n" +
                "Cảm ơn vì Bạn đã luôn đồng hành cùng FPT Polytechnic HCM,\n" +
                "\n" +
                " \n" +
                "\n" +
                "Chúc các bạn thật nhiều sức khỏe!");
        Notification tution1 = new Notification("DANH SÁCH SINH VIÊN HOÀN THÀNH \n" +
                "HỌC PHÍ KỲ SUMMER 2023", "lientt", "05/05/2023 10:26", "Bạn đang muốn đổi nhà mới nhân dịp học kỳ mới?\n" +
                "\n" +
                "Bạn đang muốn tìm kiếm nhà trọ thuận tiện với kế hoạch di chuyển của bản thân mình?\n" +
                "\n" +
                "Hay bất kì mong muốn về chổ ở nào của bạn cũng đã có P.CTSV lo rồi đây!!!\n" +
                "\n" +
                "\n" +
                "\n" +
                "Link điền thông tin cần hỗ trợ nhà trọ: https://docs.google.com/forms/d/e/1FAIpQLSd5uqxACL-RazXFD0D13BwdGyiOwTR4-4FqZQoxppOJyhT23A/viewform\n" +
                "\n" +
                "\n" +
                "\n" +
                "Với mong muốn giúp các bạn có thể chọn lựa cho mình được căn phòng ưng ý và phù hợp, Sinh viên chỉ cần điền thông tin vào link, bộ phận phụ trách sẽ cung cấp thông tin hỗ trợ tham khảo tìm phòng trọ đầy đủ tiện nghi, với nhiều mức giá hợp lý.\n" +
                "\n" +
                "Việc lựa chọn loại hình phòng trọ sẽ dựa vào nhu cầu ở ghép hay ở một mình của các bạn và khả năng tài chính nữa. Vì thế, chất lượng nhà trọ sẽ được đánh giá dựa trên các tiêu chí sau:\n" +
                "\n" +
                "- An ninh\n" +
                "\n" +
                "- Giá cả phù hợp với chất lượng\n" +
                "\n" +
                "- Tiện nghi đảm bảo\n" +
                "\n" +
                "- Vị trí thuận tiện cho việc đi lại\n" +
                "\n" +
                "\n" +
                "\n" +
                "Hy vọng đây sẽ là những thông tin hữu ích cho các bạn.\n" +
                "\n" +
                "Chúc tất cả các bạn sinh viên tìm được một chỗ trọ ưng ý để có thể tập trung tốt cho việc học tập nhé!");

        ArrayList<Notification> listTution = new ArrayList<>();
        listTution.add(tution);
        listTution.add(tution1);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), TutionFragment.newInstance(listTution))
                .commit();
    }

    public void loadService() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, ServiceFragment.newInstance())
                .commit();
    }

    public void loadProfile() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, ProfileFragment.newInstance())
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
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void showDetaiSchedule(Schedule schedule) {
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

    public ArrayList<Schedule> getDataSchedule() {
        Schedule schedule = new Schedule("MOB403", "Android Networking", "15/07/2023", "Phần mềm Quang Trung", "Phòng 308 (Nhà T)", "17h30 - 19h30", "MD17306", "channn3", "Ca 5");
        ArrayList<Schedule> listSchedule = new ArrayList<>();
        listSchedule.add(schedule);
        listSchedule.add(schedule);
        listSchedule.add(schedule);
        listSchedule.add(schedule);
        return listSchedule;
    }

    public ArrayList<TestSchedule> getDataTestSchedule() {
        TestSchedule testSchedule = new TestSchedule("11/08/2023", "Phòng 308 (Toà T)", "Ca 5", "Android Networking", "MOB403", "17h30 - 19h30", "chann3");
        ArrayList<TestSchedule> listTestSchedule = new ArrayList<>();
        listTestSchedule.add(testSchedule);
        listTestSchedule.add(testSchedule);
        listTestSchedule.add(testSchedule);
        return listTestSchedule;
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

    public void handleToDetaiNotify(Notification notification, Integer index) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification", (Serializable) notification);
        intent.putExtra("indexNotify", index);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
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

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bottomReceiver);
    }
}