package com.ambinusian.adab.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ambinusian.adab.R;
import com.ambinusian.adab.recyclerview.nextorlatestclass.NextOrLatestClassAdapter;
import com.ambinusian.adab.recyclerview.nextorlatestclass.NextOrLatestClassModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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

        //add dummies data
        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 11:00",""));
        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 12:00",""));
        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 13:00",""));
        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-30 14:00",""));
        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-29 15:00",""));
        nextClassList.add(new NextOrLatestClassModel(1,1,"Introduction to Ubiquitous","Session 11","501 - LB03","2019-09-29 16:00",""));

        //sort by date
        //using bubble sort
        int listSize = nextClassList.size();
        for(int i = 0; i<listSize-1;i++){
            for(int j = i+1; j<listSize;j++){
                try {
                    date1 = new SimpleDateFormat("yy-MM-dd HH:mm").parse(nextClassList.get(i).getTime());
                    date2 = new SimpleDateFormat("yy-MM-dd HH:mm").parse(nextClassList.get(j).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(date2.before(date1)){
                    Collections.swap(nextClassList,i,j);
                }
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

        nextClassRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        NextOrLatestClassAdapter adapter = new NextOrLatestClassAdapter(this,nextClassList);
        nextClassRecyclerView.setAdapter(adapter);


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
