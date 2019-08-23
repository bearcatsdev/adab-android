package com.ambinusian.adab.ui.lecturer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import com.ambinusian.adab.R;
import com.ambinusian.adab.ui.userprofile.UserProfileDialogFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    CircleImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lecturer);

        toolbar = findViewById(R.id.tool_bar);
        profilePicture = findViewById(R.id.circle_image_profile_picture);

        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // profile picture onclick
        profilePicture.setOnClickListener(v -> {
            showUserProfileDialog();
        });
    }

    private void showUserProfileDialog() {
        FragmentManager fm = getSupportFragmentManager();
        UserProfileDialogFragment userProfileDialogFragment = new UserProfileDialogFragment();
        userProfileDialogFragment.show(fm, "fragment_user_profile_dialog");
    }
}
