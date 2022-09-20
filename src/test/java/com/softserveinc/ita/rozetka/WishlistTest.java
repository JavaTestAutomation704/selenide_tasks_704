package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.softserveinc.ita.rozetka.data.Category.*;
import static com.softserveinc.ita.rozetka.data.subcategory.CloseShoesAccessoriesSubcategory.*;
import static com.softserveinc.ita.rozetka.data.subcategory.KidsGoodsSubcategory.*;
import static com.softserveinc.ita.rozetka.data.subcategory.SportAndHobbiesSubcategory.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class WishlistTest extends LogInViaFacebookTestRunner {
    Header header;

    @BeforeMethod
    @AfterMethod
    private void clearWishlist() {
        header = homePage.getHeader();
        if (header.isWishlistCounterVisible()) {
            header
                    .openWishlistPage()
                    .selectAllItems()
                    .removeSelectedItems();
        }
    }

    @Test
    public void verifyAddProductsToWishlistFunctionality() {
        var categoriesWithSubcategories = new HashMap<Category, List<ISubcategory>>();
        categoriesWithSubcategories.put(SPORT_AND_HOBBIES, asList(VITAMINS));
        categoriesWithSubcategories.put(KIDS_GOODS, asList(KIDS_CONSTRUCTION_SETS, TABLE_GAMES));

        var productTitles = new ArrayList<String>();

        var subcategoriesCounter = new AtomicInteger();
        categoriesWithSubcategories.forEach((category, subcategories) -> {
            for (var subcategory : subcategories) {
                var subcategoryPage = header
                        .openCatalogModal()
                        .openSubcategory(category, subcategory);

                int minimumProductsQuantity = 10;
                assertThat(subcategoryPage.getProductsQuantity())
                        .as("Insufficient products quantity on subcategory page")
                        .isGreaterThanOrEqualTo(minimumProductsQuantity);

                for (int i = 1; i <= minimumProductsQuantity; i += 3) {
                    var product = subcategoryPage
                            .getProduct(i);
                    product.addToWishlist();
                    productTitles.add(product.getTitle());
                    subcategoriesCounter.getAndIncrement();
                }

                assertThat(header.getWishlistCounter())
                        .as("Incorrect number of items in wishlist")
                        .isEqualTo(subcategoriesCounter.intValue());
            }
        });

        var wishlistPage = header.openWishlistPage();

        var wishlistItemsQuantity = wishlistPage.getWishlistItemsQuantity();
        assertThat(header.getWishlistCounter())
                .as("Number of items shown on wishlist counter should be equal to wishlist items quantity")
                .isEqualTo(wishlistItemsQuantity);

        var softly = new SoftAssertions();
        for (int i = 1; i <= wishlistItemsQuantity; i++) {
            softly.assertThat(productTitles)
                    .as("Item title from wishlist should be in product titles list")
                    .contains(wishlistPage.getItem(i).getItemTitle());
        }
        softly.assertAll();
    }

    @Test
    public void verifyWishlistRemoveItemsFunctionality() {
        var categoriesWithSubcategories = new HashMap<Category, List<ISubcategory>>();
        categoriesWithSubcategories.put(CLOSE_SHOES_ACCESSORIES, asList(MENS_JEANS, MENS_SNEAKERS));
        categoriesWithSubcategories.put(SPORT_AND_HOBBIES, asList(VITAMINS, PROTEIN));

        var subcategoriesCounter = new AtomicInteger();
        categoriesWithSubcategories.forEach((category, subcategories) -> {
            for (var subcategory : subcategories) {
                var subcategoryPage = header
                        .openCatalogModal()
                        .openSubcategory(category, subcategory);

                int minimumProductsQuantity = 19;
                assertThat(subcategoryPage.getProductsQuantity())
                        .as("Insufficient products quantity on subcategory page")
                        .isGreaterThanOrEqualTo(minimumProductsQuantity);

                for (int i = 1; i <= minimumProductsQuantity; i += 6) {
                    var product = subcategoryPage
                            .getProduct(i);
                    product.addToWishlist();
                    subcategoriesCounter.getAndIncrement();
                }

                assertThat(header.getWishlistCounter())
                        .as("Incorrect number of items in wishlist")
                        .isEqualTo(subcategoriesCounter.intValue());
            }
        });

        var wishlistPage = header.openWishlistPage();

        var wishlistItemsQuantity = wishlistPage.getWishlistItemsQuantity();
        assertThat(header.getWishlistCounter())
                .as("Number of items shown on wishlist counter should be equal to wishlist items quantity")
                .isEqualTo(wishlistItemsQuantity);

        var wishlistSelectedItems = new ArrayList<String>();
        var wishlistNotSelectedItems = new ArrayList<String>();
        for (int i = 1; i <= wishlistItemsQuantity; i++) {
            if (i % 3 == 0) {
                wishlistPage.getItem(i).selectItem();
                wishlistSelectedItems.add(wishlistPage.getItem(i).getItemTitle());
            } else {
                wishlistNotSelectedItems.add(wishlistPage.getItem(i).getItemTitle());
            }
        }

        wishlistPage.removeSelectedItems();
        wishlistItemsQuantity = wishlistPage.getWishlistItemsQuantity();
        assertThat(wishlistItemsQuantity)
                .as("Incorrect number of wishlist items after removal")
                .isEqualTo(wishlistNotSelectedItems.size());

        var softly = new SoftAssertions();
        for (int i = 1; i <= wishlistItemsQuantity; i++) {
            softly.assertThat(wishlistPage.getItem(i).getItemTitle())
                    .as("Item should be in a list of not selected items")
                    .isIn(wishlistNotSelectedItems);

            softly.assertThat(wishlistPage.getItem(i).getItemTitle())
                    .as("Item should not be in a list of selected items before removal")
                    .isNotIn(wishlistSelectedItems);
        }
        softly.assertAll();
    }
}