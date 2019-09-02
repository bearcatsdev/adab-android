package com.ambinusian.adab.ui.lecturer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.ui.lecturer.home.LecturerHomeFragment;
import com.ambinusian.adab.ui.userprofile.UserProfileDialogFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    LottieAnimationView profilePicture;
    NavigationView navigationLecturer;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_main);

        toolbar = findViewById(R.id.tool_bar);
        profilePicture = findViewById(R.id.circle_image_profile_picture);
        navigationLecturer = findViewById(R.id.nv_adab_lecturer);
        drawerLayout = findViewById(R.id.mDrawerLayoutLecturer);

        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        // profile picture onclick
        profilePicture.setOnClickListener(v -> showUserProfileDialog());

        //toolbar icon clicker
        toolbar.setNavigationOnClickListener((View view) -> drawerLayout.openDrawer(GravityCompat.START));

        //set first fragment launched
        getSupportFragmentManager().beginTransaction().replace(R.id.adab_lecturer_fragment,new LecturerHomeFragment()).commit();

    }

    private void showUserProfileDialog() {
        FragmentManager fm = getSupportFragmentManager();
        UserProfileDialogFragment userProfileDialogFragment = new UserProfileDialogFragment();
        userProfileDialogFragment.show(fm, "fragment_user_profile_dialog");
    }
}
