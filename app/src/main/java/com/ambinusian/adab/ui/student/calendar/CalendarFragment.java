package com.ambinusian.adab.ui.student.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.schedulereyclerview.ScheduleAdapter;
import com.ambinusian.adab.recyclerview.schedulereyclerview.ScheduleModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class CalendarFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    TextView selectedDate;
    ArrayList<ScheduleModel> allClassSchedule;
    SimpleDateFormat dateFormat;
    RecyclerView scheduleList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        calendarView = view.findViewById(R.id.calendarView);
        selectedDate = view.findViewById(R.id.tv_selectedDate);
        scheduleList = view.findViewById(R.id.rv_schedule);
        allClassSchedule = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -10);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 10);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                int currentDay = date.getTime().getDay();
                int currentDate = date.getTime().getDate();
                int currentMonth = date.getTime().getMonth() + 1;
                int currentYear = date.getTime().getYear() + 1900;

                Date date2 = null;
                try {
                    date2 = new SimpleDateFormat("yyyy-MM-dd").parse(currentYear + "-" + currentMonth + "-" + currentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                selectedDate.setText(new SimpleDateFormat("EEEE, d MMMM yyyy").format(date2));

                //Searching the classes at selected date
                ArrayList<ScheduleModel> selectedDateClasses = new ArrayList<>();
                for (int i = 0; i < allClassSchedule.size(); i++) {
                    Calendar selectedDateCalendar = Calendar.getInstance();
                    try {
                        selectedDateCalendar.setTime(dateFormat.parse(allClassSchedule.get(i).getClassDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (selectedDateCalendar.equals(date)) {
                        ScheduleModel item = allClassSchedule.get(i);
                        selectedDateClasses.add(item);
                    }
                }

                //set recycler view adapter
                ScheduleAdapter adapter = new ScheduleAdapter(getContext(), selectedDateClasses);
                scheduleList.setAdapter(adapter);

            }
        });

        //set recycler view layout manager
        scheduleList.setLayoutManager(new LinearLayoutManager(getContext()));

        APIManager apiManager = new APIManager(getContext());
        UserPreferences userPreferences = new UserPreferences(getContext());
        apiManager.getUserClasses(userPreferences.getUserToken(), new NetworkHelper.getUserClasses() {
            @Override
            public void onResponse(Boolean success, Map<String, Object>[] userClasses) {
                //information needed = classDate, classType, classTitle, course, courseCode, classRoom, classTime
                //add all class schedule dummy data
                if (success) {
                    for (Map<String, Object> userClass : userClasses) {
                        allClassSchedule.add(new ScheduleModel(
                                (String) userClass.get("transaction_date"),
                                (String) userClass.get("class_type"),
                                (String) userClass.get("topic"),
                                (String) userClass.get("course_name"),
                                (String) userClass.get("course_code"),
                                (String) userClass.get("class_code"),
                                (String) userClass.get("transaction_time")));
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });

        //add all class Schedule to calendar
        List<Calendar> classSchedule = new ArrayList<>();
        for (int i = 0; i < allClassSchedule.size(); i++) {
            //add date to calendae object
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(dateFormat.parse(allClassSchedule.get(i).getClassDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //add calendar object to classSchedule list
            classSchedule.add(calendar);
        }

    }
}
