package com.ambinusian.adab.recyclerview.course;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.google.android.material.chip.Chip;

public class CourseHolder extends RecyclerView.ViewHolder {
    TextView classTime, classTitle, courses,classMeeting;
    Chip courseCode, classCode, classType;
    ImageView classIcon;
    LinearLayout mainLinearLayout;

    public CourseHolder(@NonNull View itemView) {
        super(itemView);
        classTime = itemView.findViewById(R.id.tv_classTime);
        classTitle = itemView.findViewById(R.id.tv_classTitle);
        courses = itemView.findViewById(R.id.tv_courses);
        classMeeting = itemView.findViewById(R.id.tv_classMeeting);

        classIcon = itemView.findViewById(R.id.classIcon);

        courseCode = itemView.findViewById(R.id.chip_coursesCode);
        classCode = itemView.findViewById(R.id.chip_classCode);
        classType = itemView.findViewById(R.id.chip_classType);

        mainLinearLayout = itemView.findViewById(R.id.linear_layout_main);

        Context context = itemView.getContext();
        UserPreferences userPreferences = new UserPreferences(context);

        if(userPreferences.getHighContrast()){
            mainLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.button2Color));
            courseCode.setChipBackgroundColor(context.getResources().getColorStateList(R.color.button2Color));
            classCode.setChipBackgroundColor(context.getResources().getColorStateList(R.color.button2Color));
            classType.setChipBackgroundColor(context.getResources().getColorStateList(R.color.button2Color));
        }
    }
}
