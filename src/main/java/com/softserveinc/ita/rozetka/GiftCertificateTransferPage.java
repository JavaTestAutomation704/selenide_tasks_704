package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class GiftCertificateTransferPage extends BasePage {

    private final String certificateFieldXpath = "//input[@id = 'certificateInput']";
    private final String transferFormSubmitButtonXpath = "//button[@type = 'submit']";

    public boolean isOpened() {
        return isVisible("//div[@class = 'certificate__container']");
    }

    @Step("Gift certificate transfer page: fill in transfer form")
    public GiftCertificateTransferPage fillInTransferForm(String ownerPhone, String futureOwnerPhone, String certificate) {
        $x("//input[@id = 'ownerPhone']").val(ownerPhone);
        $x("//input[@id = 'futureOwnerPhone']").val(futureOwnerPhone);
        $x(certificateFieldXpath).val(certificate);
        return this;
    }

    @Step("Gift certificate transfer page: clear certificate field")
    public GiftCertificateTransferPage clearCertificateField() {
        $x(certificateFieldXpath).val("a").sendKeys(Keys.BACK_SPACE);
        return this;
    }

    @Step("Gift certificate transfer page: submit gift transfer form")
    public GiftCertificateTransferPage submitGiftTransferForm() {
        $x(transferFormSubmitButtonXpath).click();
        return this;
    }

    public boolean isGiftButtonDisabled() {
        return WebElementUtil.hasAttribute(transferFormSubmitButtonXpath, "disabled");
    }

    public boolean isCertificateFieldBorderColorCorrect() {
        return WebElementUtil.isBorderColorCorrect(certificateFieldXpath, Color.RED.getRgb());
    }

    public String getCertificateErrorMessage() {
        var certificateFieldErrorMessageXpath = "//p[@class = 'validation-message ng-star-inserted']/span";
        waitTillVisible(certificateFieldErrorMessageXpath);
        return getText(certificateFieldErrorMessageXpath);
    }
}