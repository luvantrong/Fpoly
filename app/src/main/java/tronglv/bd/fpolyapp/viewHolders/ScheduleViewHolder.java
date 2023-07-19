package tronglv.bd.fpolyapp.viewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;

public class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

    public TextView txtDay, txtRoom, txtNameCourse;
    public ImageView btnMoreSchedule;
    private ItemClickListener itemClickListener;

    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        txtDay = itemView.findViewById(R.id.txtDay);
        txtRoom = itemView.findViewById(R.id.txtRoom);
        txtNameCourse = itemView.findViewById(R.id.txtNameCourse);
        btnMoreSchedule = itemView.findViewById(R.id.btnMoreSchedule);
    }


    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
