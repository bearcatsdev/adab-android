package com.ambinusian.adab.ui.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambinusian.adab.R;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.selection.BaseSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.MultipleSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager;
import com.applikeysolutions.cosmocalendar.settings.appearance.ConnectedDayIconPosition;
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.cosmo_calendar);

        //Set Orientation 0 = Horizontal | 1 = Vertical
        calendarView.setCalendarOrientation(0);
        calendarView.isSelected();
        calendarView.onDaySelected();
        Toast.makeText(getContext(), calendarView.getSelectedDates()+"", Toast.LENGTH_SHORT).show();

        //set weekend
        calendarView.setWeekendDays(new HashSet(){{
            add(Calendar.SUNDAY);
        }});

        //set holday to red
        calendarView.setWeekendDayTextColor( Color.parseColor("#ff0000"));

        //set selected day background color
        calendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.colorAccent));

        //Range Selection
        calendarView.setSelectionType(SelectionType.SINGLE);


        //Set days you want to connect
        Calendar calendar = Calendar.getInstance();
        Set<Long> days = new TreeSet<>();
        days.add(calendar.getTimeInMillis());
        days.add(calendar.getTimeInMillis()+100000000);

        //Define colors
        int textColor = Color.parseColor("#000000");
        int selectedTextColor = Color.parseColor("#ffffff");
        int disabledTextColor = Color.parseColor("#ff8000");
        ConnectedDays connectedDays = new ConnectedDays(days, textColor, selectedTextColor, disabledTextColor);

        //Connect days to calendar
        calendarView.addConnectedDays(connectedDays);
        calendarView.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM);
        calendarView.setConnectedDayIconRes(R.drawable.ic_icon_menu);

    }
}
