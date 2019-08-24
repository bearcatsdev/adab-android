package com.ambinusian.adab.ui.lecturer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.student.main.discussionrecyclerview.DiscussionAdapter;
import com.ambinusian.adab.ui.student.main.discussionrecyclerview.DiscussionModel;
import com.ambinusian.adab.ui.userprofile.UserProfileDialogFragment;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    CircleImageView profilePicture;
    RecyclerView discussionRecyclerView;
    ArrayList<DiscussionModel> discussionList;
    LinearLayoutManager linearLayoutManager;
    TextView welcomeTitle;
    UserPreferences userPreferences;
    ImageView nextScheduleIcon;
    TextView nextScheduleTime,nextScheduleClassTitle, nextScheduleCourse, nextScheduleSession;
    Chip nextScheduleCourseCode, nextScheduleClassCode, nextScheduleClassType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lecturer);

        toolbar = findViewById(R.id.tool_bar);
        profilePicture = findViewById(R.id.circle_image_profile_picture);
        discussionRecyclerView = findViewById(R.id.rv_discussions);
        welcomeTitle = findViewById(R.id.tv_welcomeTitle);
        nextScheduleIcon = findViewById(R.id.nextScheduleIcon);
        nextScheduleTime = findViewById(R.id.tv_nextScheduleTime);
        nextScheduleClassTitle = findViewById(R.id.tv_nextScheduleClassTitle);
        nextScheduleCourse  = findViewById(R.id.tv_nextScheduleCourse);
        nextScheduleSession = findViewById(R.id.tv_nextScheduleSession);
        nextScheduleCourseCode = findViewById(R.id.chip_nextScheduleCourseCode);
        nextScheduleClassCode = findViewById(R.id.chip_nextScheduleClassCode);
        nextScheduleClassType = findViewById(R.id.chip_nextScheduleClassType);
        discussionList = new ArrayList<>();
        userPreferences = new UserPreferences(this);


        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // profile picture onclick
        profilePicture.setOnClickListener(v -> {
            showUserProfileDialog();
        });

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
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        discussionRecyclerView.setLayoutManager(linearLayoutManager);

        DiscussionAdapter adapter = new DiscussionAdapter(MainActivity.this,discussionList);
        discussionRecyclerView.setAdapter(adapter);


    }

    private void showUserProfileDialog() {
        FragmentManager fm = getSupportFragmentManager();
        UserProfileDialogFragment userProfileDialogFragment = new UserProfileDialogFragment();
        userProfileDialogFragment.show(fm, "fragment_user_profile_dialog");
    }
}
