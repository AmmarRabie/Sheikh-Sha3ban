package com.products.ammar.sheikhsha3ban.attendance.viewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.products.ammar.sheikhsha3ban.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class ViewerAttendanceFragment extends Fragment implements ViewerAttendanceContract.Views {

    private static final String ENABLE_EDIT_KEY = "enable-edit";
    private static final String USER_ID_KEY = "user-id";

    private ViewerAttendanceContract.Actions mActions;
    private boolean enableEdit;
//    private String userId;
    // views
    private MaterialCalendarView calendarView;

    public static ViewerAttendanceFragment newInstance(boolean enableEdit) {
        ViewerAttendanceFragment fragment = new ViewerAttendanceFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean(ENABLE_EDIT_KEY, enableEdit);
//        arguments.putString(USER_ID_KEY, userId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        enableEdit = getArguments() != null && getArguments().getBoolean(ENABLE_EDIT_KEY, false);
//        userId = getArguments().getString(USER_ID_KEY, "");

        View root = inflater.inflate(R.layout.frag_attendance_viewer, container, false);
        calendarView = root.findViewById(R.id.attendanceFrag_calender);
        if (enableEdit)
            setupWithEditing();
        calendarView.addDecorator(new DayViewDecorator() {
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
        });
        return root;
    }

    private void setupWithEditing() {
        calendarView.setOnDateLongClickListener(new OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull MaterialCalendarView calendar, @NonNull CalendarDay calendarDay) {
                mActions.addAttendanceOfDay(calendarDay.getYear(), calendarDay.getMonth(), calendarDay.getDay());
                calendarView.setDateSelected(calendarDay, true);
                Toast.makeText(getContext(), "hhh", Toast.LENGTH_SHORT).show();
            }
        });
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
                int month = calendarDay.getMonth() + 1;
            }
        });
    }

    @Override
    public void setPresenter(ViewerAttendanceContract.Actions presenter) {
        mActions = presenter;
    }
}
