package tronglv.bd.fpolyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ListSchedulesResponseDTO;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;
import tronglv.bd.fpolyapp.models.Notification;
import tronglv.bd.fpolyapp.models.Schedule;
import tronglv.bd.fpolyapp.viewHolders.ScheduleViewHolder;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    Context context;
    ArrayList<ListSchedulesResponseDTO.Schedule> listSchedule;

    public ScheduleAdapter(Context context, ArrayList<ListSchedulesResponseDTO.Schedule> listSchedule) {
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
        ListSchedulesResponseDTO.Schedule schedule = listSchedule.get(holder.getAdapterPosition());

        String codeCourse = schedule.getCourse_name().substring(schedule.getCourse_name().indexOf("-") + 1);//mã môn
        String nameCourse = schedule.getCourse_name();//tên môn
        String day = schedule.getDate(); //ngày học
        String address = schedule.getAddress(); //địa chỉ
        String room = schedule.getRoom(); //phòng học
        String time = schedule.getTime(); //giờ học
        String className = schedule.getClass_name(); //lớp học
        String teacherName = schedule.getTeacher_name(); //giảng viên
        String slot = schedule.getTime(); //ca học

        holder.txtDay.setText(day);
        holder.txtRoom.setText(room + " - Ca: " + slot);
        holder.txtNameCourse.setText(nameCourse);

        holder.btnMoreSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).showDetaiSchedule(schedule.getId());
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
//                    ((MainActivity)context).handleToLongClick();
                }else {
                    ((MainActivity)context).showDetaiSchedule(schedule.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSchedule.size();
    }
}
