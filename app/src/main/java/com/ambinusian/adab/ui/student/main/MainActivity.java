package com.ambinusian.adab.ui.student.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.ambinusian.adab.expandablenavigationdrawer.ExpandableListAdapter;
import com.ambinusian.adab.expandablenavigationdrawer.MenuModel;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.R;
import com.ambinusian.adab.ui.login.LoginActivity;
import com.ambinusian.adab.ui.student.allclasses.AllClassesFragment;
import com.ambinusian.adab.ui.student.calendar.CalendarFragment;
import com.ambinusian.adab.ui.student.forum.ForumFragment;
import com.ambinusian.adab.ui.student.help.HelpFragment;
import com.ambinusian.adab.ui.student.settings.SettingFragment;
import com.ambinusian.adab.ui.student.topics.TopicsFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listSemester;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    Spinner SpinnerListSemester;
    NavigationView mNavigationView;
    TextView textUserName;
    TextView textUserNIM;
    TextView textUserDepartment;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<MenuModel> groupList;
    List<MenuModel> courseSubject;
    HashMap<MenuModel,List<MenuModel>> childList;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        mDrawerLayout = findViewById(R.id.mDrawerLayout);
        mNavigationView = findViewById(R.id.nv_adab);
        expandableListView = findViewById(R.id.expandableListView);
        groupList = new ArrayList<>();
        childList = new HashMap<>();
        courseSubject = new ArrayList<>();
        listSemester = new ArrayList<>();
        headerView = getLayoutInflater().inflate(R.layout.adab_nav_header_layout,null);

        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        textUserName = headerView.findViewById(R.id.text_user_name);
        textUserNIM = headerView.findViewById(R.id.text_user_nim);
        textUserDepartment = headerView.findViewById(R.id.text_user_department);

        UserPreferences userPreferences = new UserPreferences(this);
        textUserName.setText(userPreferences.getUserName());
        textUserNIM.setText(userPreferences.getUserUsername());
        textUserDepartment.setText(userPreferences.getUserDepartement());

        //set expand indicator size
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));

        //add headerView to expandableListView
        expandableListView.addHeaderView(headerView);

        //set course Subject
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"MOOP",false,false));
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"Bahasa Indonesia",false,false));
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"English Savvy",false,false));
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"Calculus",false,false));
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"Data Structure",false,false));
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"Mobile Creative Design",false,false));
        courseSubject.add(new MenuModel(getResources().getDrawable(R.drawable.ic_outline_book_24px),"CB - Kewanegaraan",false,false));

        //set up spinner
        SpinnerListSemester = headerView.findViewById(R.id.spinner_list_semesters);
        listSemester.add("2018 Semester 1");
        listSemester.add("2018 Semester 2");
        SpinnerListSemester.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listSemester));

        //icon menu clicked
        toolbar.setNavigationOnClickListener(view -> mDrawerLayout.openDrawer(GravityCompat.START));

        //set first fragment launched
        getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new AllClassesFragment()).commit();

        //set expandable navigation drawer
        prepareMenuData();
        populateExpandableList();
    }

    public void prepareMenuData(){
        //set group menu list
        groupList.add(new MenuModel(getResources().getDrawable(R.drawable.round_view_stream_24px),"Home",false,false));
        groupList.add(new MenuModel(getResources().getDrawable(R.drawable.round_book_24px),"Topics",true,true));
        groupList.add(new MenuModel(getResources().getDrawable(R.drawable.round_today_24px),"Calendar",false,false));
        groupList.add(new MenuModel(getResources().getDrawable(R.drawable.round_forum_24px),"Discussion",false,false));
        groupList.add(new MenuModel(getResources().getDrawable(R.drawable.round_help_24px),"Help",false,false));
        groupList.add(new MenuModel(getResources().getDrawable(R.drawable.round_settings_24px),"Setting",false,false));

        //set child menu list at topics menu, index == 1
        childList.put(groupList.get(1),courseSubject);
    }

    public void populateExpandableList(){

        expandableListAdapter = new ExpandableListAdapter(this, groupList,childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setFocusable(true);
        expandableListView.setFocusableInTouchMode(true);
        expandableListView.setItemChecked(1,true);

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            if(groupPosition == 0){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new AllClassesFragment()).commit();
            }
            else if(groupPosition == 2){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new CalendarFragment()).commit();
            }
            else if(groupPosition == 3){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new ForumFragment()).commit();
            }
            else if(groupPosition == 4){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new HelpFragment()).commit();
            }
            else if(groupPosition == 5){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new SettingFragment()).commit();
            }
            else if(groupPosition == 1){
                if(!expandableListView.isGroupExpanded(1) && expandableListView.getCheckedItemPosition() > 2){
                    parent.setItemChecked(expandableListView.getCheckedItemPosition()+7,true);
                }
                else
                if(expandableListView.isGroupExpanded(1) && expandableListView.getCheckedItemPosition() > 2){
                    parent.setItemChecked(expandableListView.getCheckedItemPosition()-7,true);
                }
            }

            //don't close the drawer if selected Topics menu. Otherwise, just close it
            if(groupPosition != 1){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }

            //for highlight menu background
            int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
            if(!(groupList.get(groupPosition).hasChildren) && parent.getCheckedItemPosition() != index){
                parent.setItemChecked(index,true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }

            expandableListAdapter.notifyDataSetChanged();
            return false;
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition,childPosition));

            parent.setItemChecked(index,true);
            mDrawerLayout.closeDrawer(GravityCompat.START);

            //send bundle to Topics Fragment
            Bundle bundle = new Bundle();
            bundle.putString("class_id","12");
            bundle.putString("topic_title",childList.get(groupList.get(groupPosition)).get(childPosition).menuName);
            TopicsFragment topicsFragment = new TopicsFragment();
            topicsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,topicsFragment).commit();

            return false;
        });
    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
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

