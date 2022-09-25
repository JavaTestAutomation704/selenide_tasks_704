package com.softserveinc.ita.rozetka.profile;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.components.profile.PersonalDataSection;
import com.softserveinc.ita.rozetka.modals.PasswordEditingModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.data.ProfileSection.PERSONAL_DATA;
import static java.lang.String.format;

public class ProfilePage extends ProfileBasePage {

    @Step("Profile page: open personal data section")
    public PersonalDataSection openPersonalDataSection() {
        var personalDataSectionXpath = format("//h3[text()='%s']/ancestor::details", PERSONAL_DATA.getName());
        $x(personalDataSectionXpath).click();
        return new PersonalDataSection(personalDataSectionXpath);
    }

    @Step("Profile page: log out")
    public HomePage logOut() {
        $x("(//div/button[contains(@class,'button button--medium button--link')])[3]").click();
        return new HomePage();
    }

    public String getUserEmail() {
        return $x("//p[@class='cabinet-user__email']").text();
    }

    @Step("Profile page: start edit password")
    public PasswordEditingModal startEditPassword() {
        $x("(//div/button[contains(@class, 'button--link')])[2]").click();
        return new PasswordEditingModal();
    }
}