package tronglv.bd.fpolyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ListNotifyResponseDTO;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;
import tronglv.bd.fpolyapp.views.MainActivity;

public class TutionAdapter extends RecyclerView.Adapter<NotificationViewHolder>{

    Context context;
    ArrayList<ListNotifyResponseDTO.Notify> listTution;

    public TutionAdapter(Context context, ArrayList<ListNotifyResponseDTO.Notify> listTution) {
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
        ListNotifyResponseDTO.Notify tution = listTution.get(holder.getAdapterPosition());
        String name = tution.getTitle();
        holder.txtTitle.setText(name);
        String poster = tution.getPoster();
        holder.txtPoster.setText("Người đăng: "+ poster);
        String time = tution.getCreated_at();
        holder.txtTime.setText("Thời gian: "+ time);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
//                    ((MainActivity)context).handleToLongClick();
                }else {
                    ((MainActivity)context).handleToDetaiNotify(tution.getId(), 3);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTution.size();
    }
}
