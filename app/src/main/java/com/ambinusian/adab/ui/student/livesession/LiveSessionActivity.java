package com.ambinusian.adab.ui.student.livesession;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import org.w3c.dom.Text;

import java.util.Map;

public class LiveSessionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView courseTitle;
    private TextView className;
    private TextView classSession;
    private TextView textContent;
    private TextView textLiveNow;

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
        courseTitle = findViewById(R.id.tv_course_title);
        className = findViewById(R.id.tv_class_name);
        classSession = findViewById(R.id.tv_class_session);
        textContent = findViewById(R.id.tv_content);
        textLiveNow = findViewById(R.id.tv_live_now);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        APIManager apiManager = new APIManager(this);
        UserPreferences userPreferences = new UserPreferences(this);
        apiManager.getClassDetails(userPreferences.getUserToken(), classId, new NetworkHelper.getClassDetails() {
            @Override
            public void onResponse(Boolean success, Map<String, Object> classDetails) {
                if (success) {
                    String courseTitleText = (String) classDetails.get("topic");
                    String classNameText = (String) classDetails.get("course_name");
                    String sessionText = getString(R.string.class_session) + classDetails.get("session");

                    courseTitle.setText(courseTitleText);
                    className.setText(classNameText);
                    classSession.setText(sessionText);

                    if ((int) classDetails.get("is_live") == 1) {
                        textLiveNow.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
