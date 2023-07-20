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
import tronglv.bd.fpolyapp.models.Tution;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;

public class TutionAdapter extends RecyclerView.Adapter<NotificationViewHolder>{

    Context context;
    List<Tution> listTution;

    public TutionAdapter(Context context, List<Tution> listTution) {
        this.context = context;
        this.listTution = listTution;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notififcation, parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Tution tution = listTution.get(holder.getAdapterPosition());
        String name = tution.getTitle();
        holder.txtTitle.setText(name);
        String poster = tution.getPoster();
        holder.txtPoster.setText("Người đăng: "+ poster);
        String time = tution.getTime();
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
        return listTution.size();
    }
}
