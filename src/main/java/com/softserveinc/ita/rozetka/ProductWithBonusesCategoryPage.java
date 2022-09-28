package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.data.Category;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

public class ProductWithBonusesCategoryPage {

    @Step("Product with bonuses category page: open product with bonuses page {category}")
    public ProductWithBonusesPage openProductWithBonusesPage(Category category) {
        $x(format("(//div[@class = 'rz-categories__item']//a[contains(@href, '%s')])[2]",
                category.getCategoryXpath())).click();
        switchTo().window(1);
        return new ProductWithBonusesPage();
    }

    public boolean doesTitleContainBonus() {
        var bannerText = $x("//div[@class='rz-header-title']")
                .shouldBe(Condition.visible)
                .getText();
        return bannerText.contains("бонус");
    }
}
