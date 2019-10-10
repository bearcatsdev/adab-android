package com.ambinusian.adab.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;
import com.ambinusian.adab.ui.student.MainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ambinusian.adab.ui.splash.SplashActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FragmentLogin extends Fragment {

    private TextInputEditText inputNim;
    private TextInputEditText inputPassword;
    private APIManager apiManager;
    private UserPreferences userPreferences;
    private ClassDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnLogin = view.findViewById(R.id.btn_login);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        inputNim = view.findViewById(R.id.input_nim);
        inputPassword = view.findViewById(R.id.input_password);
        userPreferences = new UserPreferences(getContext());
        apiManager = new APIManager(getContext());
        db = ClassDatabase.getDatabase(getContext());


        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(view1 -> Objects.requireNonNull(getActivity()).onBackPressed());

        btnLogin.setOnClickListener(view12 -> doLogin());
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
            apiManager.authenticateUser(Objects.requireNonNull(inputNim.getText()).toString(), Objects.requireNonNull(inputPassword.getText()).toString(), new NetworkHelper.authenticateUser() {
                @Override
                public void onResponse(Boolean success, Map<String, Object> userProfile) {
                    if (success) {
                        userPreferences.setUserLoggedIn(true);
                        userPreferences.setUserUsername((String) userProfile.get("username"));
                        userPreferences.setUserToken((String) userProfile.get("token_id"));

                        getUserProfile();
//                        startActivity(new Intent(context, SplashActivity.class));

                    }
                }

                @Override
                public void onError(int errorCode, String errorReason) {
                    if (errorCode == 403) {
                        inputNim.setError(getString(R.string.username_or_password_invalid));
                        inputPassword.setError(getString(R.string.username_or_password_invalid));
                        inputNim.requestFocus();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void getUserProfile(){
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

                    getUserClasses();

                    startActivity(new Intent(getContext(), SplashActivity.class));
                    Objects.requireNonNull(getActivity()).finish();
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });
    }

    public void getUserClasses(){
        apiManager.getUserClasses(userPreferences.getUserToken(), new NetworkHelper.getUserClasses() {
                @Override
            public void onResponse(Boolean success, Map<String, Object>[] userClasses) {
                if(success){
                    int len = userClasses.length;
                    for(int i=0;i<len;i++){
                        int transaction_id = Integer.valueOf(userClasses[i].get("transaction_Id").toString());
                        String course_code = userClasses[i].get("course_code").toString();
                        String course_name = userClasses[i].get("course_name").toString();
                        String language = userClasses[i].get("language").toString();
                        String class_code = userClasses[i].get("class_code").toString();
                        String class_type = userClasses[i].get("class_type").toString();
                        int class_icon = (int) userClasses[i].get("class_icon");
                        String session = userClasses[i].get("session").toString();
                        String topic = userClasses[i].get("topic").toString();
                        String transaction_date = userClasses[i].get("transaction_date").toString();
                        String transaction_time = userClasses[i].get("transaction_time").toString();
                        int is_live = (int) userClasses[i].get("is_live");
                        int is_done = (int) userClasses[i].get("is_done");

                        insertData(new ClassEntity(transaction_id,course_code,course_name,language,class_code,class_type,class_icon,session, topic, transaction_date,transaction_time,is_live, is_done));
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });
    }

    private void insertData(final ClassEntity classList){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                // melakukan proses insert data
                db.classDAO().insertClass(classList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }
}
