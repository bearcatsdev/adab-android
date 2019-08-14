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

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.*;

import java.util.Calendar;


public class CalendarFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    CalendarView calendarView;
    TextView currentDate;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.calendarView);
        currentDate = view.findViewById(R.id.tv_currentDate);

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        Log.e("tommorow",calendar+"");
        events.add(new EventDay(calendar, R.drawable.ic_fiber_manual_record_black_24dp));
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                int day = clickedDayCalendar.get(Calendar.DAY_OF_WEEK);
                int date = clickedDayCalendar.get(Calendar.DATE);
                int month = clickedDayCalendar.get(Calendar.MONTH);
                int year = clickedDayCalendar.get(Calendar.YEAR);
                currentDate.setText(date+" "+day+" "+month+" "+year);
            }
        });

    }
}
