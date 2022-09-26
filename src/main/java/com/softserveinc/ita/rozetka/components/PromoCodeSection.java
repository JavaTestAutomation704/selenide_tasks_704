package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class PromoCodeSection {

    public boolean isVisible() {
        return WebElementUtil.isVisible("//rz-checkout-orders-coupons/div[contains(@class, 'checkout-promo')]");
    }

    @Step("Promo code section: add {promoCode}")
    public PromoCodeSection add(String promoCode) {
        var promoFormXpath = "//rz-checkout-orders-coupons//div[contains(@class, 'checkout-promo__form')]";
        $x("//div[contains(@class, 'checkout-promo')]/button").click();
        waitTillVisible(promoFormXpath);
        $x(promoFormXpath + "//input").val(promoCode);
        $x(promoFormXpath + "//button").click();
        waitTillPreloaderInvisible();
        return this;
    }

    public boolean isPromoCodeFieldBorderColorCorrect(String color) {
        return isColorCorrect("//div[@class = 'checkout-promo__code-wrap']/p", "border-color", color);
    }

    public String getPromoCodeErrorMessage() {
        return getText("//div[contains(@class, 'form__hint_type_warning')]");
    }
}

