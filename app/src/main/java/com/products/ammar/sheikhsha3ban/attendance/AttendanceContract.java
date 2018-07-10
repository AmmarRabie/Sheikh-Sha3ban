package com.products.ammar.sheikhsha3ban.attendance;

import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;

public interface AttendanceContract {

    interface Views extends IBaseView<Actions> {

    }


    /**
     * Actions methods implemented by presenter
     */
    interface Actions extends IBaseActions {

    }
}
