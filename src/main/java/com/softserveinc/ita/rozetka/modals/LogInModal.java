package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class LogInModal {

    public LogInModal open() {
        $x("//rz-user").click();
        return this;
    }

    public boolean isLogInModalVisible() {
        return isVisible("//rz-user-identification");
    }
}
