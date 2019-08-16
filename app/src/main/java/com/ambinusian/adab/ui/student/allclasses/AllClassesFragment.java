package com.ambinusian.adab.ui.student.allclasses;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.student.main.courses.recyclerview.CourseAdapter;
import com.ambinusian.adab.ui.student.main.courses.recyclerview.CourseModel;
import com.ambinusian.adab.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AllClassesFragment extends Fragment {

    private RecyclerView coursesRecyclerView;
    private ArrayList<CourseModel> coursesList;
    private LinearLayout liveLayout;
    private ImageView liveClassIcon;
    private LinearLayoutManager linearLayoutManager;
    private TextView liveClassTitle, liveCourse, liveClassMeeting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_classes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coursesRecyclerView = view.findViewById(R.id.rv_courses);
        liveLayout = view.findViewById(R.id.liveLayout);
        liveClassIcon = view.findViewById(R.id.liveClassIcon);
        liveClassTitle  = view.findViewById(R.id.tv_liveClassTitle);
        liveCourse = view.findViewById(R.id.tv_liveCourse);
        liveClassMeeting = view.findViewById(R.id.tv_liveClassMeeting);
        coursesList = new ArrayList<>();

        // set visibility gone
        coursesRecyclerView.setVisibility(View.GONE);

        // get classes data
        APIManager apiManager = new APIManager(getContext());
        UserPreferences userPreferences = new UserPreferences(getContext());
        apiManager.getUserClasses(userPreferences.getUserToken(), new NetworkHelper.getUserClasses() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onResponse(Boolean success, Map<String, Object>[] userClasses) {
                if (success) {
                    //set layout manager for recycler view
                    linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setReverseLayout(true);
                    coursesRecyclerView.setLayoutManager(linearLayoutManager);

                    for (Map<String, Object> userClass: userClasses) {
                        // parse date
                        String classDate = "";
                        String classTime = "";
                        try {
                            Date startDate = new SimpleDateFormat("yyyy-MM-dd")
                                    .parse((String) userClass.get("transaction_date"));
                            classDate = new SimpleDateFormat("EEEE, d MMMM yyyy")
                                    .format(startDate);

                            Date startTime = new SimpleDateFormat("HH:mm:ss")
                                    .parse((String) userClass.get("transaction_time"));
                            classTime = new SimpleDateFormat("HH:mm")
                                    .format(startTime);

                        } catch (ParseException e) { e.printStackTrace(); }

                        //set list data for recycler view
                        coursesList.add(new CourseModel(
                                (int) Integer.parseInt((String) userClass.get("session")),
                                (int) userClass.get("class_icon"),
                                classDate + " " + classTime,
                                (String) userClass.get("topic"),
                                (String) userClass.get("course_name"),
                                getString(R.string.class_session) + " " + userClass.get("session"),
                                (String) userClass.get("course_code"),
                                (String) userClass.get("class_code"),
                                (String) userClass.get("class_type")
                        ));
                    }

                    //set adapter for recycler view
                    coursesRecyclerView.setAdapter(new CourseAdapter(getContext(),coursesList));

                    //set visible
                    coursesRecyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });

        //if live class is starting
        if(false){
            liveLayout.setVisibility(View.VISIBLE);
            liveClassIcon.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_class_59_pencilpaper));
            liveClassTitle.setText("Design");
            liveCourse.setText("Design");
            liveClassMeeting.setText("Meeting 99");
        }


    }
}
