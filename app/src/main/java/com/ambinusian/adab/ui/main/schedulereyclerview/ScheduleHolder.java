package com.ambinusian.adab.ui.main.schedulereyclerview;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;

public class ScheduleHolder extends RecyclerView.ViewHolder{
    TextView scheduleClassType, scheduleClassTitle,scheduleCourse, scheduleCourseCode, scheduleClassRoom, scheduleClassTime;
    public ScheduleHolder(@NonNull View itemView) {
        super(itemView);
        scheduleClassType = itemView.findViewById(R.id.tv_schedule_classType);
        scheduleClassTitle = itemView.findViewById(R.id.tv_schedule_classTitle);
        scheduleCourse = itemView.findViewById(R.id.tv_schedule_course);
        scheduleCourseCode = itemView.findViewById(R.id.tv_schedule_courseCode);
        scheduleClassRoom = itemView.findViewById(R.id.tv_schedule_classRoom);
        scheduleClassTime = itemView.findViewById(R.id.tv_schedule_classTime);
    }
}