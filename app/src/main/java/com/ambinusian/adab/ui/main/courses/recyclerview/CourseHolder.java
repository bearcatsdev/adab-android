package com.ambinusian.adab.ui.main.courses.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.ui.livesession.LiveSessionActivity;
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

        classIcon = itemView.findViewById(R.id.classIcon);

//        Chip
        courseCode = itemView.findViewById(R.id.chip_coursesCode);
        classCode = itemView.findViewById(R.id.chip_classCode);
        classType = itemView.findViewById(R.id.chip_classType);

        //on click course
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), LiveSessionActivity.class);
                String courseId = "00123123";

                //set all data to bundle
                Bundle bundle = new Bundle();
                bundle.putString("courseid",courseId);

                //set bundle to the intent
                intent.putExtras(bundle);

                //go to LiveSessionaActivity
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
