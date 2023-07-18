package tronglv.bd.fpolyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.viewHolders.BasisViewHolder;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;
import tronglv.bd.fpolyapp.views.LoginActivity;
import tronglv.bd.fpolyapp.views.MainActivity;

public class BasisAdapter extends RecyclerView.Adapter<BasisViewHolder> {
    Context context;
    List<String> basis;

    public BasisAdapter(Context context, List<String> basis) {
        this.context = context;
        this.basis = basis;
    }

    @NonNull
    @Override
    public BasisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_select_basis, parent,false);
        return new BasisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasisViewHolder holder, int position) {
        String item = basis.get(holder.getAdapterPosition());
        holder.txtNameBasis.setText(item);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
//                    ((MainActivity)context).handleToLongClick();
                }else {
                    ((LoginActivity)context).setTextBasis(item);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return basis.size();
    }
}
