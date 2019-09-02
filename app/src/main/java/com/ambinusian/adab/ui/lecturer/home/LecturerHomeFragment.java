package com.ambinusian.adab.ui.lecturer.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.discussionrecyclerview.DiscussionAdapter;
import com.ambinusian.adab.recyclerview.discussionrecyclerview.DiscussionModel;
import com.ambinusian.adab.ui.lecturer.LecturerLiveSessionActivity;
import com.ambinusian.adab.ui.lecturer.main.MainActivity;
import com.google.android.material.chip.Chip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class LecturerHomeFragment extends Fragment {

    private RecyclerView discussionRecyclerView;
    ArrayList<DiscussionModel> discussionList;
    LinearLayoutManager linearLayoutManager;
    TextView welcomeTitle;
    UserPreferences userPreferences;
    ImageView nextScheduleIcon;
    TextView nextScheduleTime,nextScheduleClassTitle, nextScheduleCourse, nextScheduleSession;
    Chip nextScheduleCourseCode, nextScheduleClassCode, nextScheduleClassType;
    LinearLayout linearLayoutNextClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecturer_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        discussionRecyclerView = view.findViewById(R.id.rv_discussions);
        welcomeTitle = view.findViewById(R.id.tv_welcomeTitle);
        nextScheduleIcon = view.findViewById(R.id.nextScheduleIcon);
        nextScheduleTime = view.findViewById(R.id.tv_nextScheduleTime);
        nextScheduleClassTitle = view.findViewById(R.id.tv_nextScheduleClassTitle);
        nextScheduleCourse  = view.findViewById(R.id.tv_nextScheduleCourse);
        nextScheduleSession = view.findViewById(R.id.tv_nextScheduleSession);
        nextScheduleCourseCode = view.findViewById(R.id.chip_nextScheduleCourseCode);
        nextScheduleClassCode = view.findViewById(R.id.chip_nextScheduleClassCode);
        nextScheduleClassType = view.findViewById(R.id.chip_nextScheduleClassType);
        linearLayoutNextClass = view.findViewById(R.id.linear_layout_main);
        discussionList = new ArrayList<>();
        userPreferences = new UserPreferences(getContext());


        ////Set Welcome Text
        welcomeTitle.setText(getString(R.string.welcome_title, userPreferences.getUserName()));

        ////Your Next Schedule
        APIManager apiManager = new APIManager(getContext());
        apiManager.getUserClasses(userPreferences.getUserToken(), new NetworkHelper.getUserClasses() {
            @Override
            public void onResponse(Boolean success, Map<String, Object>[] userClasses) {
                if (success) {

                    Date dateNow = new Date();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateNow);
                    calendar.add(Calendar.MINUTE, -200);

                    for (Map<String, Object> userClass : userClasses) {

                        Date startDate = null;
                        try {
                            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                    .parse(userClass.get("transaction_date") + " " + userClass.get("transaction_time"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        if (((int) userClass.get("is_done")) == 0 && dateNow.before(startDate)) {
                            String courseName = (String) userClass.get("course_name");
                            String topic = (String) userClass.get("topic");
                            String courseCode = (String) userClass.get("course_code");
                            String classCode = (String) userClass.get("class_code");
                            String classType = (String) userClass.get("class_type");
                            String classDate = classDate = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm", Locale.getDefault())
                                    .format(startDate);

                            int classId = (int) userClass.get("transaction_Id");
                            int session = Integer.parseInt(((String) userClass.get("session")));

                            nextScheduleIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
                            nextScheduleTime.setText(classDate);
                            nextScheduleClassTitle.setText(topic);
                            nextScheduleCourse.setText(courseName);
                            nextScheduleSession.setText(getString(R.string.class_session, session));
                            nextScheduleCourseCode.setText(courseCode);
                            nextScheduleClassCode.setText(classCode);
                            nextScheduleClassType.setText(classType);

                            linearLayoutNextClass.setOnClickListener(v -> {
                                Intent intent = new Intent(getContext(), LecturerLiveSessionActivity.class);
                                //set all data to bundle
                                Bundle bundle = new Bundle();
                                bundle.putInt("class_id", classId);

                                //set bundle to the intent
                                intent.putExtras(bundle);

                                //go to LiveSessionActivity
                                startActivity(intent);
                            });
                            break;
                        }
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });

        ////Discussion Recycler View
        //add Dummy Data
        discussionList.add(new DiscussionModel("Thursday, 22 June 2019","Saya Binusian 2022","Chandra","on Storage(MOBI6009)"));
        discussionList.add(new DiscussionModel("Thursday, 22 June 2019","Saya Binusian 2022","Chandra","on Storage(MOBI6009)"));
        discussionList.add(new DiscussionModel("Thursday, 22 June 2019","Saya Binusian 2022","Chandra","on Storage(MOBI6009)"));

        //set layout manager and adapter for discussionRecyclerView
        linearLayoutManager = new LinearLayoutManager(getContext());
        discussionRecyclerView.setLayoutManager(linearLayoutManager);

        DiscussionAdapter adapter = new DiscussionAdapter(getContext(),discussionList);
        discussionRecyclerView.setAdapter(adapter);
    }
}
