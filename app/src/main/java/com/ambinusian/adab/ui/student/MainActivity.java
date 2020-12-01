package com.ambinusian.adab.ui.student;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.airbnb.lottie.LottieAnimationView;
import com.ambinusian.adab.all.ErrorFragment;
import com.ambinusian.adab.expandablenavigationdrawer.ExpandableListAdapter;
import com.ambinusian.adab.expandablenavigationdrawer.MenuModel;
import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;
import com.ambinusian.adab.ui.bottomSheet.LowVisionBottomSheetDialog;
import com.ambinusian.adab.ui.bottomSheet.SaveButtonListener;
import com.ambinusian.adab.ui.userprofile.UserProfileDialogFragment;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listSemester;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    Spinner SpinnerListSemester;
    NavigationView mNavigationView;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    CircleImageView ivLowVision;
    TextView binusAdab,version;
    List<MenuModel> groupList;
    List<MenuModel> courseSubject;
    HashMap<MenuModel,List<MenuModel>> childList;
    View headerView;
    LottieAnimationView profilePicture;
    ClassDatabase db;
    List<ClassEntity> classList;
    UserPreferences userPreferences;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

//        UserPreferences userPreferences = new UserPreferences(this);
//        AppCompatDelegate.setDefaultNightMode(userPreferences.getPrefNight());

        toolbar = findViewById(R.id.tool_bar);
        mDrawerLayout = findViewById(R.id.mDrawerLayout);
        mNavigationView = findViewById(R.id.nv_adab);
        expandableListView = findViewById(R.id.expandableListView);
        profilePicture = findViewById(R.id.circle_image_profile_picture);
        ivLowVision = findViewById(R.id.iv_low_vision);
        groupList = new ArrayList<>();
        childList = new HashMap<>();
        courseSubject = new ArrayList<>();
        listSemester = new ArrayList<>();
        headerView = getLayoutInflater().inflate(R.layout.adab_nav_header_layout,null);
        SpinnerListSemester = headerView.findViewById(R.id.spinner_list_semesters);
        binusAdab = headerView.findViewById(R.id.tv_binus_adab);
        version = headerView.findViewById(R.id.tv_version);
        userPreferences = new UserPreferences(this);
        db = ClassDatabase.getDatabase(getApplicationContext());


        // set toolbar
        setSupportActionBar(toolbar);

        // remove title
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // profile picture onclick
        profilePicture.setOnClickListener(v -> {
            showUserProfileDialog();
        });

        // low vision icon onclick
        ivLowVision.setOnClickListener(view -> {
            SaveButtonListener saveButtonListener = new SaveButtonListener() {
                @Override
                public void onClick() {
                    //reload activity
                    finish();
                    startActivity(getIntent());
                }
            };
            LowVisionBottomSheetDialog bottomSheetDialog = new LowVisionBottomSheetDialog(saveButtonListener);
            bottomSheetDialog.show(getSupportFragmentManager(),"LowVisionBottomSheet");
        });

        //add headerView to expandableListView
        expandableListView.addHeaderView(headerView);

        //set up spinner
        listSemester.add("2019 Semester 1");
        SpinnerListSemester.setAdapter(new SpinnerAdapter(this, android.R.layout.simple_spinner_item, listSemester));

        //icon menu clicked
        toolbar.setNavigationOnClickListener(view -> mDrawerLayout.openDrawer(GravityCompat.START));

        //set first fragment launched
        getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new FragmentHome()).commit();

        //set expandable navigation drawer
        prepareMenuData();

        //set text size
        setTextSize();
    }

    private void showUserProfileDialog() {
        FragmentManager fm = getSupportFragmentManager();
        UserProfileDialogFragment userProfileDialogFragment = new UserProfileDialogFragment();
        userProfileDialogFragment.show(fm, "fragment_user_profile_dialog");
    }

    public void prepareMenuData(){
        //set course Subject
        db.classDAO().getAllClass().observe(MainActivity.this, new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                classList = classEntities;

                for(int i=0;i<classList.size();i++){
                    int count = 0;
                    for(int j=0;j<courseSubject.size();j++)
                        if(classList.get(i).getCourseName().equals(courseSubject.get(j).menuName) && i!= j){
                            count++;
                    }
                    if(count == 0){
                        courseSubject.add(new MenuModel(6,classList.get(i).getCourseName(),false,false));
                    }
                }

                //set group menu list
                groupList.add(new MenuModel(0,"Home",false,false));
                groupList.add(new MenuModel(1,"Topics",true,true));
                groupList.add(new MenuModel(2,"Calendar",false,false));
                groupList.add(new MenuModel(3,"Discussion",false,false));
                groupList.add(new MenuModel(4,"Help",false,false));
                groupList.add(new MenuModel(5,"Setting",false,false));

                //set child menu list at topics menu, index == 1
                childList.put(groupList.get(1),courseSubject);

                populateExpandableList();
            }
        });


    }

    public void populateExpandableList(){

        expandableListAdapter = new ExpandableListAdapter(this, groupList,childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setItemChecked(1,true);

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            if(groupPosition == 0){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new FragmentHome()).commit();
            }
            else if(groupPosition == 2){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new FragmentCalendar()).commit();
            }
            else if(groupPosition == 3){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new ErrorFragment()).commit();
            }
            else if(groupPosition == 4){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new FragmentHelp()).commit();
            }
            else if(groupPosition == 5){
                getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment,new FragmentSetting()).commit();
            }
            else if(groupPosition == 1){
                if(!expandableListView.isGroupExpanded(1) && expandableListView.getCheckedItemPosition() > 2){
                    parent.setItemChecked(expandableListView.getCheckedItemPosition()+courseSubject.size(),true);
                } else if(!expandableListView.isGroupExpanded(1) && expandableListView.getCheckedItemPosition() < 0){
                    parent.setItemChecked(expandableListView.getCheckedItemPosition()+100,true);
                } else if(expandableListView.isGroupExpanded(1) && expandableListView.getCheckedItemPosition() > courseSubject.size()+2 ){
                    parent.setItemChecked(expandableListView.getCheckedItemPosition()-courseSubject.size(),true);
                }  else if(expandableListView.isGroupExpanded(1) && (expandableListView.getCheckedItemPosition() >= 3 && expandableListView.getCheckedItemPosition() <= courseSubject.size()+2)) {
                    parent.setItemChecked(expandableListView.getCheckedItemPosition()- 100, true);
                }
            }

            //don't close the drawer if selected Topics menu. Otherwise, just close it
            if(groupPosition != 1){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }

            //for highlight menu background
            int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
            if(!(groupList.get(groupPosition).hasChildren)){
                parent.setItemChecked(index-2,true);
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
            bundle.putString("topic_name",courseSubject.get(childPosition).menuName);
            FragmentTopics fragmentTopics = new FragmentTopics();
            fragmentTopics.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.adab_fragment, fragmentTopics).commit();

            return false;
        });
    }

    private void setTextSize(){
        //multiple of text size
        float textSize = userPreferences.getTextSize();
        //set text size for each text view
        binusAdab.setTextSize(TypedValue.COMPLEX_UNIT_PX,binusAdab.getTextSize()*textSize);
        version.setTextSize(TypedValue.COMPLEX_UNIT_PX,version.getTextSize()*textSize);
    }

    private class SpinnerAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> items;
        float defaultTextSize = -1;

        public SpinnerAdapter(final Context context,
                              final int textViewResourceId, final ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
            this.context = context;

        }

        @Override
        public View getView(int position, View convertView,
                                    ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(
                        android.R.layout.simple_spinner_item, parent, false);
            }

            TextView tv = (TextView) convertView
                    .findViewById(android.R.id.text1);
            tv.setText(items.get(position));

            if (defaultTextSize == -1) {
                defaultTextSize = tv.getTextSize();
            }

            //set text for spinner
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize*userPreferences.getTextSize());
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                            ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(
                        android.R.layout.simple_spinner_item, parent, false);
            }

            TextView tv = (TextView) convertView
                    .findViewById(android.R.id.text1);
            tv.setText(items.get(position));

            if (defaultTextSize == -1) {
                defaultTextSize = tv.getTextSize();
            }

            //set text for spinner
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize*userPreferences.getTextSize());
            return convertView;
        }
    }
}

