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
import tronglv.bd.fpolyapp.adapters.NewsAdapter;
import tronglv.bd.fpolyapp.adapters.NotificationAdapter;
import tronglv.bd.fpolyapp.models.News;
import tronglv.bd.fpolyapp.models.Notification;

public class NewsFragment extends Fragment {

    private RecyclerView rvNews;
    ArrayList<News> listNews;

    public NewsFragment() {
        // Required empty public constructor
    }

    //Truyền data vào fragment khi khởi tạo
    public static NewsFragment newInstance(ArrayList<News> listNews) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putSerializable("news", listNews);
        fragment.setArguments(args);
        return fragment;
    }

    //Đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listNews = (ArrayList<News>) getArguments().getSerializable("news");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvNews = view.findViewById(R.id.rvNews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvNews.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getContext(), listNews);
        rvNews.setAdapter(adapter);
    }
}