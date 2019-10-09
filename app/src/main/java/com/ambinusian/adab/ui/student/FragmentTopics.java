package com.ambinusian.adab.ui.student;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.ambinusian.adab.recyclerview.classlist.ClassListAdapter;
import com.ambinusian.adab.recyclerview.classlist.ClassListModel;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;

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
    LinearLayout nextClassLayout;

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView classListRecyclerView = view.findViewById(R.id.rv_classList);
        TextView course = view.findViewById(R.id.tv_topic_course);
        TextView courseCode= view.findViewById(R.id.tv_topic_CourseCode);
        TextView classRoom = view.findViewById(R.id.tv_topic_ClassRoom);
        TextView nextClassTitle = view.findViewById(R.id.tv_topic_nextClassTitle);
        TextView nextClassSession = view.findViewById(R.id.tv_topic_nextClassSession);
        TextView nextClassTime = view.findViewById(R.id.tv_topic_nextClassTime);
        ImageView courseIcon = view.findViewById(R.id.courseIcon);
        ImageView nextClassIcon = view.findViewById(R.id.topic_nextClassIcon);
        ClassDatabase db = ClassDatabase.getDatabase(getContext());
        ArrayList<ClassListModel> classList = new ArrayList<>();
        nextClassLayout = view.findViewById(R.id.next_class_layout);



        if(getArguments() != null) {
            course_name = getArguments().getString("topic_name");
        }

        db.classDAO().getAllClass().observe(getActivity(), new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                for(int i=0;i<classEntities.size();i++){
                    if(classEntities.get(i).getCourse_name().equals(course_name)){
                        ClassEntity classEntity = classEntities.get(i);
                        course_code = classEntity.getCourse_code();
                        //format for date
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
                        Date date = null;
                        try {
                            date = format.parse(classEntity.getTransaction_date()+" "+classEntity.getTransaction_time());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        classList.add(new ClassListModel(classEntity.getClass_icon(),classEntity.getTransaction_id(), classEntity.getTopic(),"Session "+classEntity.getSession(),new SimpleDateFormat("EEEE, d MMMM YYYY H:mm").format(date)));
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
                        date = new SimpleDateFormat("EEEE,d MMMM YYYY H:mm").parse(classList.get(i).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(date.after(currentDate)){
                        nextClass = i;
                        break;
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

                //Set Layout Manager For Recycler View
                classListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                //Set Recycler View Adapter
                ClassListAdapter adapter = new ClassListAdapter(getContext(),classList);
                classListRecyclerView.setAdapter(adapter);

                courseIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
            }
        });

        //Set Information About the Next Class


        //Show The Sorted Course


    }
}
