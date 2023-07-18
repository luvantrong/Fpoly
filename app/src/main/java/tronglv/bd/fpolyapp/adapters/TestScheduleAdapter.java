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
import tronglv.bd.fpolyapp.models.TestSchedule;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;
import tronglv.bd.fpolyapp.viewHolders.TestScheduleViewHolder;

public class TestScheduleAdapter extends RecyclerView.Adapter<TestScheduleViewHolder>{

    Context context;
    List<TestSchedule> listTestSchedule;

    public TestScheduleAdapter(Context context, List<TestSchedule> listTestSchedule) {
        this.context = context;
        this.listTestSchedule = listTestSchedule;
    }

    @NonNull
    @Override
    public TestScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_testschedule, parent,false);
        return new TestScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestScheduleViewHolder holder, int position) {
        TestSchedule testSchedule = listTestSchedule.get(holder.getAdapterPosition());
        String date = testSchedule.getDate();
        holder.txtDate.setText("Ngày: " +date);
        String room = testSchedule.getRoom();
        holder.txtRoom.setText(room);
        String session = testSchedule.getSession();
        holder.txtSession.setText(" - " +session);
        String subject = testSchedule.getSubject();
        holder.txtSubject.setText(subject);
        String code = testSchedule.getCode();
        holder.txtCode.setText(" (" + code + ")");
        String time = testSchedule.getTime();
        holder.txtTime.setText("Thời gian: "+time);
        String teacher = testSchedule.getTeacher();
        holder.txtTeacher.setText("Giảng viên: "+teacher);


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
        return listTestSchedule.size();
    }

}
