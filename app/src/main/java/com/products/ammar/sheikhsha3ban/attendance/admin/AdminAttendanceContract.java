package com.products.ammar.sheikhsha3ban.attendance.admin;

import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;
import com.products.ammar.sheikhsha3ban.common.data.model.UserDayAttendanceModel;

import java.util.ArrayList;

public interface AdminAttendanceContract {

    interface Views extends IBaseView<Actions> {
        void showUsers(ArrayList<UserDayAttendanceModel> userDayAttendanceModel);
    }


    /**
     * Actions methods implemented by presenter
     */
    interface Actions extends IBaseActions {

    }
}
