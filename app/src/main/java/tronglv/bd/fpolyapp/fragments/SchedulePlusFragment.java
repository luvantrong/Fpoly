package tronglv.bd.fpolyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.adapters.ScheduleAdapter;
import tronglv.bd.fpolyapp.adapters.ViewPagerScheduleAdapter;
import tronglv.bd.fpolyapp.models.Schedule;

public class SchedulePlusFragment extends Fragment {

    TabLayout tlSchedulePlus;
    ViewPager vpSchedulesPlus;

    public SchedulePlusFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static SchedulePlusFragment newInstance() {
        SchedulePlusFragment fragment = new SchedulePlusFragment();
//        Bundle args = new Bundle();
//        args.putSerializable("schedules", listSchedule);
//        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            listSchedule = (ArrayList<Schedule>) getArguments().getSerializable("schedules");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_plus, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tlSchedulePlus = view.findViewById(R.id.tlSchedulePlus);
        vpSchedulesPlus = view.findViewById(R.id.vpSchedulesPlus);

        tlSchedulePlus.addTab(tlSchedulePlus.newTab().setText("Lịch học"));
        tlSchedulePlus.addTab(tlSchedulePlus.newTab().setText("Lịch thi"));
        tlSchedulePlus.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerScheduleAdapter adapter = new ViewPagerScheduleAdapter(getActivity().getSupportFragmentManager(), 2);
        vpSchedulesPlus.setAdapter(adapter);

        vpSchedulesPlus.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlSchedulePlus));
        tlSchedulePlus.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpSchedulesPlus.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    tlSchedulePlus.setSelectedTabIndicator(R.drawable.background_item_tablayout_left);
                }else{
                    tlSchedulePlus.setSelectedTabIndicator(R.drawable.background_item_tablayout_right);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}