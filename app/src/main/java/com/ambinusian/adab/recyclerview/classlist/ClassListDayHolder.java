package com.ambinusian.adab.recyclerview.classlist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ambinusian.adab.R;

public class ClassListDayHolder extends RecyclerView.ViewHolder {
    TextView day;
    public ClassListDayHolder(@NonNull View itemView) {
        super(itemView);
        itemView = itemView.findViewById(R.id.tv_class_list_day);
    }
}
