package tronglv.bd.fpolyapp.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;
import tronglv.bd.fpolyapp.models.ProgressStudy;
import tronglv.bd.fpolyapp.models.SubjectStudy;
import tronglv.bd.fpolyapp.viewHolders.ProgressStudyViewHolder;
import tronglv.bd.fpolyapp.viewHolders.SubjectStudyViewHolder;

public class ProgressStudyAdapter extends RecyclerView.Adapter<ProgressStudyViewHolder> {

    Context context;
    List<ProgressStudy> listProgressStudy;

    public ProgressStudyAdapter(Context context, List<ProgressStudy> listProgressStudy) {
        this.context = context;
        this.listProgressStudy = listProgressStudy;
    }

    @NonNull
    @Override
    public ProgressStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_progress_study, parent, false);
        return new ProgressStudyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressStudyViewHolder holder, int position) {
        ProgressStudy progressStudy = listProgressStudy.get(holder.getAdapterPosition());
        String nameSubject = progressStudy.getNameSubject();
        holder.txtNameSubjectProgress.setText(nameSubject);
        int numberProgressBar = Math.round(progressStudy.getLearn() * 100 / progressStudy.getSumLearn());
        holder.ppProgress.setProgress(numberProgressBar);
        holder.txtPercent.setText(String.valueOf(numberProgressBar) + "%");
        String learn = String.valueOf(progressStudy.getLearn());
        holder.txtStudyPlus.setText("Đã học: " + learn + "/" + progressStudy.getSumLearn() + " buổi");
        String absent = String.valueOf("Vắng: " + progressStudy.getAbsent() + "/" + progressStudy.getSumLearn() + " buổi");
        holder.txtProgressPlus.setText(absent);

        String status = "";
        int numberStatus = Math.round(progressStudy.getAbsent() * 100 / progressStudy.getSumLearn());

        if (numberStatus > 20) {
            status = "Failed";
            int color = Color.parseColor("#F62C2A");
            ColorStateList progressColor = ColorStateList.valueOf(color);
            holder.ppProgress.setProgressTintList(progressColor);
            holder.ppProgress.setProgress(100);
            holder.txtPercent.setText("0%");
        } else {
            status = "Studying";
        }
        holder.txtStatus.setText("Trạng thái: " + status);

        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnDown.setVisibility(View.GONE);
                holder.btnUp.setVisibility(View.VISIBLE);
                holder.viewPlus.setVisibility(View.VISIBLE);
            }
        });

        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnDown.setVisibility(View.VISIBLE);
                holder.btnUp.setVisibility(View.GONE);
                holder.viewPlus.setVisibility(View.GONE);
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
//                    ((MainActivity)context).handleToLongClick();
                } else {
//                    ((MainActivity)context).handleToDetailProduct(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProgressStudy.size();
    }
}
