package com.ambinusian.adab.ui.splash;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.lecturer.MainActivity;
import com.ambinusian.adab.ui.login.ActivityLogin;

import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        UserPreferences userPreferences = new UserPreferences(this);

//        Handler handler = new Handler();
//        handler.postDelayed(() -> {
        if (userPreferences.getUserLoggedIn()) {
            APIManager apiManager = new APIManager(SplashActivity.this);

//                if(userPreferences.getUserPrivilege() == 1){
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                    finish();
//                } else {
//                    startActivity(new Intent(SplashActivity.this, com.ambinusian.adab.ui.student.MainActivity.class));
//                    finish();
//                }

            apiManager.getUserProfile(userPreferences.getUserToken(), new NetworkHelper.getUserProfile() {
                @Override
                public void onResponse(Boolean success, Map<String, Object> userProfile) {
                    if (success) {
                        int privilege = (int) userProfile.get("is_staff");
                        String department = (String) userProfile.get("department");
                        String username = (String) userProfile.get("user_id");
                        String name = (String) userProfile.get("user_name");

                        userPreferences.setUserUsername(username);
                        userPreferences.setUserName(name);
                        userPreferences.setUserDepartement(department);
                        userPreferences.setUserPrivilege(privilege);

                        if (privilege == 0) {
                            // student
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();

                        } else if (privilege == 1) {
                            // dosen
                            startActivity(new Intent(SplashActivity.this, com.ambinusian.adab.ui.lecturer.MainActivity.class));
                            finish();

                        }
                    }
                }

                @Override
                public void onError(int errorCode, String errorReason) {

                }
            });
        } else {
            startActivity(new Intent(SplashActivity.this, ActivityLogin.class));
            finish();
        }
//        }, 0);


    }
}
