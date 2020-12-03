package com.ambinusian.adab.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
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
import java.util.Observable;

public class LatestClassActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView latestClassRecyclerView;
    private ArrayList<NextOrLatestClassModel> latestClassList;
    private Date date1, date2;
    private TextView latestClass;
    private UserPreferences userPreferences;
    private ClassDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_latest_class);

        userPreferences = new UserPreferences(this);
        toolbar = findViewById(R.id.latest_class_toolbar);
        latestClass = findViewById(R.id.tv_latest_class);
        latestClassRecyclerView  = findViewById(R.id.latest_class_recycler_view);
        latestClassList = new ArrayList<>();
        db = ClassDatabase.getDatabase(getApplicationContext());

        //set text attributes
        setTextSize();
        setTextTypeface();

        db.classDAO().getAllClass().observe(LatestClassActivity.this, new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                for(ClassEntity classEntity : classEntities){
                    Date classDate = null;
                    try {
                        classDate = new SimpleDateFormat("yy-MM-dd HH:mm").parse(classEntity.getSessionEndDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (Calendar.getInstance().getTime().after(classDate)) {
                        //int classIcon, int classId, String classTopic, String session, String room, String time, String date
                        try {
                            latestClassList.add(new NextOrLatestClassModel(
                                    1,
                                    classEntity.getSessionId(),
                                    classEntity.getTopicTitle(),
                                    "Session "+classEntity.getSessionTh(),
                                    classEntity.getSessionRoom(),
                                    (new SimpleDateFormat("yy-MM-dd HH:mm").format(classEntity.getSessionStartDate())),
                                    ""
                            ));
                        } catch (Exception e) {}
                    }
                }

                //sort by date
                //using bubble sort
                int listSize = latestClassList.size();
                for(int i = 0; i<listSize-1;i++){
                    for(int j = i+1; j<listSize;j++){
                        Log.d("datewkwkwk",latestClassList.get(i).getTime()+" "+latestClassList.get(j).getTime());
                        try {
                            date1 = new SimpleDateFormat("yy-MM-dd HH:mm").parse(latestClassList.get(i).getTime());
                            date2 = new SimpleDateFormat("yy-MM-dd HH:mm").parse(latestClassList.get(j).getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(date2.before(date1)){
                            Collections.swap(latestClassList,i,j);
                        }
                    }
                }

                //grouping date
                int i = 0;
                Date dateTemp = null;
                Date dateIterator = null;
                while(i < latestClassList.size()){
                    try {
                        dateTemp = new SimpleDateFormat("yy-MM-dd").parse(latestClassList.get(i).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    if(i == 0 || !dateIterator.equals(dateTemp)){
                        dateIterator = dateTemp;

                        latestClassList.add(i,new NextOrLatestClassModel(1,latestClassList.get(i).getClassId(),latestClassList.get(i).getClassTopic(),latestClassList.get(i).getSession(),latestClassList.get(i).getRoom(),latestClassList.get(i).getTime(), new SimpleDateFormat("EEEE, d MMMM yyyy").format(dateIterator)));

                        i+= 2;
                    }
                    else
                    {
                        i++;
                    }
                }

                latestClassRecyclerView.setLayoutManager(new LinearLayoutManager(LatestClassActivity.this));
                NextOrLatestClassAdapter adapter = new NextOrLatestClassAdapter(LatestClassActivity.this,latestClassList);
                latestClassRecyclerView.setAdapter(adapter);


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

    private void setTextSize(){
        //multiple of text size
        float textSize = userPreferences.getTextSize();
        //set text size for each text view
        latestClass.setTextSize(TypedValue.COMPLEX_UNIT_PX,latestClass.getTextSize()*textSize);
    }

    private void setTextTypeface(){
        //get font type
        Typeface textTypeface = userPreferences.getTextTypeface();
        //set font type for each text view
        latestClass.setTypeface(textTypeface, latestClass.getTypeface().getStyle());
    }
}
