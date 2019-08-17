package com.ambinusian.adab.ui.student.main.schedulereyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder> {
    private Context context;
    private ArrayList<ScheduleModel> lists;

    public ScheduleAdapter(Context context, ArrayList<ScheduleModel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_schedule,parent,false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        ScheduleModel item = lists.get(position);
        //set all class information to holder
        holder.scheduleClassType.setText(item.getClassType());
        holder.scheduleClassTitle.setText(item.getClassTitle());
        holder.scheduleCourse.setText(item.getCourse());
        holder.scheduleCourseCode.setText(item.getCourseCode());
        holder.scheduleClassRoom.setText(item.getClassRoom());
        holder.scheduleClassTime.setText(item.getClassTime());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
