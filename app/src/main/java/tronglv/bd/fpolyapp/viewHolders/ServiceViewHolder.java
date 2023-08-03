package tronglv.bd.fpolyapp.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;

public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public ImageView imgListService;
    public TextView txtDateList, txtNameService, txtNameServiceCourse, txtReason;
    private ItemClickListener itemClickListener;
    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        imgListService = itemView.findViewById(R.id.imgListService);
        txtDateList = itemView.findViewById(R.id.txtDateList);
        txtNameService = itemView.findViewById(R.id.txtNameService);
        txtNameServiceCourse = itemView.findViewById(R.id.txtNameServiceCourse);
        txtReason = itemView.findViewById(R.id.txtReason);
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
