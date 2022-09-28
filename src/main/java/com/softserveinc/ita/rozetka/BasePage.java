package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.softserveinc.ita.rozetka.components.Header;
import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillPreloaderInvisible;

public abstract class BasePage {

    public String getUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public Header getHeader() {
        return new Header();
    }

    public boolean isShoppingCartModalVisible() {
        return isVisible("//div[contains(@class, 'modal__holder')]");
    }

    @Step("Base page: back to the previous page")
    public Header back() {
        Selenide.back();
        waitTillPreloaderInvisible();
        return new Header();
    }
}