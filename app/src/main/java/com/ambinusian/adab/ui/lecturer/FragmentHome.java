package com.ambinusian.adab.ui.lecturer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.ambinusian.adab.recyclerview.discussion.DiscussionAdapter;
import com.ambinusian.adab.recyclerview.discussion.DiscussionModel;
import com.google.android.material.chip.Chip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class FragmentHome extends Fragment {

    private RecyclerView discussionRecyclerView;
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
        welcomeLayout = view.findViewById(R.id.lecturerWelcomeLayout);
        liveClassIcon = view.findViewById(R.id.lecturerLiveClassIcon);
        liveLayout = view.findViewById(R.id.lecturerLiveLayout);
        liveClassTitle = view.findViewById(R.id.tv_lecturerLiveClassTitle);
        liveCourse  = view.findViewById(R.id.tv_lecturerLiveCourse);
        liveClassMeeting = view.findViewById(R.id.tv_lecturerLiveClassMeeting);
        discussionList = new ArrayList<>();
        userPreferences = new UserPreferences(getContext());


        ////Set Welcome Text
        welcomeTitle.setText(getString(R.string.welcome_title, userPreferences.getUserName()));

        ////Your Next Schedule
        APIManager apiManager = new APIManager(getContext());
        apiManager.getUserClasses(userPreferences.getUserToken(), new NetworkHelper.getUserClasses() {
            @SuppressLint("SetTextI18n")
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
                            String classDate = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm", Locale.getDefault())
                                    .format(startDate);

                            int classId = (int) userClass.get("transaction_Id");
                            String session = userClass.get("session") != null ? (String) userClass.get("session"): "null";

                            nextScheduleIcon.setImageResource(R.drawable.ic_class_55_pencilruler);
                            nextScheduleTime.setText(classDate);
                            nextScheduleClassTitle.setText(topic);
                            nextScheduleCourse.setText(courseName);
                            nextScheduleSession.setText("Sessions " + session);
                            nextScheduleCourseCode.setText(courseCode);
                            nextScheduleClassCode.setText(classCode);
                            nextScheduleClassType.setText(classType);

                            linearLayoutNextClass.setOnClickListener(v -> {
                                Intent intent = new Intent(getContext(), ActivityLive.class);
                                //set all data to bundle
                                Bundle bundle = new Bundle();
                                bundle.putInt("class_id", classId);

                                //set bundle to the intent
                                intent.putExtras(bundle);

                                //go to ActivityLive
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

        //show live layout
        liveLayout.setVisibility(View.VISIBLE);
        welcomeLayout.setVisibility(View.GONE);
        liveClassIcon.setImageResource(R.drawable.ic_class_58_pencilbook);
        liveClassTitle.setText("Storage");
        liveCourse.setText("Mobile Object Oriented Programming");
        liveClassMeeting.setText("Meeting 11");
        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("class_id","12");
                Intent intent = new Intent(getActivity(), ActivityLive.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

    }
}
