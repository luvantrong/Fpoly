package tronglv.bd.fpolyapp.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.views.MainActivity;

public class NotificationPlusFragment extends Fragment {

    private TextView txtNotify, txtNews, txtTution;
    private RelativeLayout rlNotify, rlNews, rlTution;

    public FrameLayout flNotifyPlus;

    public Integer index = 1;

    private LinearLayout  lnNotifyPlus;

    public NotificationPlusFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static NotificationPlusFragment newInstance() {
        NotificationPlusFragment fragment = new NotificationPlusFragment();
        Bundle args = new Bundle();
//        args.putSerializable("notifications", listNotification);
//        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            listNotification = (ArrayList<Notification>) getArguments().getSerializable("notifications");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_plus, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNotify = view.findViewById(R.id.txtNotify);
        txtNews = view.findViewById(R.id.txtNews);
        txtTution = view.findViewById(R.id.txtTution);
        rlNotify = view.findViewById(R.id.rlNotify);
        rlNews = view.findViewById(R.id.rlNews);
        rlTution = view.findViewById(R.id.rlTution);
        flNotifyPlus = view.findViewById(R.id.flNotifyPlus);
        lnNotifyPlus= view.findViewById(R.id.lnNotifyPlus);

//        ((MainActivity)view.getContext()).showMenuNotify(lnNotifyPlus, flNotifyPlus);

        index = ((MainActivity) view.getContext()).indexNotify();

        if (index == 1) {
            indexNotify1();
            ((MainActivity)view.getContext()).showMenuNotify(lnNotifyPlus, flNotifyPlus);

        }

        if (index == 2) {
            indexNotify2();
            ((MainActivity)view.getContext()).showMenuNotify2(lnNotifyPlus,flNotifyPlus);
        }

        if (index == 3) {
            indexNotify3();
            ((MainActivity)view.getContext()).showMenuNotify3( lnNotifyPlus,flNotifyPlus);
        }


        rlNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexNotify1();
                ((MainActivity)view.getContext()).loadNotifyFragment( flNotifyPlus);

                index = 1;
            }
        });

        rlNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexNotify2();
                ((MainActivity)view.getContext()).loadNewsFragment(flNotifyPlus);
                index = 2;
            }
        });

        rlTution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexNotify3();
                ((MainActivity)view.getContext()).loadTutionsFragment( flNotifyPlus);

                index = 3;
            }
        });

    }

    private void indexNotify1() {
        rlNotify.setBackgroundResource(R.drawable.background_item_notify_select);
        txtNotify.setTextColor(getResources().getColor(R.color.white));

        rlNews.setBackgroundResource(R.drawable.background_item_notification);
        txtNews.setTextColor(getResources().getColor(R.color.text_bottom_tab));

        rlTution.setBackgroundResource(R.drawable.background_item_tution);
        txtTution.setTextColor(getResources().getColor(R.color.text_bottom_tab));
    }

    private void indexNotify2() {
        rlNotify.setBackgroundResource(R.drawable.background_item_notify);
        txtNotify.setTextColor(getResources().getColor(R.color.text_bottom_tab));

        rlNews.setBackgroundResource(R.drawable.background_item_notification_select);
        txtNews.setTextColor(getResources().getColor(R.color.white));

        rlTution.setBackgroundResource(R.drawable.background_item_tution);
        txtTution.setTextColor(getResources().getColor(R.color.text_bottom_tab));
    }

    private void indexNotify3() {
        rlNotify.setBackgroundResource(R.drawable.background_item_notify);
        txtNotify.setTextColor(getResources().getColor(R.color.text_bottom_tab));

        rlNews.setBackgroundResource(R.drawable.background_item_notification);
        txtNews.setTextColor(getResources().getColor(R.color.text_bottom_tab));

        rlTution.setBackgroundResource(R.drawable.background_item_tution_select);
        txtTution.setTextColor(getResources().getColor(R.color.white));
    }

}