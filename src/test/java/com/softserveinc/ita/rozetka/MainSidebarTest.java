package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MainSidebar;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.RU;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.*;

public class MainSidebarTest extends TestRunner {

    @Test
    public void verifyLocalizationSwitchingFunctionalityWorks() {
        Header header = homePage.getHeader();

        assertThat(header.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();

        MainSidebar mainSidebar = header.openMainSidebar();

        SoftAssertions softAssertions = new SoftAssertions();
        assertThat(mainSidebar.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();
        softAssertions.assertThat(mainSidebar.getLoginButtonName())
                .as("Incorrect login button name")
                .isEqualTo("Вхід");
        softAssertions.assertThat(mainSidebar.getRegistrationButtonName())
                .as("Incorrect registration button name")
                .isEqualTo("Реєстрація");
        softAssertions.assertThat(mainSidebar.getHelpCenterButtonName())
                .as("Incorrect help center button name")
                .isEqualTo("Довідковий центр");
        softAssertions.assertThat(mainSidebar.getContactUsButtonName())
                .as("Incorrect contact us button name")
                .isEqualTo("Зв'язатися з нами");

        mainSidebar.changeLanguage(RU);

        assertThat(header.isLanguageSelected(RU))
                .as("Localization should be switched to RU")
                .isTrue();

        header.openMainSidebar();

        assertThat(mainSidebar.isLanguageSelected(RU))
                .as("Localization should be switched to RU")
                .isTrue();
        softAssertions.assertThat(mainSidebar.getLoginButtonName())
                .as("Incorrect login button name")
                .isEqualTo("Вход");
        softAssertions.assertThat(mainSidebar.getRegistrationButtonName())
                .as("Incorrect registration button name")
                .isEqualTo("Регистрация");
        softAssertions.assertThat(mainSidebar.getHelpCenterButtonName())
                .as("Incorrect help center button name")
                .isEqualTo("Справочный центр");
        softAssertions.assertThat(mainSidebar.getContactUsButtonName())
                .as("Incorrect contact us button name")
                .isEqualTo("Связаться с нами");
        softAssertions.assertAll();
    }
}
