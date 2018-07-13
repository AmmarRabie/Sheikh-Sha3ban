package com.products.ammar.sheikhsha3ban.attendance.viewer;

import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;
import com.products.ammar.sheikhsha3ban.common.data.model.UserMonthAttendanceModel;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;
import java.util.Date;

public interface ViewerAttendanceContract {

    interface Views extends IBaseView<Actions> {
        void markDateAsAttended(CalendarDay day);
        void markDateAsAttended(Date day);
        void removeDateAsAttended(CalendarDay day);
        void removeDateAsAttended(Date day);

        void showErrorMessage(String message);
        void showInfoMessage(String message);

        void changeDateAttendance(UserMonthAttendanceModel data, int year, int month);
    }


    /**
     * Actions methods implemented by presenter
     */
    interface Actions extends IBaseActions {

        /**
         * Attend the user into the day passed to func
         */
        void addAttendanceOfDay(int year, int month, int day);

        /**
         * Remove the attendance of the day passed
         */
        void removeAttendance(int year, int month, int day);

        void getMonthAttendance(int year, int month);
    }
}
