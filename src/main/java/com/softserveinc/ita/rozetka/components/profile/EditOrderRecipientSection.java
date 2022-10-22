package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.ProfilePage;
import com.softserveinc.ita.rozetka.data.EditOrderRecipientField;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class EditOrderRecipientSection {

    private final String errorMessageXpath = "//p[contains(@class, 'validation-message')]";
    private final String buttonAddRecipientXpathTemplate = "//rz-cabinet-order-recipient//button[contains(@class,'navy')%s]";

    @Step("Edit order recipient section: start adding recipient")
    public EditOrderRecipientSection startAddingRecipient() {
        $x("//button[contains(@class,'recipient__add-button')]").click();
        return this;
    }

    @Step("Edit order recipient section: fill in {editOrderRecipientField} {text}")
    public EditOrderRecipientSection fillIn(EditOrderRecipientField editOrderRecipientField, String text) {
        var textInput = $x(format("//input[@id='%s']", editOrderRecipientField.getFieldId()));
        textInput.clear();
        textInput.sendKeys(text);
        textInput.pressTab();
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessageXpath);
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessageXpath);
    }

    public List<String> getErrorMessagesList() {
        return getElementsText(errorMessageXpath);
    }

    public boolean isButtonAddRecipientEnabled() {
        return !isVisible(format(buttonAddRecipientXpathTemplate, " and @disabled"));
    }

    @Step("Edit order recipient section: add recipient")
    public ProfilePage addRecipient() {
        $x(format(buttonAddRecipientXpathTemplate, "")).click();
        return new ProfilePage();
    }

    @Step("Edit order recipient section: save changes")
    public ProfilePage saveChanges() {
        $x("(//rz-personal-information-section-header//button[contains(@type,'submit')])[1]").click();
        return new ProfilePage();
    }

    @Step("Edit order recipient section: remove recipient")
    public EditOrderRecipientSection removeRecipient() {
        $x("//rz-cabinet-order-recipient//button[contains(@class,'white')]").click();
        return new EditOrderRecipientSection();
    }
}