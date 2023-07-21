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
import tronglv.bd.fpolyapp.adapters.TutionAdapter;
import tronglv.bd.fpolyapp.models.Notification;

public class TutionFragment extends Fragment {

    private RecyclerView rvTution;
    ArrayList<Notification> listTution;

    public TutionFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static TutionFragment newInstance(ArrayList<Notification> listTution) {
        TutionFragment fragment = new TutionFragment();
        Bundle args = new Bundle();
        args.putSerializable("tutions", listTution);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listTution = (ArrayList<Notification>) getArguments().getSerializable("tutions");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tution, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTution = view.findViewById(R.id.rvTution);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTution.setLayoutManager(layoutManager);
        TutionAdapter adapter = new TutionAdapter(getContext(), listTution);
        rvTution.setAdapter(adapter);
    }
}