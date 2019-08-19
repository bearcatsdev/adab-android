package com.ambinusian.adab.ui.student.livesession;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.ambinusian.adab.R;
import org.w3c.dom.Text;

public class LiveSessionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView courseTitle;
    private TextView className;
    private TextView classSession;
    private TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_session);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, String.valueOf(bundle.getInt("class_id")), Toast.LENGTH_SHORT).show();

        toolbar = findViewById(R.id.toolbar);
        courseTitle = findViewById(R.id.tv_course_title);
        className = findViewById(R.id.tv_class_name);
        classSession = findViewById(R.id.tv_class_session);
        textContent = findViewById(R.id.tv_content);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
