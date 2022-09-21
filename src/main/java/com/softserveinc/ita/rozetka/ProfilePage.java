package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.profile.CardDataSection;
import com.softserveinc.ita.rozetka.profile.ProfileBasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage extends ProfileBasePage {

    @Step("Profile page: log out")
    public HomePage logOut() {
        $x("(//div/button[contains(@class,'button button--medium button--link')])[3]").click();
        return new HomePage();
    }

    public String getUserEmail() {
        return $x("//p[@class='cabinet-user__email']").text();
    }
}
