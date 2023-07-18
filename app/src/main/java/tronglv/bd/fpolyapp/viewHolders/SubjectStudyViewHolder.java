package tronglv.bd.fpolyapp.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;

public class SubjectStudyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView txtNameSubject, txtCodeSubject, txtFormStudy, txtTime;
    private ItemClickListener itemClickListener;

    public SubjectStudyViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        txtNameSubject = itemView.findViewById(R.id.txtNameSubject);
        txtCodeSubject = itemView.findViewById(R.id.txtCodeSubject);
        txtFormStudy = itemView.findViewById(R.id.txtFormStudy);
        txtTime = itemView.findViewById(R.id.txtTime);
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
