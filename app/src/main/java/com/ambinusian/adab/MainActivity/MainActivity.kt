package com.ambinusian.adab.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambinusian.adab.MainActivity.CourseRecyclerView.CourseAdapter
import com.ambinusian.adab.MainActivity.CourseRecyclerView.CourseModel
import com.ambinusian.adab.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val coursesList = ArrayList<CourseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(app_bar)

        rv_courses.setHasFixedSize(true)
        rv_courses.layoutManager  = LinearLayoutManager(this)

        coursesList.add(CourseModel("Yesterday","Storage","MOOP","Meeting 11","MOBI6002","LB03","LEC"))
        coursesList.add(CourseModel("Yesterday","Storage","MOOP","Meeting 11","MOBI6002","LB03","LEC"))
        coursesList.add(CourseModel("Yesterday","Storage","MOOP","Meeting 11","MOBI6002","LB03","LEC"))

        val adapter = CourseAdapter(coursesList)
        adapter.notifyDataSetChanged()
        rv_courses.adapter = adapter

    }
}
