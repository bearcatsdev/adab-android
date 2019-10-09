package com.ambinusian.adab.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ambinusian.adab.R;
import com.ambinusian.adab.recyclerview.nextorlatestclass.NextOrLatestClassAdapter;
import com.ambinusian.adab.recyclerview.nextorlatestclass.NextOrLatestClassModel;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class LatestClassActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView nextClassRecyclerView;
    private ArrayList<NextOrLatestClassModel> nextClassList;
    private Date date1, date2;
    private ClassDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_latest_class);

        toolbar = findViewById(R.id.next_class_toolbar);
        nextClassRecyclerView  = findViewById(R.id.next_class_recycler_view);
        nextClassList = new ArrayList<>();
        db = ClassDatabase.getDatabase(getApplicationContext());

        db.classDAO().getAllClass().observe(LatestClassActivity.this, new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                for(ClassEntity classEntity : classEntities){
                    if(classEntity.getIs_done() == 0){
                        nextClassList.add(new NextOrLatestClassModel(classEntity.getClass_icon(),classEntity.getTransaction_id(),classEntity.getTopic(),classEntity.getSession(),classEntity.getClass_code(),(new SimpleDateFormat("yy-MM-dd hh-mm").format(classEntity.getTransaction_date()+" "+classEntity.getTransaction_time())).toString(),""));
                    }
                }

                //grouping date
                int i = 0;
                Date dateTemp = null;
                Date dateIterator = null;
                while(i < nextClassList.size()){
                    try {
                        dateTemp = new SimpleDateFormat("yy-MM-dd").parse(nextClassList.get(i).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    if(i == 0 || !dateIterator.equals(dateTemp)){
                        dateIterator = dateTemp;

                        nextClassList.add(i,new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-29 16:00", new SimpleDateFormat("EEEE, d MMMM yyyy").format(dateIterator)));

                        i+= 2;
                    }
                    else
                    {
                        i++;
                    }
                }

                nextClassRecyclerView.setLayoutManager(new LinearLayoutManager(LatestClassActivity.this));
                NextOrLatestClassAdapter adapter = new NextOrLatestClassAdapter(LatestClassActivity.this,nextClassList);
                nextClassRecyclerView.setAdapter(adapter);


                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }
        });







    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
