package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class PromoCodeSection {

    private final String promoFormXpath = "//rz-checkout-orders-coupons//div[contains(@class, 'checkout-promo__form')]";

    public boolean isVisible() {
        return WebElementUtil.isVisible("//rz-checkout-orders-coupons/div[contains(@class, 'checkout-promo')]");
    }

    @Step("Promo code section: add {promoCode}")
    public PromoCodeSection add(String promoCode) {
        $x("//div[contains(@class, 'checkout-promo')]/button").click();
        waitTillVisible(promoFormXpath);
        $x(promoFormXpath + "//input").val(promoCode);
        $x(promoFormXpath + "//button").click();
        waitTillPreloaderInvisible();
        return this;
    }

    public boolean isPromoCodeFieldBorderColorCorrect(String color) {
        return isBorderColorCorrect("//div[@class = 'checkout-promo__code-wrap']/p", color);
    }

    public String getPromoCodeErrorMessage() {
        return getText("//div[contains(@class, 'form__hint_type_warning')]");
    }
}

