package com.ambinusian.adab.MainActivity.CourseRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ambinusian.adab.R

class CourseAdapter(private val courseList : ArrayList<CourseModel>) : RecyclerView.Adapter<CourseHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_courses, parent, false)
        return CourseHolder(layout)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        if(position < courseList.size){
            val list = courseList.get(position)
            holder.courseTime.text = list.courseTime
            holder.courseTitle.text = list.courseTitle
            holder.course.text = list.course
            holder.courseCode.text = list.courseCode
            holder.courseMeeting.text = list.courseMeeting
            holder.classCode.text = list.classCode
            holder.classType.text = list.classType
        }
    }
}