package com.ambinusian.adab.ui.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.student.splash.SplashActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    MaterialButton btnLogin;
    TextInputEditText inputNim;
    TextInputEditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        inputNim = findViewById(R.id.input_nim);
        inputPassword = findViewById(R.id.input_password);

        btnLogin.setOnClickListener(view -> {
            doLogin();
        });
    }

    private void doLogin() {
        Boolean valid = true;

        if (inputPassword.length() < 1) {
            valid = false;
            inputPassword.setError(getString(R.string.error_password_empty));
            inputPassword.requestFocus();
        }

        if (inputNim.length() < 10) {
            valid = false;
            inputNim.setError(getString(R.string.error_nim_invalid));
            inputNim.requestFocus();
        }

        if (valid) {
            Context context = this;
            APIManager apiManager = new APIManager(context);

            apiManager.authenticateUser(inputNim.getText().toString(), inputPassword.getText().toString(), new NetworkHelper.authenticateUser() {
                @Override
                public void onResponse(Boolean success, Map<String, Object> userProfile) {
                    if (success) {
                        UserPreferences userPreferences = new UserPreferences(context);
                        userPreferences.setUserLoggedIn(true);
                        userPreferences.setUserUsername((String) userProfile.get("username"));
                        userPreferences.setUserToken((String) userProfile.get("token_id"));
                        startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                        finish();
                    }
                }

                @Override
                public void onError(int errorCode, String errorReason) {
                    if (errorCode == 403) {
                        inputNim.setError(getString(R.string.username_or_password_invalid));
                        inputPassword.setError(getString(R.string.username_or_password_invalid));
                        inputNim.requestFocus();
                    } else {
                        Toast.makeText(context, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
