package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.components.*;
import com.softserveinc.ita.rozetka.data.Category;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

@Getter
public class HomePage extends BasePage {

    private final SocialNetworksSection socialNetworksSection = new SocialNetworksSection();
    private final CompanyInformationSection companyInformationSection = new CompanyInformationSection();
    private final PartnerSection partnerSection = new PartnerSection();
    private final ServiceSection serviceSection = new ServiceSection();
    private final LastSeenProductsSection lastSeenProductsSection = new LastSeenProductsSection();
    private final SmallCart smallCart = new SmallCart();

    @Step("Home page: open home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }

    public boolean isSmallCartSectionVisible() {
        return isVisible("//rz-app-small-cart");
    }

    public boolean isMainCategoriesSectionVisible() {
        return isVisible("//rz-app-fat-menu-tablet");
    }

    @Step("Home page: open category page {category}")
    public CategoryPage openCategoryPage(Category category) {
        $x(format("//div[@class = 'fat-wrap']//a[contains(@href, '%s')]", category.getCategoryXpath())).click();
        return new CategoryPage();
    }

    @Step("Home page: open pickup points city page")
    public PickupPointsCityPage openPickupPointsCityPage() {
        $x("//a[contains(@href, 'retail')]").click();
        return new PickupPointsCityPage();
    }

    @Step("Home page: open promotions page")
    public PromotionsPage openPromotionsPage() {
        $x("//a[contains(@class, 'pagination')]").click(ClickOptions.usingJavaScript());
        return new PromotionsPage();
    }

    @Step("Home page: open goods exchange page")
    public GoodsExchangePage openGoodsExchangePage() {
        $x("(//a[contains(@href, 'obmin')])[1]").click(ClickOptions.usingJavaScript());
        waitTillPreloaderInvisible();
        return new GoodsExchangePage();
    }

    @Step("Home page: open service center page")
    public ServiceCenterPage openServiceCenterPage() {
        $x("(//a[contains(@href, 'service-centers')])[1]").click();
        return new ServiceCenterPage();
    }

    public boolean isLogInButtonVisible() {
        return isVisible("//button[contains(@class, 'main-auth__button')]");
    }

    public String getUserName() {
        return getText("//span[@class='main-auth__user-name']");
    }

    public String getUserEmail() {
        return getText("//p[@class='main-auth__user-caption']");
    }

    @Step("Home page: open bonus account page")
    public BonusAccountPage openBonusAccountPage() {
        $x("//a[contains(@href, 'loyalty')]").click(ClickOptions.usingJavaScript());
        waitTillPreloaderInvisible();
        return new BonusAccountPage();
    }
}