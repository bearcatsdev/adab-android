package com.ambinusian.adab.ui.student;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ActivityLive extends AppCompatActivity{

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
    private UserPreferences userPreferences;
    private Integer sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_session);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        sessionId = bundle.getInt("session_id");

        if (sessionId == null) finish();

        Log.d("ClassId", sessionId.toString());

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

        //scroll always to bottom
        textContent.setMovementMethod(new ScrollingMovementMethod());

        APIManager apiManager = new APIManager(this);
        userPreferences = new UserPreferences(this);
        apiManager.getClassDetails(userPreferences.getUserToken(), sessionId, new NetworkHelper.getClassDetails() {
            @Override
            public void onResponse(Boolean success, Map<String, Object> classDetails) {
                if (success) {
                    String courseTitleText = (String) classDetails.get("topic_title");
                    String classNameText = (String) classDetails.get("course_name");
                    String sessionText = getString(R.string.class_session) + " " + classDetails.get("session_th");
                    String content = (String) classDetails.get("content");
                    int canTalk = (int) classDetails.get("can_talk");

                    if (canTalk == 1) {
                        Intent intent = new Intent(ActivityLive.this, com.ambinusian.adab.ui.lecturer.ActivityLive.class);

                        //set all data to bundle
                        Bundle bundle = new Bundle();
                        bundle.putInt("session_id", sessionId);

                        //set bundle to the intent
                        intent.putExtras(bundle);

                        //go to ActivityLive
                        startActivity(intent);

                        finish();
                    }

                    courseTitle.setText(courseTitleText);
                    className.setText(classNameText);
                    classSession.setText(sessionText);
                    textContent.setText(content);

                    Date endDate = null;
                    try {
                        endDate = new SimpleDateFormat("yy-MM-dd HH:mm").parse((String) classDetails.get("session_enddate"));
                    } catch (Exception e) { }

                    if (Calendar.getInstance().getTime().before(endDate)) {
                        toolbarTitle.setText(R.string.live_class_transcribe);
                    } else {
                        toolbarTitle.setText(R.string.class_transcribe_history);
                    }

                    connectSocket();
                    loadingLayout.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    scrollViewMain.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {
                Log.e("Error", errorReason);
            }
        });
    }

    private void connectSocket() {
        try {
            socket = IO.socket("https://adab2.bearcats.dev");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.connect();

        while (!socket.connected()) {
            Log.d("Socket.io", "connecting...");
        }

        // ganti "1" jadi transactionId tapi convert ke string pake String.valueOf(transactionId)
        socket.emit("join_room", String.valueOf(sessionId));

        if (socket.connected()) {
            Log.d("Socket.io", "oke bang sudah konek from students");
            contentLoadingLayout.setVisibility(View.GONE);
        } else {
            Log.d("Socket.io", "error");
        }

        socket.on("message", args -> {
            runOnUiThread(() -> {
                textContent.append(args[0].toString() + " ");
                scrollViewMain.fullScroll(View.FOCUS_DOWN);
                Log.d("message",args[0].toString());
            });
        });

        socket.on("start_talking", args -> {
            runOnUiThread(() -> textLiveNow.setVisibility(View.VISIBLE));
        });

        socket.on("stop_talking", args -> {
            runOnUiThread(() -> textLiveNow.setVisibility(View.INVISIBLE));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
