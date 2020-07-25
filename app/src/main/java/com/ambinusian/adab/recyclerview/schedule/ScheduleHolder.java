package com.ambinusian.adab.recyclerview.schedule;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.google.android.material.chip.Chip;

public class ScheduleHolder extends RecyclerView.ViewHolder{
    TextView scheduleClassTitle,scheduleCourse, scheduleClassTime;
    Chip scheduleClassType,scheduleCourseCode,scheduleClassCode;
    public ScheduleHolder(@NonNull View itemView) {
        super(itemView);
        scheduleClassType = itemView.findViewById(R.id.chip_schedule_classType);
        scheduleClassTitle = itemView.findViewById(R.id.tv_schedule_classTitle);
        scheduleCourse = itemView.findViewById(R.id.tv_schedule_course);
        scheduleCourseCode = itemView.findViewById(R.id.chip_schedule_courseCode);
        scheduleClassCode = itemView.findViewById(R.id.chip_schedule_classCode);
        scheduleClassTime = itemView.findViewById(R.id.tv_schedule_classTime);
    }
}