package tronglv.bd.fpolyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.adapters.ScheduleAdapter;
import tronglv.bd.fpolyapp.models.Schedule;


public class ScheduleFragment extends Fragment {
    private RecyclerView rvSchedules;
    ArrayList<Schedule> listSchedule;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static ScheduleFragment newInstance(ArrayList<Schedule> listSchedule) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable("schedules", listSchedule);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listSchedule = (ArrayList<Schedule>) getArguments().getSerializable("schedules");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSchedules = view.findViewById(R.id.rvSchedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvSchedules.setLayoutManager(layoutManager);
        ScheduleAdapter adapter = new ScheduleAdapter(getContext(), listSchedule);
        rvSchedules.setAdapter(adapter);
    }

}