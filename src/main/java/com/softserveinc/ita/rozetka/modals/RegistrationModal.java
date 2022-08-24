package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class RegistrationModal {
    public RegistrationModal open() {
        $x("//rz-user").click();
        return this;
    }
    public boolean isOpen() {
        return isVisible("//div[@class='modal__holder modal__holder_show_animation modal__holder--medium']");
    }
}