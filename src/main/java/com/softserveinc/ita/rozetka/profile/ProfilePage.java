package com.softserveinc.ita.rozetka.profile;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.components.profile.PersonalDataSection;
import com.softserveinc.ita.rozetka.data.ProfileSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ProfilePage extends ProfileBasePage {

    private final PersonalDataSection personalDataSection = new PersonalDataSection();

    @Step("Profile page: open section {profileSection}")
    public ProfilePage openSection(ProfileSection profileSection) {
        $x(format("//h3[text()='%s']/ancestor::details", profileSection.getName())).click();
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