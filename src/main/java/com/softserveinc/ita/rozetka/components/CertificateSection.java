package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.data.Color;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

@RequiredArgsConstructor
public class CertificateSection {
    @NonNull
    private int orderNumber;

    private final String certificateSectionXpathTemplate = "(//div[@class = 'checkout-order'])[%d]" +
            "//div[contains(@class, 'certificate-wrap')]";
    private final String applyCertificateButtonXpath = certificateSectionXpathTemplate +
            "//button[contains(@class, 'certificate-form__button')]";
    private final String usedCertificateFieldXpath = certificateSectionXpathTemplate +
            "//p[@class = 'certificate-used__code error']";

    public boolean isAddCertificateOptionAvailable() {
        return isVisible(format("(//div[@class = 'checkout-order'])[%d]" +
                "//div[contains(@class, 'certificate-wrap')]/button", orderNumber));
    }

    public boolean isOpened() {
        return isVisible(format(certificateSectionXpathTemplate +
                "//div[contains(@class, 'certificate-form__inner')]", orderNumber));
    }

    @Step("Certificate section: close certificate section")
    public OrderSection closeCertificateSection() {
        $x(format(certificateSectionXpathTemplate + "//button[contains(@class, 'certificate-header')]", orderNumber)).click();
        return new OrderSection(orderNumber);
    }

    @Step("Certificate section: fill in certificate field {certificateCode}")
    public CertificateSection fillInCertificateField(String certificateCode) {
        $x(format(certificateSectionXpathTemplate + "//input[@id = 'certificateInput']", orderNumber)).val(certificateCode);
        return this;
    }

    public boolean isApplyButtonDisabled() {
        return hasAttribute(format(applyCertificateButtonXpath, orderNumber), "disabled");
    }

    @Step("Certificate section: apply certificate")
    public CertificateSection applyCertificate() {
        $x(format(applyCertificateButtonXpath, orderNumber)).click();
        return this;
    }

    public String getCertificateFieldErrorMessage() {
        waitTillVisible(format(usedCertificateFieldXpath, orderNumber));
        return $x(format(certificateSectionXpathTemplate + "//div[contains(@class, 'form__hint_type_warning')]",
                orderNumber)).text();
    }

    @Step("Certificate section: remove certificate")
    public OrderSection removeCertificate() {
        $x(format(certificateSectionXpathTemplate + "//button[contains(@class, 'certificate-used__button')]",
                orderNumber)).click();
        return new OrderSection(orderNumber);
    }
}