package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.fragments.ImageFragment;

public class OtherServiceActivity extends AppCompatActivity {

    private ViewPager vpSlide;

    private ImageView imgBack;

    private Spinner spRoom;

    TextView txtPhone, txtDescripttion;
    private int[] images = {R.drawable.img_slide_1, R.drawable.img_slide_2, R.drawable.img_slide_3, R.drawable.img_slide_4};
    private int currentPage = 0;
    private final int DELAY_MS = 2000;
    private final int PERIOD_MS = 2000;
    private Handler handler = new Handler();

    ArrayList<String> rooms = new ArrayList<>();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_service);

        mapping();

        String t1 = "Số điện thoại <span style='color: #F56903'>*</span>";
        txtPhone.setText(android.text.Html.fromHtml(t1));

        String t2 = "Nội dung yêu cầu <span style='color: #F56903'>*</span>";
        txtDescripttion.setText(android.text.Html.fromHtml(t2));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToService();
            }
        });

        ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager());
        vpSlide.setAdapter(adapter);

        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                vpSlide.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);

        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, R.drawable.color_spinner));
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.primary));

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] {
                ContextCompat.getDrawable(this, R.drawable.backgroud_transparent),
                drawable
        });
        layerDrawable.setLayerGravity(1, Gravity.END | Gravity.CENTER_VERTICAL);
        ViewCompat.setBackground(spRoom, layerDrawable);


        rooms.add("Phòng Dịch vụ");
        rooms.add("Phòng Hành chính");
        rooms.add("Phòng TC&QLĐT");
        rooms.add("Phòng CTSV");

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoom.setAdapter(adapterSpinner);

        spRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = (String) parent.getItemAtPosition(position);
                // xử lý giá trị được chọn ở đây
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // xử lý khi không có giá trị nào được chọn
            }
        });
    }

    private void mapping() {
        imgBack = findViewById(R.id.imgBack);
        vpSlide = findViewById(R.id.vpSlide);
        spRoom = findViewById(R.id.spRoom);
        txtPhone = findViewById(R.id.txtPhone);
        txtDescripttion = findViewById(R.id.txtDescription);
    }

    public void backToService() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 4);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private class ImagePagerAdapter extends FragmentPagerAdapter {
        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(images[position]);
        }

        @Override
        public int getCount() {
            return images.length;
        }
    }
}