package com.ambinusian.adab.ui.student.livesession;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.net.URISyntaxException;
import java.util.Map;

public class LiveSessionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView className;
    private TextView classSession;
    private TextView textContent;
    private TextView textLiveNow;
    private TextView courseTitle;
    private TextView toolbarTitle;
    private Socket socket;
    private RelativeLayout loadingLayout;
    private RelativeLayout contentLoadingLayout;
    private ScrollView scrollViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_session);

        Bundle bundle = getIntent().getExtras();
        Integer classId = bundle.getInt("class_id");

        if (classId == null) {
            finish();
        }

        toolbar = findViewById(R.id.toolbar);
        className = findViewById(R.id.tv_class_name);
        classSession = findViewById(R.id.tv_class_session);
        textContent = findViewById(R.id.tv_content);
        textLiveNow = findViewById(R.id.tv_live_now);
        courseTitle = findViewById(R.id.tv_course_title);
        loadingLayout = findViewById(R.id.layout_loading);
        toolbarTitle = findViewById(R.id.toolbar_title);
        contentLoadingLayout = findViewById(R.id.layout_loading_content);
        scrollViewMain = findViewById(R.id.scrollview_main);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setVisibility(View.GONE);
        scrollViewMain.setVisibility(View.GONE);

        APIManager apiManager = new APIManager(this);
        UserPreferences userPreferences = new UserPreferences(this);
        apiManager.getClassDetails(userPreferences.getUserToken(), classId, new NetworkHelper.getClassDetails() {
            @Override
            public void onResponse(Boolean success, Map<String, Object> classDetails) {
                if (success) {
                    String courseTitleText = (String) classDetails.get("topic");
                    String classNameText = (String) classDetails.get("course_name");
                    String sessionText = getString(R.string.class_session) + " " + classDetails.get("session");

                    courseTitle.setText(courseTitleText);
                    className.setText(classNameText);
                    classSession.setText(sessionText);

                    if ((int) classDetails.get("is_live") == 1) {
                        textLiveNow.setVisibility(View.VISIBLE);
                        connectSocket();
                        toolbarTitle.setText(R.string.live_class_transcribe);
                    } else {
                        toolbarTitle.setText(R.string.class_transcribe_history);
                    }

                    loadingLayout.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    scrollViewMain.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });
    }

    private void connectSocket() {
        try {
            socket = IO.socket("https://adabapi.bancet.cf");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on("kok lai liau", args -> {
           runOnUiThread(() -> {
               textContent.append(args[0].toString() + "\n");
           });
        });
        socket.connect();

        while(!socket.connected()) {
            Log.d("Socket.io", "connecting...");
        }

        if (socket.connected()) {
            Log.d("Socket.io", "oke bang sudah konek");
            contentLoadingLayout.setVisibility(View.GONE);
        } else {
            Log.d("Socket.io", "error");
        }



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
