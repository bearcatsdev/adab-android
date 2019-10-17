package com.ambinusian.adab.ui.student;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.recyclerview.classlist.ClassListAdapter;
import com.ambinusian.adab.recyclerview.classlist.ClassListModel;
import com.ambinusian.adab.recyclerview.schedule.ScheduleAdapter;
import com.ambinusian.adab.recyclerview.schedule.ScheduleModel;
import com.ambinusian.adab.room.ClassDatabase;
import com.ambinusian.adab.room.ClassEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class FragmentCalendar extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_calendar, container, false);
    }

    TextView selectedDate;
    ArrayList<ScheduleModel> allClassSchedule;
    SimpleDateFormat dateFormat;
    RecyclerView scheduleList;
    Date time;
    LinearLayout emptyClass;
    HorizontalCalendar.Builder builder;
    ClassDatabase db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedDate = view.findViewById(R.id.tv_selectedDate);
        scheduleList = view.findViewById(R.id.rv_schedule);
        emptyClass = view.findViewById(R.id.empty_class_layout);
        allClassSchedule = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        db = ClassDatabase.getDatabase(getContext());

        db.classDAO().getAllClass().observe(getActivity(), new Observer<List<ClassEntity>>() {
            @Override
            public void onChanged(List<ClassEntity> classEntities) {
                Date temp = null;
                for(ClassEntity classEntity : classEntities){
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(classEntity.getSessionStartDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    allClassSchedule.add(new ScheduleModel(
                            new SimpleDateFormat("yyyy-MM-dd hh:mm").format(temp),
                            (String) classEntity.getSessionMode(),
                            (String) classEntity.getTopicTitle(),
                            (String) classEntity.getCourseName(),
                            (String) classEntity.getCourseId(),
                            (String) classEntity.getClassName(),
                            new SimpleDateFormat("hh:mm").format(temp)));
                }

                /* starts before 1 month from now */
                Calendar startDate = Calendar.getInstance();
                startDate.add(Calendar.YEAR, -10);

                /* ends after 1 month from now */
                Calendar endDate = Calendar.getInstance();
                endDate.add(Calendar.YEAR, 10);

                builder = new HorizontalCalendar.Builder(getActivity(), R.id.calendarView).range(startDate, endDate)
                        .datesNumberOnScreen(5);
                HorizontalCalendar horizontalCalendar = builder.addEvents(new CalendarEventsPredicate() {
                    @Override
                    public List<CalendarEvent> events(Calendar date) {
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

                        List<CalendarEvent> events = new ArrayList<>();

                        for(int i = 0; i < selectedDateClasses.size();i++){
                            events.add(new CalendarEvent(getResources().getColor(R.color.colorAccent)));
                        }
                        return events;
                    }
                })
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

                        if(selectedDateClasses.size() == 0){
                            emptyClass.setVisibility(View.VISIBLE);
                            scheduleList.setVisibility(View.GONE);
                        }
                        else {
                            emptyClass.setVisibility(View.GONE);
                            scheduleList.setVisibility(View.VISIBLE);
                            //set recycler view adapter
                            ScheduleAdapter adapter = new ScheduleAdapter(getContext(), selectedDateClasses);
                            scheduleList.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {

                    }
                });

                //set recycler view layout manager
                scheduleList.setLayoutManager(new LinearLayoutManager(getContext()));

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

                firstTimeLayout();

                horizontalCalendar.refresh();
            }
        });
    }

    public void firstTimeLayout(){
        Calendar calendar = Calendar.getInstance();
        int currentDate = calendar.getTime().getDate();
        int currentMonth = calendar.getTime().getMonth() + 1;
        int currentYear = calendar.getTime().getYear() + 1900;

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

            if (selectedDateCalendar.equals(calendar)) {
                ScheduleModel item = allClassSchedule.get(i);
                selectedDateClasses.add(item);
            }
        }

        if(selectedDateClasses.size() == 0){
            emptyClass.setVisibility(View.VISIBLE);
            scheduleList.setVisibility(View.GONE);
        }
        else {
            emptyClass.setVisibility(View.GONE);
            scheduleList.setVisibility(View.VISIBLE);
            //set recycler view adapter
            ScheduleAdapter adapter = new ScheduleAdapter(getContext(), selectedDateClasses);
            scheduleList.setAdapter(adapter);
        }
    }
}
