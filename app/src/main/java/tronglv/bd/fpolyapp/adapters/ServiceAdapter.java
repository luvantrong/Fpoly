package tronglv.bd.fpolyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tronglv.bd.fpolyapp.R;
import tronglv.bd.fpolyapp.dto.ListNotifyResponseDTO;
import tronglv.bd.fpolyapp.dto.ListServiceResponseDTO;
import tronglv.bd.fpolyapp.interfaces.ItemClickListener;
import tronglv.bd.fpolyapp.viewHolders.NotificationViewHolder;
import tronglv.bd.fpolyapp.viewHolders.ServiceViewHolder;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder>{
    Context context;
    ArrayList<ListServiceResponseDTO.ServiceDTO> listService;

    public ServiceAdapter(Context context, ArrayList<ListServiceResponseDTO.ServiceDTO> listService) {
        this.context = context;
        this.listService = listService;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_service_register, parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ListServiceResponseDTO.ServiceDTO serviceDTO = listService.get(holder.getAdapterPosition());
        String url = serviceDTO.getFile();
        Glide.with(context).load(url).into(holder.imgListService);
        String date = serviceDTO.getDay();
        holder.txtDateList.setText(date);
        String nameService = serviceDTO.getName_service();
        holder.txtNameService.setText(nameService);
        String nameCourse = serviceDTO.getCode_course();
        holder.txtNameServiceCourse.setText("Môn: " + nameCourse);
        String reason = serviceDTO.getReason();
        holder.txtReason.setText("Lý do: " + reason);
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }
}
