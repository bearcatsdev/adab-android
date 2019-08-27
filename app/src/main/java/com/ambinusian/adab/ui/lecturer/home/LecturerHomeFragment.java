package com.ambinusian.adab.ui.lecturer.home;

import android.content.Context;
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
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.discussionrecyclerview.DiscussionAdapter;
import com.ambinusian.adab.recyclerview.discussionrecyclerview.DiscussionModel;
import com.ambinusian.adab.ui.lecturer.main.MainActivity;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class LecturerHomeFragment extends Fragment {

    RecyclerView discussionRecyclerView;
    ArrayList<DiscussionModel> discussionList;
    LinearLayoutManager linearLayoutManager;
    TextView welcomeTitle;
    UserPreferences userPreferences;
    ImageView nextScheduleIcon;
    TextView nextScheduleTime,nextScheduleClassTitle, nextScheduleCourse, nextScheduleSession;
    Chip nextScheduleCourseCode, nextScheduleClassCode, nextScheduleClassType;

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
        discussionList = new ArrayList<>();
        userPreferences = new UserPreferences(getContext());


        ////Set Welcome Text
        welcomeTitle.setText(getString(R.string.welcome_title, userPreferences.getUserName()));

        ////Your Next Schedule
        //add Dummy data
        nextScheduleIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
        nextScheduleTime.setText("Monday, 26 August 2019");
        nextScheduleClassTitle.setText("Storage");
        nextScheduleCourse.setText("Mobile Object Oriented Programming");
        nextScheduleSession.setText("Session 9");
        nextScheduleCourseCode.setText("MOBI6002");
        nextScheduleClassCode.setText("LA03");
        nextScheduleClassType.setText("LEC");

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
