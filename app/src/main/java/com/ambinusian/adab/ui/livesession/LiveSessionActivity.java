package com.ambinusian.adab.ui.livesession;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ambinusian.adab.R;

public class LiveSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_session);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, String.valueOf(bundle.getInt("class_id")), Toast.LENGTH_SHORT).show();
    }
}
