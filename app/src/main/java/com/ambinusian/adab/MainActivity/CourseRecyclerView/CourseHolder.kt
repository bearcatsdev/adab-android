package com.ambinusian.adab.MainActivity.CourseRecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambinusian.adab.R
import com.google.android.material.chip.Chip

class CourseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var courseTime: TextView = itemView.findViewById(R.id.tv_coursesTime)
    var courseTitle: TextView = itemView.findViewById(R.id.tv_coursesTitle)
    var course:TextView = itemView.findViewById(R.id.tv_courses)
    var courseMeeting: TextView = itemView.findViewById(R.id.tv_coursesMeeting)
    var courseCode: Chip = itemView.findViewById(R.id.chip_coursesCode)
    var classCode: Chip = itemView.findViewById(R.id.chip_classCode)
    var classType: Chip = itemView.findViewById(R.id.chip_classType)
}
