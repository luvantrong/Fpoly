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
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.SubjectStudy;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;
import tronglv.bd.fpolyapp.viewHolders.SubjectStudyViewHolder;

public class SubjectStudyAdapter extends RecyclerView.Adapter<SubjectStudyViewHolder>{

    Context context;
    List<SubjectStudy> listSubjectStudy;

    public SubjectStudyAdapter(Context context, List<SubjectStudy> listSubjectStudy) {
        this.context = context;
        this.listSubjectStudy = listSubjectStudy;
    }

    @NonNull
    @Override
    public SubjectStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject_study, parent,false);
        return new SubjectStudyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectStudyViewHolder holder, int position) {
        SubjectStudy subjectStudy = listSubjectStudy.get(holder.getAdapterPosition());
        String nameSubject = subjectStudy.getNameSubject();
        if (nameSubject.length() > 15) {
            String newStr = nameSubject.substring(0, 15);
            holder.txtNameSubject.setText(newStr + "...");
        } else {
            holder.txtNameSubject.setText(nameSubject);
        }
        String codeSubject = subjectStudy.getCodeSubject();
        holder.txtCodeSubject.setText(codeSubject);
        String formStudy = subjectStudy.getFormStudy();
        holder.txtFormStudy.setText(formStudy);
        String time = subjectStudy.getTime();
        holder.txtTime.setText(time);

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
        return listSubjectStudy.size();
    }
}
