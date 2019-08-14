package com.ambinusian.adab.ui.splash;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.login.LoginActivity;
import com.ambinusian.adab.ui.main.MainActivity;

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

        if (userPreferences.getUserLoggedIn()) {
            APIManager apiManager = new APIManager(this);

            apiManager.getUserProfile(userPreferences.getUserToken(), new NetworkHelper.getUserProfile() {
                @Override
                public void onResponse(Boolean success, Map<String, Object> userProfile) {
                    if (success) {
                        int privilege = (int) userProfile.get("privilege");
                        String department = (String) userProfile.get("department");
                        String username = (String) userProfile.get("username");
                        String name = (String) userProfile.get("name");

                        userPreferences.setUserUsername(username);
                        userPreferences.setUserName(name);
                        userPreferences.setUserDepartement(department);
                        userPreferences.setUserPrivilege(privilege);

                        if (privilege == 2) {
                            // student
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();

                        } else if (privilege == 1) {
                            // dosen
                            Toast.makeText(SplashActivity.this, "Coming soon", Toast.LENGTH_LONG).show();

                        }
                    }
                }

                @Override
                public void onError(int errorCode, String errorReason) {

                }
            });
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }
}
