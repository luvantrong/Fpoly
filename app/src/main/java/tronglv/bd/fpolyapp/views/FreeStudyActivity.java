package tronglv.bd.fpolyapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;

public class FreeStudyActivity extends AppCompatActivity {

    private ImageView imgBack;

    private Spinner spForm, spAddress;

    ArrayList<String> forms = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToService();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_study);

        mapping();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToService();
            }
        });

        forms.add("Nộp trực tiếp tại phòng DVSV");
        forms.add("Chuyển phát nhanh (CPN)");

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, forms);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spForm.setAdapter(adapterSpinner);

        spForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, R.drawable.color_spinner));
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.primary));

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] {
                ContextCompat.getDrawable(this, R.drawable.backgroud_transparent),
                drawable
        });
        layerDrawable.setLayerGravity(1, Gravity.END | Gravity.CENTER_VERTICAL);
        ViewCompat.setBackground(spForm, layerDrawable);
        ViewCompat.setBackground(spAddress, layerDrawable);

        address.add("Nhận trực tiếp tại phòng DVSV");
        address.add("Chuyển phát nhanh (CPN)");

        ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, address);
        adapterSpinner1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddress.setAdapter(adapterSpinner1);

        spAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spForm = findViewById(R.id.spForm);
        spAddress = findViewById(R.id.spAddress);
        imgBack = findViewById(R.id.imgBack);
    }

    public void backToService() {
        // Quay lại MainActivity khi người dùng bấm nút "Back" trên điện thoại
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("index", 4);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}