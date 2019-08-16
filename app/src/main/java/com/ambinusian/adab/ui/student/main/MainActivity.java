package com.ambinusian.adab.ui.student.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.student.allclasses.AllClassesFragment;
import com.ambinusian.adab.ui.student.calendar.CalendarFragment;
import com.ambinusian.adab.ui.student.forum.ForumFragment;
import com.ambinusian.adab.ui.student.help.HelpFragment;
import com.ambinusian.adab.R;
import com.ambinusian.adab.ui.login.LoginActivity;
import com.ambinusian.adab.ui.student.settings.SettingFragment;
import com.ambinusian.adab.ui.student.topics.TopicsFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listSemester;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    Spinner SpinnerListSemester;
    NavigationView mNavigationView;
    TextView textUserName;
    TextView textUserNIM;
    TextView textUserDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        mDrawerLayout = findViewById(R.id.mDrawerLayout);
        mNavigationView = findViewById(R.id.nv_adab);

        textUserName = mNavigationView.getHeaderView(0).findViewById(R.id.text_user_name);
        textUserNIM = mNavigationView.getHeaderView(0).findViewById(R.id.text_user_nim);
        textUserDepartment = mNavigationView.getHeaderView(0).findViewById(R.id.text_user_department);

        UserPreferences userPreferences = new UserPreferences(this);
        textUserName.setText(userPreferences.getUserName());
        textUserNIM.setText(userPreferences.getUserUsername());
        textUserDepartment.setText(userPreferences.getUserDepartement());

        listSemester = new ArrayList<>();

        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //set up spinner
        SpinnerListSemester = mNavigationView.getHeaderView(0).findViewById(R.id.spinner_list_semesters);
        listSemester.add("2018 Semester 1");
        listSemester.add("2018 Semester 2");
        SpinnerListSemester.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listSemester));

        //icon menu clicked
        toolbar.setNavigationOnClickListener(view -> mDrawerLayout.openDrawer(GravityCompat.START));

        //set first fragment launched
        getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new AllClassesFragment()).commit();
        mNavigationView.setCheckedItem(R.id.menu_allClasses);

        //navigation item clicked
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if(id == R.id.menu_allClasses){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new AllClassesFragment()).commit();
            }
            else if(id == R.id.menu_topics){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new TopicsFragment()).commit();
            }
            else if(id == R.id.menu_calendar){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new CalendarFragment()).commit();
            }
            else if(id == R.id.menu_forum){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new ForumFragment()).commit();
            }
            else if(id == R.id.menu_help){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new HelpFragment()).commit();
            }
            else if(id == R.id.menu_setting){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new SettingFragment()).commit();
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_guide:
                return true;
            case R.id.menu_about:
                return true;
            case R.id.menu_help:
                return true;
            case R.id.menu_logout:
                UserPreferences userPreferences = new UserPreferences(this);
                userPreferences.clearLoggedInUser();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

