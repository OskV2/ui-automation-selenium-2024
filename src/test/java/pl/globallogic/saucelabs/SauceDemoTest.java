package pl.globallogic.saucelabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.globallogic.pages.CartPage;
import pl.globallogic.pages.CatalogPage;
import pl.globallogic.pages.DetailsPage;
import pl.globallogic.pages.LandingPage;

import java.util.ArrayList;

public class SauceDemoTest extends SauceDemoBaseTest {

    //  shouldSuccessfullyLoginStandardUSer
    @Test
    public void shouldSuccessfullyLoginStandardUSer() {
        String standardUsername = "standard_user";
        String password = "secret_sauce";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(standardUsername, password);

        Assert.assertTrue(landingPage.isUserLoggedInSuccessfully());
    }

    //  shouldAddSauceLabsBackpackIntoCartFromCatalog
    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromCatalogView() {
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        int expectedCartItemsCount = 1;

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);

        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);
    }

    //  shouldAddSauceLabsBackpackIntoCartFromDetailsView
    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromDetailsView() {
        String username = "standard_user";
        String password = "secret_sauce";
        String itemId = "4";
        int expectedCartItemsCount = 1;

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.goToItemDetails(itemId);

        DetailsPage detailsPage = new DetailsPage(driver);
        detailsPage.addToCart();

        Assert.assertEquals(detailsPage.cartItemsCount(), expectedCartItemsCount);
    }

    //  shouldHavePriceInformationForItemInCart
    @Test
    public void shouldHavePriceInformationForItemInCart() {
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        catalogPage.goToCartPage();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isPriceDisplayedForFirstElement());
    }

    //  shouldRemoveSauceLabsBackpackFromCart (Verify: CartIcon or ButtonChange)
    @Test
    public void shouldRemoveSauceLabsBackpackFromCart() {
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        catalogPage.goToCartPage();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeFirstItem();

        Assert.assertTrue(cartPage.isItemsCountBadgeDisplayed());
    }

    //  shouldRemoveSauceLabsBackpackFromCatalogPage
    @Test
    public void shouldRemoveSauceLabsBackpackFromCatalogPage() {
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        String expectedText = "Add to cart";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        catalogPage.addToCart(itemName);  //  click the same button second time, no point of creating second function that does the same thing

        Assert.assertEquals(catalogPage.textDisplayedOnButton("Sauce Labs Backpack"), expectedText);
    }

    //  shouldAddListOfItemsToTheCart
    @Test
    public void shouldAddListOfItemsToTheCart() {
        String username = "standard_user";
        String password = "secret_sauce";
        ArrayList<String> items = new ArrayList<>();

        items.add("Sauce Labs Backpack");
        items.add("Sauce Labs Bike Light");
        items.add("Sauce Labs Bolt T-Shirt");

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        CatalogPage catalogPage = new CatalogPage(driver);
        for (String item : items) {
            catalogPage.addToCart(item);
        }
        catalogPage.goToCartPage();
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(items, cartPage.listOfNamesOfProductsInCart());
    }

    //  shouldPersistCheckoutDetails
    @Test
    public void shouldPersistCheckoutDetails() {
        String username = "standard_user";
        String password = "secret_sauce";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
    }

    //  -------------------------------------------------
    //  TESTS THAT ARE CHECKING IF VALIDATION AND ERRORS ARE WORKING PROPERLY
    //  -------------------------------------------------

    //  shouldShowAnErrorForLockedOutUser
    @Test
    public void shouldShowAnErrorForLockedOutUser() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        String expectedError = "Epic sadface: Sorry, this user has been locked out.";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        Assert.assertEquals(expectedError, landingPage.getErrorMessage());
    }

    //  shouldShowErrorForInvalidUsername
    @Test
    public void shouldShowErrorForInvalidUsername() {
        String username = "user";
        String password = "secret_sauce";
        String expectedError = "Epic sadface: Username and password do not match any user in this service";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        Assert.assertEquals(expectedError, landingPage.getErrorMessage());
    }

    //  shouldShowErrorForInvalidPassword
    @Test
    public void shouldShowErrorForInvalidPassword() {
        String username = "standard_user";
        String password = "password";
        String expectedError = "Epic sadface: Username and password do not match any user in this service";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        Assert.assertEquals(expectedError, landingPage.getErrorMessage());
    }

    //  shouldShowErrorForEmptyCredentials
    @Test
    public void shouldShowErrorForEmptyCredentials() {
        String username = "";
        String password = "";
        String expectedError = "Epic sadface: Username is required";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);

        Assert.assertEquals(expectedError, landingPage.getErrorMessage());
    }
}
