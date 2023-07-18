package tronglv.bd.fpolyapp.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;

public class ProgressStudyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView txtNameSubjectProgress, txtPercent, txtStudyPlus, txtProgressPlus, txtStatus;

    public ProgressBar ppProgress;

    public ImageView imgSubjectProgress, btnDown, btnUp;

    public RelativeLayout viewPlus;
    private ItemClickListener itemClickListener;

    public ProgressStudyViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        txtNameSubjectProgress = itemView.findViewById(R.id.txtNameSubjectProgress);
        txtPercent = itemView.findViewById(R.id.txtPercent);
        txtStudyPlus = itemView.findViewById(R.id.txtStudyPlus);
        txtProgressPlus = itemView.findViewById(R.id.txtProgressPlus);
        txtStatus = itemView.findViewById(R.id.txtStatus);
        ppProgress = itemView.findViewById(R.id.ppProgress);
        imgSubjectProgress = itemView.findViewById(R.id.imgSubjectProgress);
        btnDown = itemView.findViewById(R.id.btnDown);
        btnUp = itemView.findViewById(R.id.btnUp);
        viewPlus = itemView.findViewById(R.id.viewPlus);
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
