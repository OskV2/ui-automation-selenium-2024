package pl.globallogic.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.globallogic.CatalogPage;
import pl.globallogic.LandingPage;

import java.util.Objects;

public class SauceDemoTest extends SauceDemoBaseTest {
    //  shouldShowErrorForInvalidUsername
    //  shouldShowErrorForInvalidPassword
    //  shouldShowErrorForEmptyCredentials


    //  shouldSuccessfullyLoginStandardUSer
    @Test
    public void shouldSuccessfullyLoginStandardUSer() {
        //  test data
        String standardUsername = "standard_user";
        String password = "secret_sauce";

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(standardUsername, password);

        Assert.assertTrue(landingPage.isUserLoggedInSuccessfully());
    }

    //  shouldShowAnErrorForLockedOutUser
    @Test
    public void shouldShowAnErrorForLockedOutUser() {

        //  HOMEWORK TO DO

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
    public void shouldAddSauceLabsBackpackIntoCartFromDetailsView() {}

    //  shouldHavePriceInformationForItemInCart
    @Test
    public void shouldHavePriceInformationForItemInCart() {}

    //  shouldRemoveSauceLabsBackpackFromCart (Verify: CartIcon or ButtonChange)
    @Test
    public void shouldRemoveSauceLabsBackpackFromCart() {}

    //  shouldRemoveSauceLabsBackpackFromCatalogPage
    @Test
    public void shouldRemoveSauceLabsBackpackFromCatalogPage() {}

    //  shouldAddListOfItemsToTheCart
    @Test
    public void shouldAddListOfItemsToTheCart() {}

    //  shouldPersistCheckoutDetails
    @Test
    public void shouldPersistCheckoutDetails() {}

    /* --------------------------------------------------------------------------------- */
    /* SOME USEFULL FUNCTIONS */

    private void addItemToTheCart(WebDriver driver, String itemName) {
        //get element add to cart button using item name and click it
        String selectorBlueprint = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";
        driver.findElement(By.xpath(selectorBlueprint.formatted(itemName))).click();

    }

}
