package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.data.subcategory.BusinessGoodsSubcategory;
import com.softserveinc.ita.rozetka.data.subcategory.OfficeSchoolBooksSubcategory;
import com.softserveinc.ita.rozetka.data.subcategory.SportAndHobbiesSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.GAMERS_GOODS;
import static com.softserveinc.ita.rozetka.data.Category.SMARTPHONES_TV_AND_ELECTRONICS;
import static com.softserveinc.ita.rozetka.data.City.DNIPRO;
import static com.softserveinc.ita.rozetka.data.DeliveryService.*;
import static com.softserveinc.ita.rozetka.data.DeliveryType.*;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;
import static com.softserveinc.ita.rozetka.data.ProductFilter.ROZETKA_SELLER;
import static com.softserveinc.ita.rozetka.data.subcategory.GamersGoodsSubcategory.MONITORS;
import static com.softserveinc.ita.rozetka.data.subcategory.SmartphonesTvAndElectronicsSubcategory.MOBILE_PHONES;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends TestRunner {
    private Header header;

    @BeforeMethod
    public void clearShoppingCart() {
        header = homePage.getHeader();

        if (header.isShoppingCartCounterVisible()) {
            var shoppingCartModal = header
                    .openShoppingCartModal()
                    .clear();
            if (shoppingCartModal.isCloseButtonVisible()) {
                shoppingCartModal.close();
            } else {
                homePage.back();
            }
        }
    }

    @Test
    public void verifyCheckoutFunctionalityWorks() {
        var subcategoryPage = homePage
                .openCategoryPage(GAMERS_GOODS)
                .openSubcategoryPage(MONITORS);
        var filter = subcategoryPage.getFilter();
        filter.filter(AVAILABLE);

        assertThat(filter.isSelected(AVAILABLE))
                .as("Filter should be selected")
                .isTrue();

        int productsQuantity = 60;

        assertThat(subcategoryPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 10; i <= productsQuantity; i += 10) {
            subcategoryPage
                    .getProduct(i)
                    .addToShoppingCart();
        }
        var shoppingCart = subcategoryPage
                .getHeader()
                .openShoppingCartModal();
        var shoppingCartTotalSum = shoppingCart.getTotalSum();
        var checkoutPage = shoppingCart.startCheckout();
        var checkoutPageTotalSum = checkoutPage.getTotalSum();

        assertThat(checkoutPageTotalSum)
                .as("Shopping cart order sum should be equal to checkout order sum")
                .isEqualTo(shoppingCartTotalSum);
        assertThat(checkoutPage.isHeaderVisible())
                .as("Header should be visible")
                .isTrue();
        assertThat(checkoutPage.isOrderModalVisible())
                .as("Order modal should be visible")
                .isTrue();
        assertThat(checkoutPage.isContactsModalVisible())
                .as("Contacts modal should be visible")
                .isTrue();
        assertThat(checkoutPage.isTotalModalVisible())
                .as("Total modal should be visible")
                .isTrue();
    }

    @Test
    public void verifyDeliveryOrderInformationIsCopiedToAnotherOrder() {
        var filter = homePage
                .openCategoryPage(Category.OFFICE_SCHOOL_BOOKS)
                .openSubcategoryPage(OfficeSchoolBooksSubcategory.PENS)
                .getFilter();

        var searchResultsPage = filter.filter(List.of(ROZETKA_SELLER, AVAILABLE));

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var firstProduct = searchResultsPage.getProduct(1);

        assertThat(firstProduct.isAvailable())
                .as("First searched product should be available")
                .isTrue();

        firstProduct.addToShoppingCart();

        assertThat(firstProduct.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        header.openHomePageViaLogo();

        homePage
                .openCategoryPage(Category.BUSINESS_GOODS)
                .openSubcategoryPage(BusinessGoodsSubcategory.CASH_BOXES);

        filter.filter(List.of(ROZETKA_SELLER, AVAILABLE));

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var secondProduct = searchResultsPage.getProduct(1);

        assertThat(secondProduct.isAvailable())
                .as("Second searched product should be available")
                .isTrue();

        secondProduct.addToShoppingCart();

        assertThat(secondProduct.isInShoppingCart())
                .as("Second product should be added to shopping cart")
                .isTrue();

        var shoppingCartModal = header.openShoppingCartModal();

        assertThat(shoppingCartModal.isOpened())
                .as("Shopping cart modal should be opened")
                .isTrue();

        var checkoutPage = shoppingCartModal.startCheckout();

        assertThat(checkoutPage.isOrderModalVisible())
                .as("Order section should be opened")
                .isTrue();

        var firstOrderSection = checkoutPage.getOrderSection(1);

        var firstCourierDeliverySection = firstOrderSection.selectCourierDelivery();

        assertThat(firstCourierDeliverySection.isOpened())
                .as("Courier delivery section should be opened")
                .isTrue();

        firstCourierDeliverySection.fillInDeliveryDetails("Вул", "12", "12");

        firstOrderSection.copyToOtherOrders();

        var secondCourierDeliverySection = checkoutPage
                .getOrderSection(2)
                .getCourierDeliverySection();

        assertThat(secondCourierDeliverySection.isOpened())
                .as("Courier delivery section in second order should be opened")
                .isTrue();

        assertThat(firstCourierDeliverySection.isDeliveryDataCopiedTo(2))
                .as("Delivery information should be copied to second order")
                .isTrue();
    }

    @Test
    public void verifyOrderTotalSumIsNotChangingAndErrorMessageAppearsWhenInvalidPromoCodeIsApplied() {
        if (!header.isLanguageSelected(UA)) {
            header
                    .openMainSidebar()
                    .changeLanguage(UA);
        }
        var filter = homePage
                .openCategoryPage(Category.SPORT_AND_HOBBIES)
                .openSubcategoryPage(SportAndHobbiesSubcategory.YOGA)
                .getFilter();

        var searchResultsPage = filter.filter(AVAILABLE);

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var firstProduct = searchResultsPage.getProduct(1);

        assertThat(firstProduct.isAvailable())
                .as("First product should be available")
                .isTrue();

        var secondProduct = searchResultsPage.getProduct(2);

        assertThat(secondProduct.isAvailable())
                .as("Second product should be available")
                .isTrue();

        firstProduct.addToShoppingCart();

        assertThat(firstProduct.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        secondProduct.addToShoppingCart();

        assertThat(secondProduct.isInShoppingCart())
                .as("Second product should be added to shopping cart")
                .isTrue();

        var shoppingCartModal = header.openShoppingCartModal();

        assertThat(shoppingCartModal.isOpened())
                .as("Shopping cart modal should be opened")
                .isTrue();

        var checkoutPage = shoppingCartModal.startCheckout();
        var promoCodeSection = checkoutPage.getPromoCodeSection();

        assertThat(promoCodeSection.isVisible())
                .as("Promo code section should be visible")
                .isTrue();

        var totalOrderSection = checkoutPage.getTotalOrderSection();

        assertThat(totalOrderSection.isVisible())
                .as("Total order section should be visible")
                .isTrue();

        long totalSumWithoutPromoCode = totalOrderSection.getTotalSum();
        promoCodeSection.add("NJ");

        var softly = new SoftAssertions();

        softly.assertThat(totalSumWithoutPromoCode)
                .as("Total sum should be the same")
                .isEqualTo(totalOrderSection.getTotalSum());

        var actualPromoCodeErrorMessage = promoCodeSection.getPromoCodeErrorMessage();

        softly.assertThat(actualPromoCodeErrorMessage)
                .as("Error message should appear")
                .isEqualTo("Купон неможливо застосувати");

        var isBorderColorCorrect = promoCodeSection.isPromoCodeFieldBorderColorCorrect(Color.RED.getRgb());

        softly.assertThat(isBorderColorCorrect)
                .as("Promo code field border color should be " + Color.RED)
                .isTrue();

        softly.assertAll();
    }

    @Test
    public void verifyDeliveryWorksOnCheckoutPage() {
        var checkoutPage = homePage
                .openCategoryPage(SMARTPHONES_TV_AND_ELECTRONICS)
                .openSubcategoryPage(MOBILE_PHONES)
                .getFilter()
                .filter(AVAILABLE)
                .getProduct(1)
                .addToShoppingCart()
                .getHeader()
                .openShoppingCartModal()
                .startCheckout();

        assertThat(checkoutPage.isOrderModalVisible())
                .as("Order modal should be visible")
                .isTrue();

        int orderNumber = 1;
        var orderSection = checkoutPage.getOrderSection(orderNumber);
        orderSection.changeCity(DNIPRO.getCityNameUa());
        var meestPickup = orderSection.selectMeestPickUp(UA);

        assertThat(checkoutPage.getSelectedDeliveryTitle(orderNumber))
                .as("Delivery should be correct")
                .contains(MEEST_PICK_UP.getDeliveryNameUa());

        int departmentNumber = 8841;
        meestPickup.selectPickUpPointDepartment(departmentNumber);
        var pickupPointName = meestPickup.getPickUpPointName();
        var pickupPointModal = meestPickup.openPickUpPointModal();

        var softly = new SoftAssertions();
        softly.assertThat(pickupPointModal.getPickUpPointName())
                .as("Pickup point should be correct")
                .contains(MEEST.getNameUa())
                .contains(pickupPointName);

        departmentNumber = 5;
        pickupPointName = pickupPointModal
                .focusOnPickUpPoint(NOVA_POSHTA, departmentNumber)
                .getPickUpPointName();

        pickupPointModal.selectActivePickUpPoint();

        assertThat(checkoutPage.getSelectedDeliveryTitle(orderNumber))
                .as("Delivery should be correct")
                .contains(NOVA_POSHTA_PICK_UP.getDeliveryNameUa());

        var pickupPointNameActual = orderSection
                .selectNovaPoshtaPickUp(UA)
                .getPickUpPointName();

        softly.assertThat(pickupPointName)
                .as("Pickup point should be correct")
                .contains(NOVA_POSHTA.getNameUa())
                .contains(pickupPointNameActual);

        var rozetkaPickup = orderSection.selectRozetkaPickUp(UA);

        assertThat(checkoutPage.getSelectedDeliveryTitle(orderNumber))
                .as("Delivery should be correct")
                .contains(ROZETKA_PICK_UP.getDeliveryNameUa());

        var departmentAddress = "Шолохова вул., 7";
        rozetkaPickup.selectPickUpPointDepartment(departmentAddress);
        pickupPointName = rozetkaPickup.getPickUpPointName();
        rozetkaPickup.openPickUpPointModal();

        softly.assertThat(pickupPointModal.getPickUpPointName())
                .as("Pickup point should be correct")
                .contains(ROZETKA.getNameUa())
                .contains(pickupPointName);
        softly.assertAll();
    }

    @Test
    public void verifyThatContactInformationIsCopiedToRecipientContactInformation() {
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var searchResultsPage = header.search("Lenovo");

        int productNumber = 1;

        assertThat(searchResultsPage.getProductsQuantity())
                .as("The products quantity should be sufficient on the search results page")
                .isGreaterThanOrEqualTo(productNumber);

        searchResultsPage
                .getProduct(productNumber)
                .addToShoppingCart();

        var shoppingCartModal = header.openShoppingCartModal();

        assertThat(shoppingCartModal.isEmpty())
                .as("There should be at least one product in the shopping cart")
                .isFalse();

        var checkoutPage = shoppingCartModal.startCheckout();

        var contactInformationSection = checkoutPage
                .getContactInformationSection()
                .fillInContactInformation("Musk", "Elon", "9875");

        var softly = new SoftAssertions();

        var actualSurnameErrorMessage = contactInformationSection.getSurnameErrorMessage();
        var actualNameErrorMessage = contactInformationSection.getNameErrorMessage();
        var actualPhoneErrorMessage = contactInformationSection.getPhoneNumberErrorMessage();

        softly
                .assertThat(actualSurnameErrorMessage)
                .as("Error message should be displayed when entering invalid data on the contact information section")
                .isEqualTo("Введіть своє прізвище кирилицею");
        softly
                .assertThat(actualNameErrorMessage)
                .as("Error message should be displayed when entering invalid data on the contact information section")
                .isEqualTo("Введіть своє ім'я кирилицею");
        softly
                .assertThat(actualPhoneErrorMessage)
                .as("Error message should be displayed when entering invalid data on the contact information section")
                .isEqualTo("Введіть номер мобільного телефону");

        var redColor = Color.RED.getRgb();

        var isActualSurnameBorderColorCorrect = contactInformationSection.isSurnameBorderColorCorrect(redColor);
        var isActualNameBorderColorCorrect = contactInformationSection.isNameBorderColorCorrect(redColor);
        var isActualPhoneBorderColorCorrect = contactInformationSection.isPhoneNumberBorderColorCorrect(redColor);

        softly
                .assertThat(isActualSurnameBorderColorCorrect)
                .as("Surname border color should be red when entering invalid data on the contact information section")
                .isTrue();

        softly
                .assertThat(isActualNameBorderColorCorrect)
                .as("Name border color should be red when entering invalid data on the contact information section")
                .isTrue();

        softly
                .assertThat(isActualPhoneBorderColorCorrect)
                .as("Phone border color should be red when entering invalid data on the contact information section")
                .isTrue();

        var contactInformation = contactInformationSection.getContactInformation();
        var recipientContactInformation = checkoutPage
                .getOrderSection(1)
                .getRecipientContactInformation();

        softly.assertThat(recipientContactInformation)
                .as("Contact information should be copied to recipient's contact information")
                .usingRecursiveComparison()
                .isEqualTo(contactInformation);

        softly.assertAll();
    }
}