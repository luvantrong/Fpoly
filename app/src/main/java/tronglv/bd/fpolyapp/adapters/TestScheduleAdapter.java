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
import tronglv.bd.fpolyapp.models.TestSchedule;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;
import tronglv.bd.fpolyapp.viewHolders.TestScheduleViewHolder;

public class TestScheduleAdapter extends RecyclerView.Adapter<TestScheduleViewHolder>{

    Context context;
    ArrayList<ListSchedulesResponseDTO.Schedule> listTestSchedule;

    public TestScheduleAdapter(Context context, ArrayList<ListSchedulesResponseDTO.Schedule> listTestSchedule) {
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
        ListSchedulesResponseDTO.Schedule testSchedule = listTestSchedule.get(holder.getAdapterPosition());
        String date = testSchedule.getDate();
        holder.txtDate.setText("Ngày: " +date);
        String room = testSchedule.getRoom();
        holder.txtRoom.setText(room);
        String session = testSchedule.getTime();
        holder.txtSession.setText(" - Ca:" +session);
        String subject = testSchedule.getCourse_name();
        holder.txtSubject.setText(subject);
//        String code = testSchedule.getCode();
//        holder.txtCode.setText(" (" + code + ")");
        String slot = "";
        switch (String.valueOf(testSchedule.getTime())){
            case "1":
                slot = "7h30 - 9h30";
                break;
            case "2":
                slot = "9h35 - 11h45";
                break;
            case "3":
                slot = "13h00 - 15h00";
                break;
            case "4":
                slot = "15h15 - 17h15";
                break;
            case "5":
                slot = "17h30 - 19h30";
                break;
            case "6":
                slot = "19h30 - 21h30";
                break;
            default:
                slot = "Tự học";
                break;
        }
        holder.txtTime.setText("Thời gian: "+slot);
        String teacher = testSchedule.getTeacher_name();
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
