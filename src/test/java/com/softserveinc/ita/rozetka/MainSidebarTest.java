package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.RU;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class MainSidebarTest extends TestRunner {
    private Header header;

    @Test
    public void verifyLocalizationSwitchingFunctionalityWorks() {
        header = homePage.getHeader();

        assertThat(header.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();

        var mainSidebar = header.openMainSidebar();

        var softly = new SoftAssertions();
        assertThat(mainSidebar.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();
        softly.assertThat(mainSidebar.getLoginButtonName())
                .as("Incorrect login button name")
                .isEqualTo("Вхід");
        softly.assertThat(mainSidebar.getRegistrationButtonName())
                .as("Incorrect registration button name")
                .isEqualTo("Реєстрація");
        softly.assertThat(mainSidebar.getHelpCenterButtonName())
                .as("Incorrect help center button name")
                .isEqualTo("Довідковий центр");
        softly.assertThat(mainSidebar.getContactUsButtonName())
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
        softly.assertThat(mainSidebar.getLoginButtonName())
                .as("Incorrect login button name")
                .isEqualTo("Вход");
        softly.assertThat(mainSidebar.getRegistrationButtonName())
                .as("Incorrect registration button name")
                .isEqualTo("Регистрация");
        softly.assertThat(mainSidebar.getHelpCenterButtonName())
                .as("Incorrect help center button name")
                .isEqualTo("Справочный центр");
        softly.assertThat(mainSidebar.getContactUsButtonName())
                .as("Incorrect contact us button name")
                .isEqualTo("Связаться с нами");

        mainSidebar.changeLanguage(UA);

        softly.assertThat(header.isLanguageSelected(UA))
                .as("Localization should be switched to UA")
                .isTrue();
        softly.assertAll();
    }

    @AfterMethod
    public void resetLanguage() {
        if (!header.isLanguageSelected(UA)) {
            header
                    .openMainSidebar()
                    .changeLanguage(UA);
        }
    }
}