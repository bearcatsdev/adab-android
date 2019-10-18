package com.ambinusian.adab.ui.lecturer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.course.CourseAdapter;
import com.ambinusian.adab.recyclerview.course.CourseModel;
import com.ambinusian.adab.recyclerview.discussion.DiscussionAdapter;
import com.ambinusian.adab.recyclerview.discussion.DiscussionModel;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;
import com.ambinusian.adab.ui.student.NextClassActivity;
import com.ambinusian.adab.utility.TextUtility;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FragmentHome extends Fragment {
    private ArrayList<DiscussionModel> discussionList;
    private LinearLayoutManager linearLayoutManager;
    private TextView welcomeTitle;
    private ImageView liveClassIcon;
    private UserPreferences userPreferences;
    private ImageView nextScheduleIcon;
    private TextView nextScheduleTime,nextScheduleClassTitle, nextScheduleCourse, nextScheduleSession;
    private Chip nextScheduleCourseCode, nextScheduleClassCode, nextScheduleClassType;
    private LinearLayout linearLayoutNextClass, welcomeLayout, liveLayout;
    private TextView liveClassTitle, liveCourse, liveClassMeeting;
    private MaterialButton seeAllNextSchedule;
    private ClassDatabase db;
    private ClassEntity liveClass;
    private LinearLayout yourNextClassLayout, latestClassLayout;
    private ArrayList<CourseModel> coursesList;
    private RecyclerView coursesRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecturer_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        welcomeTitle = view.findViewById(R.id.tv_welcomeTitle);
        nextScheduleIcon = view.findViewById(R.id.nextScheduleIcon);
        nextScheduleTime = view.findViewById(R.id.tv_nextScheduleTime);
        nextScheduleClassTitle = view.findViewById(R.id.tv_nextScheduleClassTitle);
        nextScheduleCourse  = view.findViewById(R.id.tv_nextScheduleCourse);
        nextScheduleSession = view.findViewById(R.id.tv_nextScheduleSession);
        nextScheduleCourseCode = view.findViewById(R.id.chip_nextScheduleCourseCode);
        nextScheduleClassCode = view.findViewById(R.id.chip_nextScheduleClassCode);
        nextScheduleClassType = view.findViewById(R.id.chip_nextScheduleClassType);
        linearLayoutNextClass =  view.findViewById(R.id.linear_layout_main);
        welcomeLayout = view.findViewById(R.id.lecturerWelcomeLayout);
        liveClassIcon = view.findViewById(R.id.lecturerLiveClassIcon);
        liveLayout = view.findViewById(R.id.lecturerLiveLayout);
        liveClassTitle = view.findViewById(R.id.tv_lecturerLiveClassTitle);
        liveCourse  = view.findViewById(R.id.tv_lecturerLiveCourse);
        liveClassMeeting = view.findViewById(R.id.tv_lecturerLiveClassMeeting);
        discussionList = new ArrayList<>();
        userPreferences = new UserPreferences(getContext());
        seeAllNextSchedule = view.findViewById(R.id.see_all_next_schedule);
        yourNextClassLayout = view.findViewById(R.id.your_next_class_layout);
        latestClassLayout = view.findViewById(R.id.latest_class_layout);
        db = ClassDatabase.getDatabase(getContext());
        coursesList = new ArrayList<>();
        coursesRecyclerView = view.findViewById(R.id.rv_latest_class);

        ////Set Welcome Text
        welcomeTitle.setText(getContext().getString(R.string.welcome_title, userPreferences.getUserName()));

        ////Your Next Schedule
        db.classDAO().getAllClass().observe(getActivity(), new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                liveClass = null;
                for(ClassEntity classEntity : classEntities){
                    Date date1 = null;
                    Date date2 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(classEntity.getSessionStartDate());
                        date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(classEntity.getSessionEndDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(Calendar.getInstance().getTime().after(date1) && Calendar.getInstance().getTime().before(date2)){
                        liveClass = classEntity;
                        break;
                    }
                }

                if(liveClass == null){
                    welcomeLayout.setVisibility(View.VISIBLE);
                    liveLayout.setVisibility(View.GONE);
//                    welcomeTitle.setText(getString(R.string.welcome_title, TextUtility.toTitleCase(userPreferences.getUserName().toUpperCase())));
                }
                else{
                    liveLayout.setVisibility(View.VISIBLE);
                    welcomeLayout.setVisibility(View.GONE);
                    liveClassIcon.setImageResource( R.drawable.ic_class_59_pencilpaper);
                    liveClassTitle.setText((String) liveClass.getTopicTitle());
                    liveCourse.setText((String) liveClass.getCourseName());
                    String meeting = "Session " + liveClass.getSessionTh();
                    liveClassMeeting.setText(meeting);

                    liveLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(), com.ambinusian.adab.ui.student.ActivityLive.class);
                            int sessionId = liveClass.getSessionId();

                            //set all data to bundle
                            Bundle bundle = new Bundle();
                            bundle.putInt("session_id", sessionId);

                            //set bundle to the intent
                            intent.putExtras(bundle);

                            //go to ActivityLive
                            startActivity(intent);
                        }
                    });
                }

                int nextClass;
                Date currentDate  = Calendar.getInstance().getTime();

                //set list data for recycler view
                Date date = null;
                for(nextClass=0;nextClass<classEntities.size();nextClass++){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
                    try {
                        date = format.parse(classEntities.get(nextClass).getSessionStartDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(currentDate.before(date)) {
                        break;
                    }
                }

                //for next class layout
                if(nextClass == classEntities.size()){
                    yourNextClassLayout.setVisibility(View.GONE);
                }
                else {
                    //Next Class
                    ClassEntity next_class_info = classEntities.get(nextClass);
                    nextScheduleIcon.setImageResource(R.drawable.ic_class_56_pencilnote);
                    nextScheduleTime.setText(new SimpleDateFormat("EEEE, d MMMM YYYY H:MM").format(date));
                    nextScheduleClassTitle.setText(next_class_info.getTopicTitle());
                    nextScheduleCourse.setText(next_class_info.getCourseName());
                    nextScheduleSession.setText("Session "+next_class_info.getSessionTh());
                    nextScheduleCourseCode.setText(next_class_info.getCourseId());
                    nextScheduleClassCode.setText(next_class_info.getClassName());
                    nextScheduleClassType.setText(next_class_info.getSessionMode());
                }

                //for latest class layout
                if(nextClass == 0){
                    latestClassLayout.setVisibility(View.GONE);
                }
                else {
                    for (int i = nextClass - 1; i >= 0; i--) {
                        Date class_date = null;
                        try {
                            class_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(classEntities.get(i).getSessionStartDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        coursesList.add(new CourseModel(classEntities.get(i).getSessionId(),
                                1,
                                new SimpleDateFormat("EEEE, d MMMM YYYY h:mm").format(class_date),
                                classEntities.get(i).getTopicTitle(),
                                classEntities.get(i).getCourseName(),
                                "Session " + classEntities.get(i).getSessionTh(),
                                classEntities.get(i).getCourseId(),
                                classEntities.get(i).getClassName(),
                                classEntities.get(i).getSessionMode()
                        ));
                    }

                    //set adapter for recycler view
                    coursesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    coursesRecyclerView.setAdapter(new CourseAdapter(getContext(), coursesList));
                    //set visible
                    coursesRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });



//        ////Discussion Recycler View
//        //add Dummy Data
//        discussionList.add(new DiscussionModel("Thursday, 22 June 2019","Saya Binusian 2022","Chandra","on Storage(MOBI6009)"));
//        discussionList.add(new DiscussionModel("Thursday, 22 June 2019","Saya Binusian 2022","Chandra","on Storage(MOBI6009)"));
//        discussionList.add(new DiscussionModel("Thursday, 22 June 2019","Saya Binusian 2022","Chandra","on Storage(MOBI6009)"));
//
//        //set layout manager and adapter for discussionRecyclerView
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        discussionRecyclerView.setLayoutManager(linearLayoutManager);
//
//        DiscussionAdapter adapter = new DiscussionAdapter(getContext(),discussionList);
//        discussionRecyclerView.setAdapter(adapter);
//
//        liveClassIcon.setImageResource(R.drawable.ic_class_58_pencilbook);
//        liveClassTitle.setText("Storage");
//        liveCourse.setText("Mobile Object Oriented Programming");
//        liveClassMeeting.setText("Meeting 11");
//        liveLayout.setOnClickListener(view12 -> {
//            Bundle bundle = new Bundle();
//            bundle.putString("class_id","12");
//            Intent intent = new Intent(getActivity(), ActivityLive.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        });

        seeAllNextSchedule.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), NextClassActivity.class);
            startActivity(intent);
        });

    }
}
