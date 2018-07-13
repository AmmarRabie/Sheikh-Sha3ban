package com.products.ammar.sheikhsha3ban.attendance.viewer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.model.UserMonthAttendanceModel;
import com.products.ammar.sheikhsha3ban.common.util.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import es.dmoral.toasty.Toasty;

public class ViewerAttendanceFragment extends Fragment implements ViewerAttendanceContract.Views {

    private static final String ENABLE_EDIT_KEY = "enable-edit";
    private static final String USER_ID_KEY = "user-id";

    private ViewerAttendanceContract.Actions mActions;

    // views
    private MaterialCalendarView calendarView;

    private ArrayList<CalendarDay> currentSelected;


    public static ViewerAttendanceFragment newInstance() {
        return new ViewerAttendanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_attendance_viewer, container, false);
        calendarView = root.findViewById(R.id.attendanceFrag_calender);
        currentSelected = new ArrayList<>();
        setupCalendar();
        CalendarDay currentDate = calendarView.getCurrentDate();
        mActions.getMonthAttendance(currentDate.getYear(), currentDate.getMonth());
        return root;
    }

    private void setupCalendar() {
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
        calendarView.setOnDateLongClickListener(new OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull MaterialCalendarView calendar, @NonNull CalendarDay calendarDay) {
                int day = calendarDay.getDay();
                int year = calendarDay.getYear();
                int month = calendarDay.getMonth();
                if (currentSelected.contains(calendarDay))
                    mActions.removeAttendance(year, month, day);
                else mActions.addAttendanceOfDay(year, month, day);
                Toast.makeText(getContext()
                        , String.format("long click: %s/%s/%s", day, month + 1, year)
                        , Toast.LENGTH_SHORT).show();
            }
        });
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
                mActions.getMonthAttendance(calendarDay.getYear(), calendarDay.getMonth());
            }
        });
        calendarView.addDecorators(FridayDisableDecorator.getInstance(), new AttendanceMarkerDecorator(getContext(), currentSelected));
    }

    private void updateDecorators() {
        calendarView.removeDecorators();
        calendarView.addDecorators(new AttendanceMarkerDecorator(getContext(), currentSelected)
                , FridayDisableDecorator.getInstance());
    }

    @Override
    public void setPresenter(ViewerAttendanceContract.Actions presenter) {
        mActions = presenter;

    }

    @Override
    public void markDateAsAttended(CalendarDay day) {
//        calendarView.setDateSelected(day, true);
        if (currentSelected.contains(day))
            return;
        currentSelected.add(day);
        updateDecorators();
    }

    @Override
    public void markDateAsAttended(Date day) {
//        calendarView.setDateSelected(day, true);
        markDateAsAttended(new CalendarDay(day));
    }

    @Override
    public void removeDateAsAttended(CalendarDay day) {
//        calendarView.setDateSelected(day, false);
        if (!currentSelected.contains(day))
            return;
        currentSelected.remove(day);
        updateDecorators();
    }

    @Override
    public void removeDateAsAttended(Date day) {
//        calendarView.setDateSelected(day, false);
        removeDateAsAttended(new CalendarDay(day));
    }

    @Override
    public void changeDateAttendance(UserMonthAttendanceModel data, int year, int month) {
        currentSelected.clear();
        SparseBooleanArray attendance = data.getAttendance();
        for (int day = 0; day < attendance.size(); day++) {
            boolean isAttend = attendance.get(day);
            Date date = DateUtil.getDate(year, month, day + 1);
            if (isAttend) currentSelected.add(new CalendarDay(date));
        }
        updateDecorators();
    }

    @Override
    public void showErrorMessage(String message) {
        Toasty.error(getContext(),message,Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void showInfoMessage(String message) {
        Toasty.info(getContext(),message,Toast.LENGTH_SHORT,true).show();
    }

    private static final class FridayDisableDecorator implements DayViewDecorator {

        private static FridayDisableDecorator _INSTANCE;

        private FridayDisableDecorator() {
        }

        public static FridayDisableDecorator getInstance() {
            if (_INSTANCE == null)
                _INSTANCE = new FridayDisableDecorator();
            return _INSTANCE;
        }

        @Override
        public boolean shouldDecorate(CalendarDay calendarDay) {
            Calendar c = Calendar.getInstance();
            c.setTime(calendarDay.getDate());
            return c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
        }

        @Override
        public void decorate(DayViewFacade dayViewFacade) {
            dayViewFacade.setDaysDisabled(true);
        }

    }

    private static final class AttendanceMarkerDecorator implements DayViewDecorator {

        private final Context mContext;
        private final HashSet<CalendarDay> dates;

        AttendanceMarkerDecorator(Context context, Collection<CalendarDay> dates) {
            this.mContext = context;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            int color = ContextCompat.getColor(mContext, R.color.attend);
            view.addSpan(new DotSpan(5, color));
        }

    }

}

