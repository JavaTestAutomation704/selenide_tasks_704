package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MobileMenu;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.RU;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.*;

public class MobileMenuTest extends TestRunner {

    @Test
    public void verifyLanguageSwitch() {
        Header header = homePage.getHeader();

        assertThat(header.isLanguageSelected(UA))
                .as("Should be UA")
                .isTrue();
        assertThat(header.isLanguageSelected(RU))
                .as("Should not be RU")
                .isFalse();

        MobileMenu mobileMenu = header.openMobileMenu();

        assertThat(mobileMenu.isLanguageSelected(UA))
                .as("Should be UA")
                .isTrue();
        assertThat(mobileMenu.isLanguageSelected(RU))
                .as("Should not be RU")
                .isFalse();
        assertThat(mobileMenu.getLoginButtonName()).isEqualTo("Вхід");
        assertThat(mobileMenu.getRegistrationButtonName()).isEqualTo("Реєстрація");
        assertThat(mobileMenu.getHelpCenterButtonName()).isEqualTo("Довідковий центр");
        assertThat(mobileMenu.getContactUsButtonName()).isEqualTo("Зв'язатися з нами");

        mobileMenu.changeLanguage(RU);

        assertThat(header.isLanguageSelected(UA))
                .as("Should not be UA")
                .isFalse();
        assertThat(header.isLanguageSelected(RU))
                .as("Should be RU")
                .isTrue();

        header.openMobileMenu();

        assertThat(mobileMenu.isLanguageSelected(UA))
                .as("Should not be UA")
                .isFalse();
        assertThat(mobileMenu.isLanguageSelected(RU))
                .as("Should be RU")
                .isTrue();
        assertThat(mobileMenu.getLoginButtonName()).isEqualTo("Вход");
        assertThat(mobileMenu.getRegistrationButtonName()).isEqualTo("Регистрация");
        assertThat(mobileMenu.getHelpCenterButtonName()).isEqualTo("Справочный центр");
        assertThat(mobileMenu.getContactUsButtonName()).isEqualTo("Связаться с нами");
    }
}
