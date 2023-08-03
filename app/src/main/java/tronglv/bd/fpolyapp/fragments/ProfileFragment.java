package tronglv.bd.fpolyapp.fragments;

import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;

import tronglv.bd.fpolyapp.dto.LoginResponseDTO;
import tronglv.bd.fpolyapp.views.EditProfile;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ProfileFragment extends Fragment {
    private TextView txtLogOut, txtFullName, txtEmail, txtMssv, txtRole, txtDate, textAddress, textMajoy, textStatus;

    private ImageView imgEdit, imgAvatar;

    private LoginResponseDTO.User user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static ProfileFragment newInstance(LoginResponseDTO.User user) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtLogOut = view.findViewById(R.id.txtLogOut);
        imgEdit = view.findViewById(R.id.imgEdit);
        txtFullName = view.findViewById(R.id.txtFullName);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtMssv = view.findViewById(R.id.txtMssv);
        txtRole = view.findViewById(R.id.txtRole);
        txtDate = view.findViewById(R.id.txtDate);
        textAddress = view.findViewById(R.id.textAddress);
        textMajoy = view.findViewById(R.id.textMajoy);
        textStatus = view.findViewById(R.id.textStatus);
        imgAvatar = view.findViewById(R.id.imgAvatar);


        txtFullName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtMssv.setText(user.getStudent_code());

        if(user.getGender() == 1){
            txtRole.setText("Nam");
        }else{
            txtRole.setText("Nữ");
        }
        txtDate.setText(user.getBirthday());
        textAddress.setText(user.getAddress());
        textMajoy.setText(user.getCourse());

        if(user.getStatus() == 0){
            textStatus.setText("Trạng thái: HDI");
        }else if(user.getStatus() == 1 ){
            textStatus.setText("Trạng thái: Bảo lưu");
        }

        Glide.with(view.getContext()).load(user.getAvatar()).into(imgAvatar);

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).showSignOut();
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getContext()).handleToEditProfile();
            }
        });

    }
}