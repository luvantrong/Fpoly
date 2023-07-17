package tronglv.bd.fpolyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tronglv.bd.fpolyapp.R;

public class StudyFragment extends Fragment {

    public StudyFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static StudyFragment newInstance() {
        StudyFragment fragment = new StudyFragment();
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
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        rvListProducts = view.findViewById(R.id.rvListProducts);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rvListProducts.setLayoutManager(layoutManager);
//        AdapterProdutcs adapter = new AdapterProdutcs(getContext(), listProducts);
//        rvListProducts.setAdapter(adapter);
    }
}