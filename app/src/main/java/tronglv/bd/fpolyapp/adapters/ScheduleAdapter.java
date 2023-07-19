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
import tronglv.bd.fpolyapp.models.Schedule;
import tronglv.bd.fpolyapp.viewHolders.ScheduleViewHolder;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    Context context;
    List<Schedule> listSchedule;

    public ScheduleAdapter(Context context, List<Schedule> listSchedule) {
        this.context = context;
        this.listSchedule = listSchedule;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent,false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = listSchedule.get(holder.getAdapterPosition());

        String codeCourse = schedule.getCodeCourse();//mã môn
        String nameCourse = schedule.getNameCourse();//tên môn
        String day = schedule.getDay(); //ngày học
        String address = schedule.getAddress(); //địa chỉ
        String room = schedule.getRoom(); //phòng học
        String time = schedule.getTime(); //giờ học
        String className = schedule.getClassName(); //lớp học
        String teacherName = schedule.getTeacherName(); //giảng viên
        String slot = schedule.getSlot(); //ca học

        holder.txtDay.setText(day);
        holder.txtRoom.setText(room + " - " + slot);
        holder.txtNameCourse.setText(nameCourse + " - " +codeCourse);

        holder.btnMoreSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).showDetaiSchedule(schedule);
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
//                    ((MainActivity)context).handleToLongClick();
                }else {
                    ((MainActivity)context).showDetaiSchedule(schedule);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSchedule.size();
    }
}
