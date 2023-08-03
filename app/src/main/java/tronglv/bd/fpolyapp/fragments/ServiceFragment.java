package tronglv.bd.fpolyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.LoginResponseDTO;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ServiceFragment extends Fragment {

    private TextView txtServiceOne, txtServiceTwo, txtServiceThree, txtServiceFour, txtServiceFive, txtNameUser;
    LoginResponseDTO.User user;

    private ImageView imgAvatar;
    public ServiceFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static ServiceFragment newInstance(LoginResponseDTO.User user) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (LoginResponseDTO.User) getArguments().getSerializable("user");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtServiceOne = view.findViewById(R.id.txtServiceOne);
        txtServiceTwo = view.findViewById(R.id.txtServiceTwo);
        txtServiceThree = view.findViewById(R.id.txtServiceThree);
        txtServiceFour = view.findViewById(R.id.txtServiceFour);
        txtServiceFive = view.findViewById(R.id.txtServiceFive);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        txtNameUser = view.findViewById(R.id.txtNameUser);

        txtNameUser.setText(user.getName());
        Glide.with(view.getContext()).load(user.getAvatar()).into(imgAvatar);

        txtServiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).onCLickListService(1);
            }
        });

        txtServiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).onCLickListService(2);
            }
        });

        txtServiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).onCLickListService(3);
            }
        });

        txtServiceFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).onCLickListService(4);
            }
        });

        txtServiceFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).onCLickListService(5);
            }
        });

    }
}