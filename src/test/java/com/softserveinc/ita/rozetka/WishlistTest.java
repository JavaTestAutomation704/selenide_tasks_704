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
    private Header header;

    @BeforeMethod
    @AfterMethod
    private void clearWishlist() {
        header = homePage.getHeader();
        if (header.isWishlistCounterVisible()) {
            var wishlistPage = header.openWishlistPage();
            for (int i = wishlistPage.getWishlistsQuantity(); i > 0; i--) {
                wishlistPage
                        .getWishlist(i)
                        .delete();
            }
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
                    var product = subcategoryPage.getProduct(i);
                    product.addToWishlist();
                    productTitles.add(product.getTitle());
                    subcategoriesCounter.getAndIncrement();
                }

                assertThat(header.getWishlistProductQuantity())
                        .as("Incorrect number of items in wishlist")
                        .isEqualTo(subcategoriesCounter.intValue());
            }
        });

        var wishlistPage = header.openWishlistPage();

        var defaultWishlist = wishlistPage.getDefaultWishlist();
        var wishlistItemsQuantity = defaultWishlist.getWishlistItemsQuantity();
        assertThat(header.getWishlistProductQuantity())
                .as("Number of items shown on wishlist counter should be equal to wishlist items quantity")
                .isEqualTo(wishlistItemsQuantity);

        var softly = new SoftAssertions();
        for (int i = 1; i <= wishlistItemsQuantity; i++) {
            softly.assertThat(productTitles)
                    .as("Item title from wishlist should be in product titles list")
                    .contains(defaultWishlist.getItem(i).getTitle());
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

                assertThat(header.getWishlistProductQuantity())
                        .as("Incorrect number of items in wishlist")
                        .isEqualTo(subcategoriesCounter.intValue());
            }
        });

        var wishlistPage = header.openWishlistPage();

        var defaultWishlist = wishlistPage.getDefaultWishlist();
        var wishlistItemsQuantityBeforeRemoval = defaultWishlist.getWishlistItemsQuantity();
        assertThat(header.getWishlistProductQuantity())
                .as("Number of items shown on wishlist counter should be equal to wishlist items quantity")
                .isEqualTo(wishlistItemsQuantityBeforeRemoval);

        var selectedWishlistItemTitles = new ArrayList<String>();
        for (int i = 1; i <= wishlistItemsQuantityBeforeRemoval; i += 3) {
            var wishlistItem = defaultWishlist.getItem(i);
            wishlistItem.select();
            selectedWishlistItemTitles.add(wishlistItem.getTitle());
        }
        defaultWishlist.removeSelectedItems();

        var wishlistItemsQuantityAfterRemoval = defaultWishlist.getWishlistItemsQuantity();
        assertThat(wishlistItemsQuantityAfterRemoval)
                .as("Incorrect number of wishlist items after removal")
                .isEqualTo(wishlistItemsQuantityBeforeRemoval - selectedWishlistItemTitles.size());

        var softly = new SoftAssertions();
        for (int i = 1; i <= wishlistItemsQuantityAfterRemoval; i++) {
            softly.assertThat(defaultWishlist.getItem(i).getTitle())
                    .as(i + " wishlist item should not be in a list of selected items before removal")
                    .isNotIn(selectedWishlistItemTitles);
        }
        softly.assertAll();
    }

    @Test
    public void verifyAddWishlistFunctionality() {
        header
                .openCatalogModal()
                .openSubcategory(SPORT_AND_HOBBIES, VITAMINS)
                .getProduct(1)
                .addToWishlist();

        var wishlistPage = header.openWishlistPage();
        var expectedWishlistsQuantity = new AtomicInteger(1);

        assertThat(wishlistPage.getWishlistsQuantity())
                .as("Incorrect quantity of wishlists on wishlist page")
                .isEqualTo(expectedWishlistsQuantity.get());

        asList("Vitamins", "Protein", "Nice Jeans", "Cool Sneakers").forEach(name -> {
            wishlistPage
                    .addWishlist()
                    .fillInName(name)
                    .add();
            expectedWishlistsQuantity.getAndIncrement();

            assertThat(wishlistPage.getWishlistsQuantity())
                    .as("Incorrect quantity of wishlists on wishlist page")
                    .isEqualTo(expectedWishlistsQuantity.get());

            assertThat(wishlistPage.getWishlistNames())
                    .as("List of wishlist names should contain wishlist name")
                    .contains(name);
        });
    }

    @Test
    public void verifyRemoveWishlistFunctionality() {
        header
                .openCatalogModal()
                .openSubcategory(SPORT_AND_HOBBIES, PROTEIN)
                .getProduct(1)
                .addToWishlist();

        var wishlistPage = header.openWishlistPage();
        var names = asList("Vitamins", "Protein", "Nice Jeans", "Cool Sneakers", "Phones");

        names.forEach(name -> wishlistPage
                .addWishlist()
                .fillInName(name)
                .setDefault()
                .add());

        int wishlistsQuantity = wishlistPage.getWishlistsQuantity();

        int removalCounter = 0;
        for (int i = names.size() - 1; i >= 0; i -= 2) {
            var name = names.get(i);
            wishlistPage
                    .getWishlist(name)
                    .delete();
            removalCounter++;

            assertThat(wishlistPage.getWishlistsQuantity())
                    .as("Incorrect quantity of wishlists on wishlist page")
                    .isEqualTo(wishlistsQuantity - removalCounter);

            assertThat(wishlistPage.getWishlistNames())
                    .as("List of wishlist names should not contain wishlist name")
                    .doesNotContain(name);
        }
    }

    @Test
    public void verifyRenameWishlistFunctionality() {
        header
                .openCatalogModal()
                .openSubcategory(CLOSE_SHOES_ACCESSORIES, MENS_SNEAKERS)
                .getProduct(1)
                .addToWishlist();

        var wishlistPage = header.openWishlistPage();
        var names = asList("Protein", "Top Jeans", "Fancy Sneakers", "Latest Phones", "Vitamins");

        names.forEach(name -> wishlistPage
                .addWishlist()
                .fillInName(name)
                .add());

        for (int i = names.size() - 2; i >= 0; i -= 2) {
            var name = names.get(i);
            var newName = "New " + name;
            wishlistPage
                    .getWishlist(name)
                    .rename()
                    .fillInName(newName)
                    .save();

            var wishlistNames = wishlistPage.getWishlistNames();

            assertThat(wishlistNames)
                    .as("List of wishlist names should not contain wishlist name")
                    .doesNotContain(name);

            assertThat(wishlistNames)
                    .as("List of wishlist names should contain wishlist name")
                    .contains(newName);
        }
    }
}