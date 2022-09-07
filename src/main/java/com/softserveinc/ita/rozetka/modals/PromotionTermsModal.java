package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.PromotionPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class PromotionTermsModal {

    public boolean isOpen() {
        return isVisible("//rz-promotion-modal");
    }

    public String getTitle() {
        return getText("//h3[contains(@class, 'promotion')]");
    }

    public List<String> getPromotionPeriod() {
        return List.of(getText("(//div[contains(@class,'modal__body')]//p)[last()-1]")
                .replaceAll("[^*]+:|.\\.", "")
                .trim()
                .split(" — "));
    }

    @Step("Promotion terms modal: open promotion page")
    public PromotionPage openPromotionPage() {
        $x("//a[contains(@class, 'promotion-link')]").click();
        return new PromotionPage();
    }
}