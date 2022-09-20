package com.softserveinc.ita.rozetka.profile;

import com.softserveinc.ita.rozetka.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.data.ProfileSection.PERSONAL_DATA;
import static java.lang.String.format;

public class ProfilePage extends ProfileBasePage {

    @Step("Profile page: open personal data section")
    public ProfilePage openPersonalDataSection() {
        $x(format("//h3[text()='%s']/ancestor::details", PERSONAL_DATA.getName())).click();
        return this;
    }

    @Step("Profile page: sign out")
    public HomePage signOut() {
        $x("(//div/button[contains(@class,'button button--medium button--link')])[3]").click();
        return new HomePage();
    }

    public String getUserEmail() {
        return $x("//p[@class='cabinet-user__email']").text();
    }
}