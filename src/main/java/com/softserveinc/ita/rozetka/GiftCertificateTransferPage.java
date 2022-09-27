package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.models.CertificateData;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class GiftCertificateTransferPage extends BasePage {

    private final String certificateFieldXpath = "//input[@id = 'certificateInput']";
    private final String transferFormGiftButtonXpath = "//button[@type = 'submit']";

    public boolean isOpened() {
        return isVisible("//div[@class = 'certificate__container']");
    }

    @Step("Gift certificate transfer page: fill in transfer form {certificate}")
    public GiftCertificateTransferPage fillInTransferForm(CertificateData certificate) {
        $x("//input[@id = 'ownerPhone']").val(certificate.getOwnerPhone());
        $x("//input[@id = 'futureOwnerPhone']").val(certificate.getFutureOwnerPhone());
        $x(certificateFieldXpath).val(certificate.getCode());
        return this;
    }

    @Step("Gift certificate transfer page: clear certificate field")
    public GiftCertificateTransferPage clearCertificateField() {
        $x(certificateFieldXpath).val("a").sendKeys(Keys.BACK_SPACE);
        return this;
    }

    @Step("Gift certificate transfer page: submit gift transfer form")
    public GiftCertificateTransferPage submitGiftTransferForm() {
        $x(transferFormGiftButtonXpath).click();
        return this;
    }

    public boolean isToGiftButtonDisabled() {
        return hasAttribute(transferFormGiftButtonXpath, "disabled");
    }

    public boolean isCertificateFieldBorderColorCorrect() {
        return isColorCorrect(certificateFieldXpath, "border-color", Color.RED.getRgb());
    }

    public String getCertificateErrorMessage() {
        var certificateFieldErrorMessageXpath = "//p[@class = 'validation-message ng-star-inserted']/span";
        waitTillVisible(certificateFieldErrorMessageXpath);
        return getText(certificateFieldErrorMessageXpath);
    }
}