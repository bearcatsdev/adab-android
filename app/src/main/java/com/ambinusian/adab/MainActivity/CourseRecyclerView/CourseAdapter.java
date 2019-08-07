package com.ambinusian.adab.MainActivity.CourseRecyclerView;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

    private Context context;
    private ArrayList<CourseModel> list;

    public CourseAdapter(Context context, ArrayList<CourseModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_courses,parent,false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        CourseModel item = list.get(position);
        holder.classTime.setText(item.getClassTime());
        holder.classTitle.setText(item.getClassTitle());
        holder.courses.setText(item.getCourse());
        holder.classMeeting.setText(item.getClassMeeting());
        holder.courseCode.setText(item.getCourseCode());
        holder.classCode.setText(item.getClassCode());
        holder.classType.setText(item.getClassType());
    }
}
