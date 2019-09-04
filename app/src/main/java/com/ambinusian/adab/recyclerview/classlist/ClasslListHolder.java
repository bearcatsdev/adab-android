package com.ambinusian.adab.recyclerview.classlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ambinusian.adab.R;

public class ClasslListHolder extends RecyclerView.ViewHolder {
    public ImageView classIcon;
    public TextView classTopic, meeting, time;
    public ClasslListHolder(@NonNull View itemView) {
        super(itemView);
        classIcon = itemView.findViewById(R.id.class_list_icon);
        classTopic = itemView.findViewById(R.id.tv_class_list_topic);
        meeting = itemView.findViewById(R.id.tv_class_list_meeting);
        time = itemView.findViewById(R.id.tv_class_list_time);
    }
}
