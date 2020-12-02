package com.ambinusian.adab.ui.student;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.customcomponent.SectionView;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.classlist.ClassListAdapter;
import com.ambinusian.adab.recyclerview.classlist.ClassListModel;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;
import com.ambinusian.adab.utility.DateUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentTopics extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    String course_name="", course_code="", class_room="";
    RecyclerView classListRecyclerView;
    TextView course;
    TextView courseCode;
    TextView classRoom;
    TextView nextClassTitle;
    TextView nextClassSession;
    TextView nextClassTime;
    ImageView courseIcon;
    ImageView nextClassIcon;
    SectionView svClassList;
    ClassDatabase db;
    ArrayList<ClassListModel> classList;
    LinearLayout nextClassLayout;
    UserPreferences userPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classListRecyclerView = view.findViewById(R.id.rv_classList);
        course = view.findViewById(R.id.tv_topic_course);
        courseCode= view.findViewById(R.id.tv_topic_CourseCode);
        classRoom = view.findViewById(R.id.tv_topic_ClassRoom);
        nextClassTitle = view.findViewById(R.id.tv_topic_nextClassTitle);
        nextClassSession = view.findViewById(R.id.tv_topic_nextClassSession);
        nextClassTime = view.findViewById(R.id.tv_topic_nextClassTime);
        courseIcon = view.findViewById(R.id.courseIcon);
        nextClassIcon = view.findViewById(R.id.topic_nextClassIcon);
        svClassList = view.findViewById(R.id.sv_class_list);
        db = ClassDatabase.getDatabase(getContext());
        classList = new ArrayList<>();
        nextClassLayout = view.findViewById(R.id.next_class_layout);
        userPreferences = new UserPreferences(getContext());

        if(getArguments() != null) {
            course_name = getArguments().getString("topic_name");
        }

        db.classDAO().getAllClass().observe(getActivity(), new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                for(int i=0;i<classEntities.size();i++){
                    if(classEntities.get(i).getCourseName().equals(course_name)){
                        ClassEntity classEntity = classEntities.get(i);
                        course_code = classEntity.getCourseId();
                        classList.add(new ClassListModel(1,
                                classEntity.getSessionId(),
                                classEntity.getTopicTitle(),
                                "Session "+classEntity.getSessionTh(),
                                DateUtility.convertToDateAndTimeFormat(classEntity.getSessionStartDate())));
                    }
                }
                course.setText(course_name);
                courseCode.setText(course_code);
                classRoom.setText("507 - LA03");

                //search for next class
                int nextClass = -1;
                Date currentDate = Calendar.getInstance().getTime();
                Date date = null;
                for(int i=0;i<classList.size();i++){
                    try {
                        date = DateUtility.convertStringToDate(classList.get(i).getTime());
                        if (date != null && date.after(currentDate)) {
                            nextClass = i;
                            break;
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }

                if(nextClass > -1 ){
                    ClassListModel next_class_info = classList.get(nextClass);
                    nextClassLayout.setVisibility(View.VISIBLE);
                    nextClassIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
                    nextClassTitle.setText(next_class_info.getClassTopic());
                    nextClassSession.setText("Session "+next_class_info.getMeeting());
                    nextClassTime.setText(next_class_info.getTime());
                }

                //set text attributes
                setTextSize();
                setTextTypeface();

                //Set Layout Manager For Recycler View
                classListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                //Set Recycler View Adapter
                ClassListAdapter adapter = new ClassListAdapter(getContext(),classList);
                classListRecyclerView.setAdapter(adapter);

                courseIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
            }
        });
    }

    private void setTextSize(){
        //multiple of text size
        float textSize = userPreferences.getTextSize();
        //set text size for each text view
        course.setTextSize(TypedValue.COMPLEX_UNIT_PX,course.getTextSize()*textSize);
        courseCode.setTextSize(TypedValue.COMPLEX_UNIT_PX,courseCode.getTextSize()*textSize);
        classRoom.setTextSize(TypedValue.COMPLEX_UNIT_PX, classRoom.getTextSize()*textSize);
        nextClassTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, nextClassTitle.getTextSize()*textSize);
        nextClassSession.setTextSize(TypedValue.COMPLEX_UNIT_PX, nextClassSession.getTextSize()*textSize);
        nextClassTime.setTextSize(TypedValue.COMPLEX_UNIT_PX, nextClassTime.getTextSize()*textSize);
        svClassList.sectionTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, svClassList.sectionTitle.getTextSize()*textSize);
        svClassList.sectionSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, svClassList.sectionSubtitle.getTextSize()*textSize);
    }

    private void setTextTypeface(){
        //get font type
        Typeface textTypeface = userPreferences.getTextTypeface();
        //set font type for each text view
        course.setTypeface(textTypeface);
        courseCode.setTypeface(textTypeface);
        classRoom.setTypeface(textTypeface);
        nextClassTitle.setTypeface(textTypeface);
        nextClassSession.setTypeface(textTypeface);
        nextClassTime.setTypeface(textTypeface);
        svClassList.sectionTitle.setTypeface(textTypeface);
        svClassList.sectionSubtitle.setTypeface(textTypeface);
    }
}
