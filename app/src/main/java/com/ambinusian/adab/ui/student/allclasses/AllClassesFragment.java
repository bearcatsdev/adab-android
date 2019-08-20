package com.ambinusian.adab.ui.student.allclasses;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.ambinusian.adab.ui.student.livesession.LiveSessionActivity;
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
    private LinearLayout liveLayout,welcomeLayout;
    private ImageView liveClassIcon;
    private LinearLayoutManager linearLayoutManager;
    private TextView liveClassTitle, liveCourse, liveClassMeeting;
    private TextView welcomeTitle;

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
        welcomeLayout = view.findViewById(R.id.welcomeLayout);
        welcomeTitle = view.findViewById(R.id.tv_welcomeTitle);
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

                        if ((int) userClass.get("is_done") == 1) {

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

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            //set list data for recycler view
                            coursesList.add(new CourseModel(
                                    (int) userClass.get("transaction_Id"),
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

                        if ((int) userClass.get("is_live") == 1) {
                                liveLayout.setVisibility(View.VISIBLE);
                                liveClassIcon.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_class_59_pencilpaper));
                                liveClassTitle.setText((String) userClass.get("topic"));
                                liveCourse.setText((String) userClass.get("course_name"));
                                String meeting = getString(R.string.class_session) + " " + userClass.get("session");
                                liveClassMeeting.setText(meeting);

                                liveLayout.setOnClickListener(v -> {
                                    Intent intent = new Intent(getContext(), LiveSessionActivity.class);
                                    int classId = (int) userClass.get("transaction_Id");

                                    //set all data to bundle
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("class_id", classId);

                                    //set bundle to the intent
                                    intent.putExtras(bundle);

                                    //go to LiveSessionActivity
                                    startActivity(intent);
                                });
                        }
                        else
                        {
                            welcomeLayout.setVisibility(View.VISIBLE);
                            welcomeTitle.setText("Welcome Back, "+userPreferences.getUserName());
                        }
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
    }
}
