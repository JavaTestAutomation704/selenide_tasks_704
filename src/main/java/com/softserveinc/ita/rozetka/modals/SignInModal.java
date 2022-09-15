package com.softserveinc.ita.rozetka.modals;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SignInModal {
    private final ConfigProperties property = new ConfigProperties();

    @Step("Sign in modal: sign in by facebook")
    public HomePage signInByFacebook() {
        $x("//button[contains(text(),'Facebook')]").click();
        if (!isVisible("//a[@class='header__button ng-star-inserted']", 4)) {
            Selenide.switchTo().window(1);
            $x("//input[@id='email']").setValue(property.getUserEmailOrPhoneNumber());
            $x("//input[@id='pass']").setValue(property.getUserPassword());
            $x("//input[@name='login']").click();
            Selenide.switchTo().window(0);
        }
        return new HomePage();
    }

    public boolean isSignInModalVisible() {
        return isVisible("//div[@class='modal__content']");
    }
}