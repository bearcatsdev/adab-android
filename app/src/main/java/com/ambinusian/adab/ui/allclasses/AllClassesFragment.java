package com.ambinusian.adab.ui.allclasses;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.ui.mainactivity.courses.recyclerview.CourseAdapter;
import com.ambinusian.adab.ui.mainactivity.courses.recyclerview.CourseModel;
import com.ambinusian.adab.R;

import java.util.ArrayList;

public class AllClassesFragment extends Fragment {

    RecyclerView coursesRecyclerView;
    ArrayList<CourseModel> coursesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_classes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coursesRecyclerView = view.findViewById(R.id.rv_courses);
        coursesList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

//        divider (not used)
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(coursesRecyclerView.getContext(),
                    linearLayoutManager.getOrientation());
            coursesRecyclerView.addItemDecoration(dividerItemDecoration);

        //set layout manager for recycler view
        coursesRecyclerView.setLayoutManager(linearLayoutManager);

        //set list data for recycler view
        coursesList.add(new CourseModel(0,"Yesterday", "Storage", "MOOP","Meeting 11","MOBI009","LA03","LEC"));
        coursesList.add(new CourseModel(0,"Yesterday", "Storage", "MOOP","Meeting 11","MOBI009","LA03","LEC"));
        coursesList.add(new CourseModel(0,"Yesterday", "Storage", "MOOP","Meeting 11","MOBI009","LA03","LEC"));

        //set adapter for recycler view
        coursesRecyclerView.setAdapter(new CourseAdapter(getContext(),coursesList));
    }
}
