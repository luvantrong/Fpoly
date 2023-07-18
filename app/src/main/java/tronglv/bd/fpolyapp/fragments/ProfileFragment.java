package tronglv.bd.fpolyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.User;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ProfileFragment extends Fragment {

    private RecyclerView rvProfile;
    private TextView txtLogOut;

    public ProfileFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtLogOut = view.findViewById(R.id.txtLogOut);

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).signOut();
            }
        });
    }
}