package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.CaseStatus.BROKEN_OR_DEFORMED;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.CaseStatus.NO_USE_SIGNS;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ChargingStatus.LESS_THAN_HUNDRED_RECHARGE_CYCLES;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ChargingStatus.MORE_THEN_SEVEN_HUNDRED_RECHARGE_CYCLES;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.EquipmentStatus.COMPLETE_SET_IS_MISSING;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.EquipmentStatus.FULL_EQUIPMENT;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ExchangeProductStatus.OTHER_GOODS;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.FunctionalityStatus.ALL_FUNCTION_WORKS;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.FunctionalityStatus.FUNCTION_NOT_WORKS;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ImageStatus.BROKEN_COLOR_RENDERING;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ImageStatus.PERFECT_IMAGE;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ScreenStatus.DAMAGED;
import static com.softserveinc.ita.rozetka.data.goods_exchange_page.ScreenStatus.NO_USE;
import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeGoodsTest extends BaseTestRunner {

    @Test
    public void verifyGoodsExchangeFunctionality() {
        var productForm = homePage
                .openGoodsExchangePage()
                .getProductForm();

        //TODO: When opening the exchange goods page, the trading calculator form may be missing,
        // for this you need to refresh the page
        refresh();

        assertThat(productForm.isTradingCalculatorVisible())
                .as("Trading calculator should be visible")
                .isTrue();

        var brandName = "APPLE";
        var categoryName = "Mac";
        var modelName = "MacBook 12";
        var characteristicDescription = "2017 / 1.3 GHz Intel Core i5 / 512 GB / 8 GB";
        var color = "Gold";
        productForm
                .selectBrand(brandName)
                .selectCategory(categoryName)
                .selectModel(modelName)
                .selectCharacteristic(characteristicDescription)
                .selectColor(color);
        var maxPrice = productForm.getMaxPrice();
        var selectedProductName = productForm.getProductName();
        var resultProductForm = productForm
                .switchToNextStep()
                .selectStatus(ALL_FUNCTION_WORKS)
                .switchToNextStep()
                .selectStatus(LESS_THAN_HUNDRED_RECHARGE_CYCLES)
                .switchToNextStep()
                .selectStatus(NO_USE)
                .switchToNextStep()
                .selectStatus(PERFECT_IMAGE)
                .switchToNextStep()
                .selectStatus(NO_USE_SIGNS)
                .switchToNextStep()
                .selectStatus(FULL_EQUIPMENT)
                .switchToNextStep()
                .selectStatus(OTHER_GOODS)
                .switchToNextStep();

        var softly = new SoftAssertions();
        softly.assertThat(resultProductForm.getMaxPrice())
                .as("Max price should be correct")
                .isEqualTo(maxPrice);
        softly.assertThat(resultProductForm.getProductName())
                .as("Product name should be correct")
                .isEqualTo(selectedProductName);

        resultProductForm.startOver();
        productForm
                .selectBrand(brandName)
                .selectCategory(categoryName)
                .selectModel(modelName)
                .selectCharacteristic(characteristicDescription)
                .selectColor(color);

        resultProductForm = productForm
                .switchToNextStep()
                .selectStatus(FUNCTION_NOT_WORKS)
                .switchToNextStep()
                .selectStatus(MORE_THEN_SEVEN_HUNDRED_RECHARGE_CYCLES)
                .switchToNextStep()
                .selectStatus(DAMAGED)
                .switchToNextStep()
                .selectStatus(BROKEN_COLOR_RENDERING)
                .switchToNextStep()
                .selectStatus(BROKEN_OR_DEFORMED)
                .switchToNextStep()
                .selectStatus(COMPLETE_SET_IS_MISSING)
                .switchToNextStep()
                .selectStatus(OTHER_GOODS)
                .switchToNextStep();

        softly.assertThat(resultProductForm.getMaxPrice())
                .as("Max price should be correct")
                .isLessThan(maxPrice);
        softly.assertThat(resultProductForm.getProductName())
                .as("Product name should be correct")
                .isEqualTo(selectedProductName);
        softly.assertAll();
    }
}