package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.seller.registration.form.SellerRegistrationFormStepOne;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SellerRegistrationPage {

    public boolean isOpened() {
        return isVisible("//div[@class = 'registration--body']");
    }

    public SellerRegistrationFormStepOne getSellerRegistrationFormStepOne() {
        return new SellerRegistrationFormStepOne();
    }
}
