package com.ambinusian.adab.MainActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.MainActivity.CourseRecyclerView.CourseAdapter;
import com.ambinusian.adab.MainActivity.CourseRecyclerView.CourseModel;
import com.ambinusian.adab.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView coursesRecyclerView;
    ArrayList<CourseModel> coursesList;
    ArrayList<String> listSemester;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    Spinner SpinnerlistSemester;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        coursesRecyclerView = findViewById(R.id.rv_courses);
        mDrawerLayout = findViewById(R.id.mDrawerLayout);
        coursesList = new ArrayList<>();
        navigationView = findViewById(R.id.nv_adab);
        SpinnerlistSemester = navigationView.getHeaderView(0).findViewById(R.id.spinner_list_semesters);
        listSemester = new ArrayList<>();

        //set value for spinner
        listSemester.add("2018 Semester 1");
        listSemester.add("2018 Semester 2");

        SpinnerlistSemester.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listSemester));


        //set Action bar
        setSupportActionBar(toolbar);

        //set Navigation View


        //set layout manager for recycler view
        coursesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set list data for recycler view
        coursesList.add(new CourseModel(0,"Yesterday", "Storage", "MOOP","Meeting 11","MOBI009","LA03","LEC"));

        coursesList.add(new CourseModel(0,"Yesterday", "Storage", "MOOP","Meeting 11","MOBI009","LA03","LEC"));
        coursesList.add(new CourseModel(0,"Yesterday", "Storage", "MOOP","Meeting 11","MOBI009","LA03","LEC"));

        //set adapter for recycler view
        coursesRecyclerView.setAdapter(new CourseAdapter(this,coursesList));

        //icon menu clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

}

