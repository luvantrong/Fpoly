package tronglv.bd.fpolyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;
import tronglv.bd.fpolyapp.models.News;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;

public class NewsAdapter extends RecyclerView.Adapter<NotificationViewHolder>{

    Context context;
    List<News> listNews;

    public NewsAdapter(Context context, List<News> listNews) {
        this.context = context;
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notififcation, parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        News news = listNews.get(holder.getAdapterPosition());
        String name = news.getTitle();
        holder.txtTitle.setText(name);
        String poster = news.getPoster();
        holder.txtPoster.setText("Người đăng: "+ poster);
        String time = news.getTime();
        holder.txtTime.setText("Thời gian: "+ time);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
//                    ((MainActivity)context).handleToLongClick();
                }else {
//                    ((MainActivity)context).handleToDetailProduct(product);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }
}
