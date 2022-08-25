package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MobileMenu;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.softserveinc.ita.rozetka.data.Language.RU;
import static com.softserveinc.ita.rozetka.data.Language.UA;

public class MobileMenuTest extends TestRunner {

    @Test
    public void verifyIsLanguageSwitches() {
        Header header = homePage.getHeader();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(header.isLanguageSelected(UA), "Should be UA");
        softAssert.assertFalse(header.isLanguageSelected(RU), "Should not be RU");
        MobileMenu mobileMenu = header.openMobileMenu();
        softAssert.assertTrue(mobileMenu.isLanguageSelected(UA), "Should be UA");
        softAssert.assertFalse(mobileMenu.isLanguageSelected(RU), "Should not be RU");
        softAssert.assertEquals(mobileMenu.getLoginText(), "Вхід");
        softAssert.assertEquals(mobileMenu.getRegistrationText(), "Реєстрація");
        softAssert.assertEquals(mobileMenu.getHelpCenterText(), "Довідковий центр");
        softAssert.assertEquals(mobileMenu.getContactUsText(), "Зв'язатися з нами");
        mobileMenu.changeLanguage();
        softAssert.assertFalse(header.isLanguageSelected(UA), "Should not be UA");
        softAssert.assertTrue(header.isLanguageSelected(RU), "Should be RU");
        header.openMobileMenu();
        softAssert.assertFalse(mobileMenu.isLanguageSelected(UA), "Should not be UA");
        softAssert.assertTrue(mobileMenu.isLanguageSelected(RU), "Should be RU");
        softAssert.assertEquals(mobileMenu.getLoginText(), "Вход");
        softAssert.assertEquals(mobileMenu.getRegistrationText(), "Регистрация");
        softAssert.assertEquals(mobileMenu.getHelpCenterText(), "Справочный центр");
        softAssert.assertEquals(mobileMenu.getContactUsText(), "Связаться с нами");
        softAssert.assertAll();
    }
}
