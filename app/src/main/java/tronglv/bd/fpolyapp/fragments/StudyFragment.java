package tronglv.bd.fpolyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.adapters.ProgressStudyAdapter;
import tronglv.bd.fpolyapp.adapters.SubjectStudyAdapter;
import tronglv.bd.fpolyapp.dto.ListProgressResponseDTO;
import tronglv.bd.fpolyapp.dto.LoginResponseDTO;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.ProgressStudy;
import tronglv.bd.fpolyapp.models.SubjectStudy;

public class StudyFragment extends Fragment {

    private RecyclerView rvSubjectStudy, rvProgress;

    TextView txtName;
    ArrayList<ListProgressResponseDTO.Progress> listProgress;

    LoginResponseDTO.User user;

    public StudyFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static StudyFragment newInstance(ArrayList<ListProgressResponseDTO.Progress> listProgress, LoginResponseDTO.User user) {
        StudyFragment fragment = new StudyFragment();
        Bundle args = new Bundle();
        args.putSerializable("progress", listProgress);
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listProgress = (ArrayList<ListProgressResponseDTO.Progress>) getArguments().getSerializable("progress");
            user = (LoginResponseDTO.User) getArguments().getSerializable("user");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSubjectStudy = view.findViewById(R.id.rvSubjectStudy);
        txtName = view.findViewById(R.id.txtName);
        txtName.setText(user.getName());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rvSubjectStudy.setLayoutManager(layoutManager);
        SubjectStudyAdapter adapter = new SubjectStudyAdapter(getContext(), listProgress);
        rvSubjectStudy.setAdapter(adapter);

        rvProgress = view.findViewById(R.id.rvProgress);
        RecyclerView.LayoutManager layoutManagerProgress = new LinearLayoutManager(getContext());
        rvProgress.setLayoutManager(layoutManagerProgress);
        ProgressStudyAdapter adapterProgress = new ProgressStudyAdapter(getContext(), listProgress);
        rvProgress.setAdapter(adapterProgress);
    }
}