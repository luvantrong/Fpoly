package tronglv.bd.fpolyapp.adapters;

import android.app.Activity;
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
import tronglv.bd.fpolyapp.models.TestSchedule;
import tronglv.bd.fpolyapp.views.MainActivity;

public class ViewPagerScheduleAdapter extends FragmentStatePagerAdapter {

    private MainActivity mActivity;

    int anInt;
    public ViewPagerScheduleAdapter(@NonNull FragmentManager fm, int anInt, MainActivity activity) {
        super(fm);
        this.anInt = anInt;
        mActivity = activity;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            ArrayList<Schedule> listSchedule = mActivity.getDataSchedule();
            ScheduleFragment scheduleFragment = ScheduleFragment.newInstance(listSchedule);
            return scheduleFragment;
        }

        else{
            ArrayList<TestSchedule> listTestSchedule = mActivity.getDataTestSchedule();
            TestScheduleFragment testScheduleFragment = TestScheduleFragment.newInstance(listTestSchedule);
            return testScheduleFragment;
        }
    }

    @Override
    public int getCount() {
        return anInt;
    }
}
