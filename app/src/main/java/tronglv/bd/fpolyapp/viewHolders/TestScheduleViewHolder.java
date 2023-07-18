package tronglv.bd.fpolyapp.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;

public class TestScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

    public TextView txtDate, txtRoom, txtSession, txtSubject, txtCode, txtTime,txtTeacher;
    private ItemClickListener itemClickListener;

    public TestScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        txtDate = itemView.findViewById(R.id.txtDate);
        txtRoom = itemView.findViewById(R.id.txtRoom);
        txtSession = itemView.findViewById(R.id.txtSession);
        txtSubject = itemView.findViewById(R.id.txtSubject);
        txtCode = itemView.findViewById(R.id.txtCode);
        txtTime = itemView.findViewById(R.id.txtTime);
        txtTeacher = itemView.findViewById(R.id.txtTeacher);
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
