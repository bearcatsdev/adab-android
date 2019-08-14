package com.ambinusian.adab.ui.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambinusian.adab.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.*;

import java.util.Calendar;


public class CalendarFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    MaterialCalendarView calendarView;
    TextView selectedDate;
    String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String[] months = {"January","Februabry","March","April","May","June","July","August","September","October","November","December"};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.calendarView);
        selectedDate = view.findViewById(R.id.tv_selectedDate);

        //information needed = date, classType, classTitle, courseCode, classRoom, classTime

        //add all class Schedule to calendar
        List<CalendarDay> classSchedule = new ArrayList<>();
        classSchedule.add(CalendarDay.today());
        classSchedule.add(CalendarDay.from(Calendar.getInstance().getTime()));
        calendarView.setSelectedDate(CalendarDay.today());
        calendarView.addDecorator(new EventDecorator(Color.parseColor("#c0c0c0"),classSchedule));

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Calendar calendar = date.getCalendar();
                int currentDay= calendar.getTime().getDay();
                int currentDate = calendar.getTime().getDate();
                int currentMonth = calendar.getTime().getMonth();
                int currentYear = calendar.getTime().getYear()+1900;

                selectedDate.setText(days[currentDay]+", "+currentDate+" "+months[currentMonth]+" "+currentYear);
            }
        });



    }
}
