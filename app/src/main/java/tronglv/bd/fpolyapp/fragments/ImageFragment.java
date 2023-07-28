package tronglv.bd.fpolyapp.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.adapters.NotificationAdapter;
import tronglv.bd.fpolyapp.models.Notification;

public class ImageFragment extends Fragment {

    private static final String ARG_IMAGE_RES_ID = "image_res_id";
    private ImageView imgSlide;

    private int image;

    public ImageFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static ImageFragment newInstance(int image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image =  getArguments().getInt("image");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgSlide = view.findViewById(R.id.imgSlide);
        imgSlide.setImageResource(image);
    }


}