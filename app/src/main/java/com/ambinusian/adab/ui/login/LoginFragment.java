package com.ambinusian.adab.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ambinusian.adab.ui.splash.SplashActivity;


import java.util.Map;

public class LoginFragment extends Fragment {

    MaterialButton btnLogin;
    TextInputEditText inputNim;
    TextInputEditText inputPassword;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogin = view.findViewById(R.id.btn_login);
        inputNim = view.findViewById(R.id.input_nim);
        inputPassword = view.findViewById(R.id.input_password);
        toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }


    private void doLogin() {
        boolean valid = true;

        if (inputPassword.length() < 1) {
            valid = false;
            inputPassword.setError(getString(R.string.error_password_empty));
            inputPassword.requestFocus();
        }

        if (inputNim.length() != 10 && inputNim.length() != 5) {
            valid = false;
            inputNim.setError(getString(R.string.error_nim_invalid));
            inputNim.requestFocus();
        }

        if (valid) {
            Context context = getContext();
            APIManager apiManager = new APIManager(context);

            apiManager.authenticateUser(inputNim.getText().toString(), inputPassword.getText().toString(), new NetworkHelper.authenticateUser() {
                @Override
                public void onResponse(Boolean success, Map<String, Object> userProfile) {
                    if (success) {
                        UserPreferences userPreferences = new UserPreferences(context);
                        userPreferences.setUserLoggedIn(true);
                        userPreferences.setUserUsername((String) userProfile.get("username"));
                        userPreferences.setUserToken((String) userProfile.get("token_id"));
                        startActivity(new Intent(context, SplashActivity.class));
                        getActivity().finish();
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
