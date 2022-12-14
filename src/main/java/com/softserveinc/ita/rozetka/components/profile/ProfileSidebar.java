package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.profile.ProfilePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfileSidebar {

    @Step("Profile sidebar: open profile page")
    public ProfilePage openProfilePage() {
        $x("//div[contains(@class,'cabinet-user')]/a").click();
        return new ProfilePage();
    }
}