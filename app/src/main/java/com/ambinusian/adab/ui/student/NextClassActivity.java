package com.ambinusian.adab.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ambinusian.adab.R;
import com.ambinusian.adab.recyclerview.nextorlatestclass.NextOrLatestClassAdapter;
import com.ambinusian.adab.recyclerview.nextorlatestclass.NextOrLatestClassModel;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class NextClassActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView nextClassRecyclerView;
    private ArrayList<NextOrLatestClassModel> nextClassList;
    private Date date1, date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_next_class);

        toolbar = findViewById(R.id.next_class_toolbar);
        nextClassRecyclerView  = findViewById(R.id.next_class_recycler_view);
        nextClassList = new ArrayList<>();

        ClassDatabase.getDatabase(this).classDAO().getAllClass().observe(this, classEntities -> {
            for (ClassEntity  classEntity : classEntities) {
                Date classDate = null;
                try {
                    classDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(classEntity.getSessionStartDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (Calendar.getInstance().getTime().before(classDate)) {
                    //int classIcon, int classId, String classTopic, String session, String room, String time, String date
                    try {
                        nextClassList.add(new NextOrLatestClassModel(
                                1,
                                classEntity.getSessionId(),
                                classEntity.getTopicTitle(),
                                "Session "+classEntity.getSessionTh(),
                                classEntity.getSessionRoom(),
                                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(classDate),
                               ""
                        ));
                    } catch (Exception e) {}
                }
            }

            //sort by date
            //using bubble sort
            int listSize = nextClassList.size();
            for(int i = 0; i<listSize-1;i++){
                for(int j = i+1; j<listSize;j++){
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(nextClassList.get(i).getTime());
                        date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(nextClassList.get(j).getTime());

                        if(date2.before(date1)){
                            Collections.swap(nextClassList,i,j);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            //grouping date

            Date dateTemp = null;
            Date dateIterator = null;

            Log.d("size",nextClassList.size()+"");

            if(nextClassList.size() != 0){
                int i = 0;
                while(i < nextClassList.size()){
                    try {
                        dateTemp = new SimpleDateFormat("yyyy-MM-dd").parse(nextClassList.get(i).getTime());
                        if(i == 0){
                            dateIterator = dateTemp;
                            nextClassList.add(i, new NextOrLatestClassModel(1,
                                    nextClassList.get(i).getClassId(),
                                    nextClassList.get(i).getClassTopic(),
                                    nextClassList.get(i).getSession(),
                                    nextClassList.get(i).getRoom(),
                                    nextClassList.get(i).getTime(),
                                    new SimpleDateFormat("EEEE, d MMMM YYYY").format(dateIterator)));
                            i+=2;
                        }else if(!dateIterator.equals(dateTemp)){
                            dateIterator = dateTemp;
                            nextClassList.add(i, new NextOrLatestClassModel(1,
                                    nextClassList.get(i).getClassId(),
                                    nextClassList.get(i).getClassTopic(),
                                    nextClassList.get(i).getSession(),
                                    nextClassList.get(i).getRoom(),
                                    nextClassList.get(i).getTime(),
                                    new SimpleDateFormat("EEEE, d MMMM YYYY").format(dateIterator)));
                            i+= 2;
                        }
                        else
                        {
                            i++;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                nextClassRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                NextOrLatestClassAdapter adapter = new NextOrLatestClassAdapter(this,nextClassList);
                nextClassRecyclerView.setAdapter(adapter);
            }
        });

        //add dummies data
//        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 11:00",""));
//        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 12:00",""));
//        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 13:00",""));
//        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 14:00",""));
//        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-29 15:00",""));
//        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-29 16:00",""));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
