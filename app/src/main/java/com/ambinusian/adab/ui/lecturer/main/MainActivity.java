package com.ambinusian.adab.ui.lecturer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.discussionrecyclerview.DiscussionAdapter;
import com.ambinusian.adab.recyclerview.discussionrecyclerview.DiscussionModel;
import com.ambinusian.adab.ui.lecturer.LecturerLiveSessionActivity;
import com.ambinusian.adab.ui.lecturer.home.LecturerHomeFragment;
import com.ambinusian.adab.ui.student.allclasses.AllClassesFragment;
import com.ambinusian.adab.ui.userprofile.UserProfileDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    CircleImageView profilePicture;
    NavigationView navigationLecturer;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lecturer);

        toolbar = findViewById(R.id.tool_bar);
        profilePicture = findViewById(R.id.circle_image_profile_picture);
        navigationLecturer = findViewById(R.id.nv_adab_lecturer);
        drawerLayout = findViewById(R.id.mDrawerLayoutLecturer);

        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // profile picture onclick
        profilePicture.setOnClickListener(v -> {
            showUserProfileDialog();
        });

        //toolbar icon clickder
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //set first fragment launched
        getSupportFragmentManager().beginTransaction().replace(R.id.adab_lecturer_fragment,new LecturerHomeFragment()).commit();

    }

    private void showUserProfileDialog() {
        FragmentManager fm = getSupportFragmentManager();
        UserProfileDialogFragment userProfileDialogFragment = new UserProfileDialogFragment();
        userProfileDialogFragment.show(fm, "fragment_user_profile_dialog");
    }
}
