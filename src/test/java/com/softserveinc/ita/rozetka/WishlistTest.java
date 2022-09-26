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
import static java.lang.String.format;
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

        asList("Vitamins", "Protein", "Nice Jeans", "Cool Sneakers", "Phones").forEach(name -> wishlistPage
                .addWishlist()
                .fillInName(name)
                .setDefault()
                .add());

        int wishlistsQuantity = wishlistPage.getWishlistsQuantity();

        var removalCounter = new AtomicInteger();
        asList("Cool Sneakers", "Vitamins", "Nice Jeans").forEach(name -> {
            wishlistPage
                    .getWishlist(name)
                    .delete();
            removalCounter.getAndIncrement();

            assertThat(wishlistPage.getWishlistsQuantity())
                    .as("Incorrect quantity of wishlists on wishlist page")
                    .isEqualTo(wishlistsQuantity - removalCounter.get());

            assertThat(wishlistPage.getWishlistNames())
                    .as("List of wishlist names should not contain wishlist name")
                    .doesNotContain(name);
        });
    }

    @Test
    public void verifyRenameWishlistFunctionality() {
        header
                .openCatalogModal()
                .openSubcategory(CLOSE_SHOES_ACCESSORIES, MENS_SNEAKERS)
                .getProduct(1)
                .addToWishlist();

        var wishlistPage = header.openWishlistPage();

        asList("Protein", "Top Jeans", "Fancy Sneakers", "Latest Phones", "Vitamins").forEach(name -> wishlistPage
                .addWishlist()
                .fillInName(name)
                .add());

        asList("Vitamins", "Protein", "Fancy Sneakers").forEach(name -> {
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
        });
    }

    @Test
    public void verifyCreateDefaultWishlistFunctionality() {
        var subcategoryPage = header
                .openCatalogModal()
                .openSubcategory(CLOSE_SHOES_ACCESSORIES, MENS_JEANS);

        subcategoryPage
                .getProduct(1)
                .addToWishlist();

        var wishlistPage = header.openWishlistPage();
        var defaultWishlist = wishlistPage.getDefaultWishlist();
        assertThat(defaultWishlist.getWishlistItemsQuantity())
                .as("Incorrect quantity of items in default wishlist")
                .isEqualTo(1);

        var oldDefaultWishlistName = "Jeans";
        defaultWishlist
                .rename()
                .fillInName(oldDefaultWishlistName)
                .save();

        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(oldDefaultWishlistName);

        var newDefaultWishlistName = "Sneakers";
        wishlistPage
                .addWishlist()
                .fillInName(newDefaultWishlistName)
                .setDefault()
                .add();

        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(newDefaultWishlistName);

        header
                .openCatalogModal()
                .openSubcategory(CLOSE_SHOES_ACCESSORIES, MENS_SNEAKERS);

        for (int i = 1; i <= 6; i++) {
            subcategoryPage
                    .getProduct(i)
                    .addToWishlist();
        }

        header.openWishlistPage();
        assertThat(defaultWishlist.getWishlistItemsQuantity())
                .as("Incorrect quantity of items in default wishlist")
                .isEqualTo(6);
        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(newDefaultWishlistName);
        assertThat(wishlistPage
                        .getWishlist(oldDefaultWishlistName)
                        .getWishlistItemsQuantity())
                .as(format("Incorrect quantity of items in '%s' wishlist", oldDefaultWishlistName))
                .isEqualTo(1);
    }

    @Test
    public void verifyChangeDefaultWishlistFunctionality() {
        var subcategoryPage = header
                .openCatalogModal()
                .openSubcategory(SPORT_AND_HOBBIES, PROTEIN);

        subcategoryPage
                .getProduct(2)
                .addToWishlist();
        subcategoryPage
                .getProduct(4)
                .addToWishlist();

        var wishlistPage = header.openWishlistPage();
        var defaultWishlist = wishlistPage.getDefaultWishlist();
        assertThat(defaultWishlist.getWishlistItemsQuantity())
                .as("Incorrect quantity of items in default wishlist")
                .isEqualTo(2);

        var oldDefaultWishlistName = "Protein";
        defaultWishlist
                .rename()
                .fillInName(oldDefaultWishlistName)
                .save();

        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(oldDefaultWishlistName);

        var wishlistName = "Vitamins";
        wishlistPage
                .addWishlist()
                .fillInName(wishlistName)
                .add();

        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(oldDefaultWishlistName);

        wishlistPage
                .getWishlist(wishlistName)
                .makeDefault();

        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(wishlistName);

        header
                .openCatalogModal()
                .openSubcategory(SPORT_AND_HOBBIES, VITAMINS);

        for (int i = 1; i <= 3; i++) {
            subcategoryPage
                    .getProduct(i)
                    .addToWishlist();
        }

        header.openWishlistPage();
        assertThat(defaultWishlist.getWishlistItemsQuantity())
                .as("Incorrect quantity of items in default wishlist")
                .isEqualTo(3);

        assertThat(defaultWishlist.getWishlistName())
                .as("Incorrect default wishlist name")
                .isEqualTo(wishlistName);


        assertThat(wishlistPage
                        .getWishlist(oldDefaultWishlistName)
                        .getWishlistItemsQuantity())
                .as(format("Incorrect quantity of items in '%s' wishlist", oldDefaultWishlistName))
                .isEqualTo(2);
    }

    @Test
    public void verifyMoveItemsToDifferentWishlistFunctionality() {
        var subcategoryPage = header
                .openCatalogModal()
                .openSubcategory(KIDS_GOODS, TABLE_GAMES);

        for (int i = 1; i <= 6; i++) {
            subcategoryPage
                    .getProduct(i)
                    .addToWishlist();
        }

        var wishlistPage = header.openWishlistPage();

        var firstWishlistName = "Table games";
        wishlistPage
                .getDefaultWishlist()
                .rename()
                .fillInName(firstWishlistName)
                .save();

        var firstWishlist = wishlistPage.getWishlist(firstWishlistName);
        assertThat(firstWishlist.getWishlistItemsQuantity())
                .as(format("Incorrect quantity of items in '%s' wishlist", firstWishlistName))
                .isEqualTo(6);

        var secondWishlistName = "Fun table games";
        wishlistPage
                .addWishlist()
                .fillInName(secondWishlistName)
                .add();

        var secondWishlist = wishlistPage.getWishlist(secondWishlistName);
        assertThat(secondWishlist.getWishlistItemsQuantity())
                .as(format("Incorrect quantity of items in '%s' wishlist", firstWishlistName))
                .isEqualTo(0);

        for (int i = 1; i <= 4; i++) {
            firstWishlist
                    .getItem(i)
                    .select();
        }

        firstWishlist.moveSelectedItems(secondWishlistName);

        assertThat(firstWishlist.getWishlistItemsQuantity())
                .as(format("Incorrect quantity of items in '%s' wishlist", firstWishlistName))
                .isEqualTo(2);

        assertThat(secondWishlist.getWishlistItemsQuantity())
                .as(format("Incorrect quantity of items in '%s' wishlist", secondWishlistName))
                .isEqualTo(4);
    }
}