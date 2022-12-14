package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.seller.registration.form.StepOneSellerRegistrationForm;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SellerRegistrationPage {

    public boolean isOpened() {
        return isVisible("//div[@class = 'registration--body']");
    }

    public StepOneSellerRegistrationForm getStepOneSellerRegistrationForm() {
        return new StepOneSellerRegistrationForm();
    }
}