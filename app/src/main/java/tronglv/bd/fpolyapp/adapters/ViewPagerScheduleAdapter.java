package tronglv.bd.fpolyapp.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tronglv.bd.fpolyapp.fragments.ScheduleFragment;
import tronglv.bd.fpolyapp.fragments.TestScheduleFragment;
import tronglv.bd.fpolyapp.models.Schedule;

public class ViewPagerScheduleAdapter extends FragmentStatePagerAdapter {
    int anInt;
    public ViewPagerScheduleAdapter(@NonNull FragmentManager fm, int anInt) {
        super(fm);
        this.anInt = anInt;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            Schedule schedule = new Schedule("MOB403","Android Networking", "15/07/2023", "Phần mềm Quang Trung", "Phòng 308 (Nhà T)","Ca 5", "MD17306", "channn3", "17h30 - 19h30" );
            ArrayList<Schedule> listSchedule = new ArrayList<>();
            listSchedule.add(schedule);
            listSchedule.add(schedule);
            listSchedule.add(schedule);
            listSchedule.add(schedule);

            ScheduleFragment scheduleFragment = ScheduleFragment.newInstance(listSchedule);
            return scheduleFragment;
        }

        else{
            Schedule schedule = new Schedule("MOB403","Android Networking", "15/07/2023", "Phần mềm Quang Trung", "Phòng 308 (Nhà T)","Ca 5", "MD17306", "channn3", "17h30 - 19h30" );
            ArrayList<Schedule> listSchedule = new ArrayList<>();
            listSchedule.add(schedule);
            listSchedule.add(schedule);
            ScheduleFragment scheduleFragment = ScheduleFragment.newInstance(listSchedule);
            return scheduleFragment;
        }
    }

    @Override
    public int getCount() {
        return anInt;
    }
}
