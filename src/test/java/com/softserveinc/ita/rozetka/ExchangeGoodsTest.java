package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeGoodsTest extends TestRunner {

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
                .openNextStep()
                .selectStatusAllFunctionWorks()
                .openNextStep()
                .selectStatusLessThanHundredRechargeCycles()
                .openNextStep()
                .selectStatusNoUseSigns()
                .openNextStep()
                .selectStatusPerfectImage()
                .openNextStep()
                .selectStatusNoUseSigns()
                .openNextStep()
                .selectStatusFullEquipment()
                .openNextStep();

        var softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultProductForm.getMaxPrice())
                .as("Max price should be correct")
                .isEqualTo(maxPrice);
        softAssertions.assertThat(resultProductForm.getProductName())
                .as("Product name should be correct")
                .isEqualTo(selectedProductName);

        resultProductForm.startOver();
        productForm.selectBrand(brandName)
                .selectCategory(categoryName)
                .selectModel(modelName)
                .selectCharacteristic(characteristicDescription)
                .selectColor(color);

        resultProductForm = productForm
                .openNextStep()
                .selectStatusFunctionNotWorks()
                .openNextStep()
                .selectStatusMoreThanSevenHundredRechargeCycles()
                .openNextStep()
                .selectStatusDamaged()
                .openNextStep()
                .selectStatusBrokenColorRendering()
                .openNextStep()
                .selectStatusBrokenOrDeformed()
                .openNextStep()
                .selectStatusCompleteSetIsMissing()
                .openNextStep();

        softAssertions.assertThat(resultProductForm.getMaxPrice())
                .as("Max price should be correct")
                .isLessThan(maxPrice);
        softAssertions.assertThat(resultProductForm.getProductName())
                .as("Product name should be correct")
                .isEqualTo(selectedProductName);
        softAssertions.assertAll();
    }
}
