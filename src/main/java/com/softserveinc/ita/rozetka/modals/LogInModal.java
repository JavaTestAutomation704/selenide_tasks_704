package com.softserveinc.ita.rozetka.modals;

import static utils.WebElementUtil.isVisible;

public class LogInModal {

    public boolean isLogInModalVisible() {
        return isVisible("//rz-user-identification");
    }
}
