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
                .as("'Login' button name is incorrect")
                .isEqualTo("Вхід");
        assertThat(mainSidebar.getRegistrationButtonName())
                .as("'Registration' button name is incorrect")
                .isEqualTo("Реєстрація");
        assertThat(mainSidebar.getHelpCenterButtonName())
                .as("'Help center' button name is incorrect")
                .isEqualTo("Довідковий центр");
        assertThat(mainSidebar.getContactUsButtonName())
                .as("'Contact us' button name is incorrect")
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
                .as("'Login' button name is incorrect")
                .isEqualTo("Вход");
        assertThat(mainSidebar.getRegistrationButtonName())
                .as("'Registration' button name is incorrect")
                .isEqualTo("Регистрация");
        assertThat(mainSidebar.getHelpCenterButtonName())
                .as("'Help center' button name is incorrect")
                .isEqualTo("Справочный центр");
        assertThat(mainSidebar.getContactUsButtonName())
                .as("'Contact us' button name is incorrect")
                .isEqualTo("Связаться с нами");
    }
}
