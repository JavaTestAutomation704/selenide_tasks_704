package com.softserveinc.ita.rozetka.modals;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class RegistrationModal {
    public boolean isOpen() {
        return isVisible("//div[@class='modal__holder modal__holder_show_animation modal__holder--medium']");
    }
}