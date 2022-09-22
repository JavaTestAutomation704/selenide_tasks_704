package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.switchToTab;
import static org.assertj.core.api.Assertions.assertThat;

public class GiftCertificateTest extends BaseTestRunner {

    @Test
    public void verifyUserIsNotAbleToTransferCertificateAndErrorMessagesAppearWhenFieldsAreFilledInWithInvalidData() {
        var header = homePage.getHeader();
        if (!header.isLanguageSelected(UA)) {
            header
                    .openMainSidebar()
                    .changeLanguage(UA);
        }

        var giftCertificatesPage = homePage
                .getServiceSection()
                .openGiftCertificatesPage();

        assertThat(giftCertificatesPage.isOpened())
                .as("Gift certificates page should be opened")
                .isTrue();

        var electronicGiftCertificatesTransferSection = giftCertificatesPage.openElectronicGiftCertificatesTransferSection();

        assertThat(electronicGiftCertificatesTransferSection.isOpened())
                .as("Electronic gift certificates transfer section should be opened")
                .isTrue();

        var giftCertificatesTransferPage = electronicGiftCertificatesTransferSection.openGiftCertificatesTransferPage();

        switchToTab(1);

        assertThat(giftCertificatesTransferPage.isOpened())
                .as("Gift certificate transfer page should be opened")
                .isTrue();

        giftCertificatesTransferPage.fillInTransferForm("631234", "633456", "GYTR");

        var softly = new SoftAssertions();

        softly.assertThat(giftCertificatesTransferPage.isGiftButtonDisabled())
                .as("Gift button should be disabled")
                .isTrue();

        giftCertificatesTransferPage.clearCertificateField();

        var emptyCertificateFieldErrorMessage = giftCertificatesTransferPage.getCertificateErrorMessage();

        softly.assertThat(emptyCertificateFieldErrorMessage)
                .as("Error message should appear")
                .isEqualTo("Поле обов'язкове для заповнення");

        softly.assertThat(giftCertificatesTransferPage.isCertificateFieldBorderColorCorrect())
                .as("Certificate field border color should be correct")
                .isTrue();

        giftCertificatesTransferPage.fillInTransferForm("631122345", "631133456", "ABGYRTYUERTYUIOP");

        assertThat(giftCertificatesTransferPage.isGiftButtonDisabled())
                .as("Gift button should be enabled")
                .isFalse();

        giftCertificatesTransferPage.submitGiftTransferForm();

        var certificateFieldWithInvalidDataErrorMessage = giftCertificatesTransferPage.getCertificateErrorMessage();

        softly.assertThat(certificateFieldWithInvalidDataErrorMessage)
                .as("Error message should appear")
                .isEqualTo("Номер сертифіката або номер телефону власника не вірні. Перевірте коректність даних");

        softly.assertThat(giftCertificatesTransferPage.isCertificateFieldBorderColorCorrect())
                .as("Certificate field border color should be correct")
                .isTrue();

        softly.assertAll();
    }
}