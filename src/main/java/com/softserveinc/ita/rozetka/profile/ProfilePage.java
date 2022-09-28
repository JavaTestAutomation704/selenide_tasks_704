package com.softserveinc.ita.rozetka.profile;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.components.profile.CardDataSection;
import com.softserveinc.ita.rozetka.components.profile.MyOrderRecipientsSection;
import com.softserveinc.ita.rozetka.components.profile.PersonalDataSection;
import com.softserveinc.ita.rozetka.modals.PasswordChangeModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.data.ProfileSection.MY_ORDER_RECIPIENTS;
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

    @Step("Profile page: start change password")
    public PasswordChangeModal startChangePassword() {
        $x("(//div/button[contains(@class, 'button--link')])[2]").click();
        return new PasswordChangeModal();
    }

    @Step("Profile page: open card data section")
    public CardDataSection openCardDataSection() {
        $x("//button[contains(@class,'wallet-cards__add')]").click();
        return new CardDataSection();
    }
    
    @Step("Profile page: open my order recipients section")
    public MyOrderRecipientsSection openMyOrderRecipientsSection() {
        $x(format("//h3[text()='%s']/ancestor::details", MY_ORDER_RECIPIENTS.getName())).click();
        return new MyOrderRecipientsSection();
    }
}