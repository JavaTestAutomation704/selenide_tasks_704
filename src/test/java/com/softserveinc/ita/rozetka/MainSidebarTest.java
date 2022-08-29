package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MainSidebar;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.RU;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.*;

public class MainSidebarTest extends TestRunner {

    @Test
    public void verifyLanguageSwitch() {
        Header header = homePage.getHeader();

        assertThat(header.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();
        assertThat(header.isLanguageSelected(RU))
                .as("Localization shouldn't be switched to RU")
                .isFalse();

        MainSidebar mainSidebar = header.openMobileMenu();

        assertThat(mainSidebar.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();
        assertThat(mainSidebar.isLanguageSelected(RU))
                .as("Localization shouldn't be switched to RU")
                .isFalse();
        assertThat(mainSidebar.getLoginButtonName())
                .as("Incorrect login button name")
                .isEqualTo("Вхід");
        assertThat(mainSidebar.getRegistrationButtonName())
                .as("Incorrect registration button name")
                .isEqualTo("Реєстрація");
        assertThat(mainSidebar.getHelpCenterButtonName())
                .as("Incorrect help center button name")
                .isEqualTo("Довідковий центр");
        assertThat(mainSidebar.getContactUsButtonName())
                .as("Incorrect contact us button name")
                .isEqualTo("Зв'язатися з нами");

        mainSidebar.changeLanguage(RU);

        assertThat(header.isLanguageSelected(UA))
                .as("Localization shouldn't be switched to UA")
                .isFalse();
        assertThat(header.isLanguageSelected(RU))
                .as("Localization should be switched to RU")
                .isTrue();

        header.openMobileMenu();

        assertThat(mainSidebar.isLanguageSelected(UA))
                .as("Localization shouldn't be switched to UA")
                .isFalse();
        assertThat(mainSidebar.isLanguageSelected(RU))
                .as("Localization should be switched to RU")
                .isTrue();
        assertThat(mainSidebar.getLoginButtonName())
                .as("Incorrect login button name")
                .isEqualTo("Вход");
        assertThat(mainSidebar.getRegistrationButtonName())
                .as("Incorrect registration button name")
                .isEqualTo("Регистрация");
        assertThat(mainSidebar.getHelpCenterButtonName())
                .as("Incorrect help center button name")
                .isEqualTo("Справочный центр");
        assertThat(mainSidebar.getContactUsButtonName())
                .as("Incorrect contact us button name")
                .isEqualTo("Связаться с нами");
    }
}
