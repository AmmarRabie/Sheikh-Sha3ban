package com.products.ammar.sheikhsha3ban.attendance.viewer;

import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;

public interface ViewerAttendanceContract {

    interface Views extends IBaseView<Actions> {

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
    }
}
