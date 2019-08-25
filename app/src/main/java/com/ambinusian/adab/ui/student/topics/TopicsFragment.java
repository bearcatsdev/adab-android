package com.ambinusian.adab.ui.student.topics;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.recyclerview.classlistrecyclerview.ClassListAdapter;
import com.ambinusian.adab.recyclerview.classlistrecyclerview.ClassListModel;

import java.util.ArrayList;

public class TopicsFragment extends Fragment {

    RecyclerView classListRecyclerView;
    ArrayList<ClassListModel> classList;
    TextView course, courseCode, classRoom, nextClassTitle, nextClassSession, nextClassTime;
    ImageView courseIcon, nextClassIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classListRecyclerView = view.findViewById(R.id.rv_classList);
        courseIcon = view.findViewById(R.id.courseIcon);
        course = view.findViewById(R.id.tv_topic_course);
        courseCode= view.findViewById(R.id.tv_topic_CourseCode);
        classRoom = view.findViewById(R.id.tv_topic_ClassRoom);
        nextClassIcon = view.findViewById(R.id.topic_nextClassIcon);
        nextClassTitle = view.findViewById(R.id.tv_topic_nextClassTitle);
        nextClassSession = view.findViewById(R.id.tv_topic_nextClassSession);
        nextClassTime = view.findViewById(R.id.tv_topic_nextClassTime);

        classList = new ArrayList<>();

        //set information about the course
        courseIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
        course.setText(getArguments().getString("topic_title"));
        courseCode.setText("MOBI0062");
        classRoom.setText("507 - LA03");

        //set information about the next class
        nextClassIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
        nextClassTitle.setText("Storage");
        nextClassSession.setText("Session 11");
        nextClassTime.setText("Sunday, 25 August 2019 11:00");


        //show the sorted course
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));


        //set layout manager for recycler view
        classListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //set racycler view adapter
        ClassListAdapter adapter = new ClassListAdapter(getContext(),classList);
        classListRecyclerView.setAdapter(adapter);


    }
}
