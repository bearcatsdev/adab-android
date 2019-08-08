package com.ambinusian.adab.ui.login;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        APIManager apiManager = new APIManager(this);
        apiManager.authenticateUser("admin", "admin", (success, userProfile) -> {
            if (success) {
                Log.d("agaasdas", userProfile.toString());

            } else {

            }
        });
    }
}
