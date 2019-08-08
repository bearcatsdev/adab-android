package com.ambinusian.adab.main_activity.course_recycler_view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.google.android.material.chip.Chip;

public class CourseHolder extends RecyclerView.ViewHolder {
    TextView classTime, classTitle, courses,classMeeting;
    Chip courseCode, classCode, classType;
    ImageView classIcon;

    public CourseHolder(@NonNull View itemView) {
        super(itemView);
        classTime = itemView.findViewById(R.id.tv_classTime);
        classTitle = itemView.findViewById(R.id.tv_classTitle);
        courses = itemView.findViewById(R.id.tv_courses);
        classMeeting = itemView.findViewById(R.id.tv_classMeeting);
        courseCode = itemView.findViewById(R.id.chip_coursesCode);
        classCode = itemView.findViewById(R.id.chip_classCode);
        classType = itemView.findViewById(R.id.chip_classType);
        classIcon = itemView.findViewById(R.id.classIcon);
    }
}
