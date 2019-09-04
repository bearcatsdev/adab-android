package com.ambinusian.adab.ui.student;

import android.annotation.SuppressLint;
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

public class FragmentTopics extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

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

        ArrayList<ClassListModel> classList = new ArrayList<>();

        //Set Information About the Course
        courseIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
        if(getArguments() != null) {
            course.setText(getArguments().getString("topic_title"));
        }
        courseCode.setText("MOBI0062");
        classRoom.setText("507 - LA03");

        //Set Information About the Next Class
        nextClassIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_class_56_pencilnote));
        nextClassTitle.setText("Storage");
        nextClassSession.setText("Session 11");
        nextClassTime.setText("Sunday, 25 August 2019 11:00");

        //Show The Sorted Course
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));
        classList.add(new ClassListModel(1,1,"Introduction to Java","Session 11","Sunday, 25 August 2019 11:00"));

        //Set Layout Manager For Recycler View
        classListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Set Recycler View Adapter
        ClassListAdapter adapter = new ClassListAdapter(getContext(),classList);
        classListRecyclerView.setAdapter(adapter);
    }
}
