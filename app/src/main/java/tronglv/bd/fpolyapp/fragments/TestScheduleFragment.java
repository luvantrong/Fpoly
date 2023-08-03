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
import tronglv.bd.fpolyapp.adapters.TestScheduleAdapter;
import tronglv.bd.fpolyapp.dto.ListSchedulesResponseDTO;
import tronglv.bd.fpolyapp.models.Schedule;
import tronglv.bd.fpolyapp.models.TestSchedule;

public class TestScheduleFragment extends Fragment {

    private RecyclerView rvTestSchedule;
    ArrayList<ListSchedulesResponseDTO.Schedule> listTestSchedule;

    public TestScheduleFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static TestScheduleFragment newInstance(ArrayList<ListSchedulesResponseDTO.Schedule> listTestSchedule) {
        TestScheduleFragment fragment = new TestScheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable("testSchedules", listTestSchedule);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listTestSchedule = (ArrayList<ListSchedulesResponseDTO.Schedule>) getArguments().getSerializable("testSchedules");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_schedule, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTestSchedule = view.findViewById(R.id.rvTestschedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTestSchedule.setLayoutManager(layoutManager);
        TestScheduleAdapter adapter = new TestScheduleAdapter(getContext(), listTestSchedule);
        rvTestSchedule.setAdapter(adapter);
    }
}