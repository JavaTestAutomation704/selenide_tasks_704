package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.profile.ProfilePage;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class MainSidebar {

    @Getter
    private final DownloadApplicationSection downloadApplicationSection = new DownloadApplicationSection();

    private final String buttonXpathTemplateAuthentication = "(//button[contains(@class,'side-menu__auth-button')])[%d]";

    public String getLoginButtonName() {
        return getText(format(buttonXpathTemplateAuthentication, 1));
    }

    public String getRegistrationButtonName() {
        return getText(format(buttonXpathTemplateAuthentication, 2));
    }

    public String getHelpCenterButtonName() {
        return getText("//a[contains(@class,'side-menu__button') and contains(@href,'help.')]");
    }

    public String getContactUsButtonName() {
        return getText("//a[contains(@class,'side-menu__button') and contains(@href,'t.me')]");
    }

    public String getCity() {
        SelenideElement citySpan = $x("//span[@class='city-toggle__text']").shouldBe(Condition.visible);
        return citySpan.text();
    }

    @Step("Main sidebar: change city to {city}")
    public Header changeCity(String city) {
        $x("//button[contains(@class, 'city-toggle')]").click();
        return new ChangeCityModal().changeCity(city);
    }

    @Step("Main Sidebar: open home page via logo")
    public HomePage openHomePageViaLogo() {
        $x("//a[contains(@class, 'side-menu__logo')]").click();
        return new HomePage();
    }

    public boolean isLanguageSelected(Language language) {
        return isVisible(
                format("(//li[contains(@class, 'lang__item')]/span[contains(text(),'%s')])[2]", language));
    }

    @Step("Main sidebar: change language to {language}")
    public Header changeLanguage(Language language) {
        $x(format("//li[contains(@class, 'side-menu')]//*[contains(text(),'%s')]", language)).click();
        return new Header();
    }

    public boolean isOpened() {
        return isVisible("//div[contains(@class, 'side-menu drawer') " +
                "and contains(@class, 'drawer-content_state_visible')]");
    }

    @Step("Main sidebar: open profile page")
    public ProfilePage openProfilePage() {
        $x("//div[contains(@class,'side-menu__auth ')]").click();
        return new ProfilePage();
    }
}