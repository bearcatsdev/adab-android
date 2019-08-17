package com.ambinusian.adab.ui.student.topics;

import android.os.Bundle;
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
import com.ambinusian.adab.ui.student.main.courses.recyclerview.CourseAdapter;
import com.ambinusian.adab.ui.student.main.courses.recyclerview.CourseModel;

import java.util.ArrayList;

public class TopicsFragment extends Fragment {

    RecyclerView classTopic;
    ArrayList<CourseModel> classList;
    TextView course;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classTopic = view.findViewById(R.id.rv_classTopic);
        course = view.findViewById(R.id.tv_topicTitle);
        classList = new ArrayList<>();

        course.setText(getArguments().getString("topic_title"));

        //dummy data
        //show the sorted course
        classList.add(new CourseModel(1,1,"Friday, 16 August 2019 11:20","Introduction to Java","Mobile Object Oriented Programming","Session 1", "MOBI006", "LA03","LEC"));
        classList.add(new CourseModel(1,2,"Friday, 16 August 2019 13:20","Introduction to Java 2","Mobile Object Oriented Programming","Session 2", "MOBI006", "LA03","LEC"));
        classList.add(new CourseModel(1,3,"Friday, 16 August 2019 15:20","Introduction to Java 3","Mobile Object Oriented Programming","Session 3", "MOBI006", "LA03","LEC"));
        classList.add(new CourseModel(1,4,"Friday, 16 August 2019 17:20","Introduction to Java 4","Mobile Object Oriented Programming","Session 4", "MOBI006", "LA03","LEC"));
        classList.add(new CourseModel(1,5,"Friday, 16 August 2019 19:20","Introduction to Java 5","Mobile Object Oriented Programming","Session 5", "MOBI006", "LA03","LEC"));


        //set layout manager for recycler view
        classTopic.setLayoutManager(new LinearLayoutManager(getContext()));

        //set racycler view adapter
        CourseAdapter adapter = new CourseAdapter(getContext(),classList);
        classTopic.setAdapter(adapter);


    }
}
