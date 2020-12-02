package com.ambinusian.adab.recyclerview.schedule;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.course.CourseHolder;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder> {
    private Context context;
    private ArrayList<ScheduleModel> lists;
    private UserPreferences userPreferences;

    public ScheduleAdapter(Context context, ArrayList<ScheduleModel> lists) {
        this.context = context;
        this.lists = lists;
        userPreferences = new UserPreferences(context);
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
        holder.scheduleClassCode.setText(item.getClassRoom());
        holder.scheduleClassTime.setText(item.getClassTime());

        //set text attributes
        setTextSize(holder);
        setTextTypeface(holder);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    private void setTextSize(ScheduleHolder holder){
        //multiple of text size
        float textSize = userPreferences.getTextSize();

        holder.scheduleClassType.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.scheduleClassType.getTextSize() * textSize);
        holder.scheduleClassTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.scheduleClassTitle.getTextSize() * textSize);
        holder.scheduleCourse.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.scheduleCourse.getTextSize() * textSize);
        holder.scheduleCourseCode.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.scheduleCourseCode.getTextSize() * textSize);
        holder.scheduleClassCode.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.scheduleClassCode.getTextSize() * textSize);
        holder.scheduleClassTime.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.scheduleClassTime.getTextSize() * textSize);
    }

    private void setTextTypeface(ScheduleHolder holder){
        //get font type
        Typeface textTypeface = userPreferences.getTextTypeface();
        //set font type for each text view
        holder.scheduleClassType.setTypeface(textTypeface);
        holder.scheduleClassTitle.setTypeface(textTypeface);
        holder.scheduleCourse.setTypeface(textTypeface);
        holder.scheduleCourseCode.setTypeface(textTypeface);
        holder.scheduleClassCode.setTypeface(textTypeface);
        holder.scheduleClassTime.setTypeface(textTypeface);
    }
}
