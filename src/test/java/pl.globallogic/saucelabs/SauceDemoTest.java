package pl.globallogic.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class SauceDemoTest {
    //  shouldShowErrorForInvalidUsername
    //  shouldShowErrorForInvalidPassword
    //  shouldShowErrorForEmptyCredentials


    //  shouldSuccessfullyLoginStandardUSer
    @Test
    public void shouldSuccessfullyLoginStandardUSer() {
        //  test data
        String standardUsername = "standard_user";
        String password = "secret_sauce";
        String inventoryUrlPart = "inventory.html";
        By usernameFieldLocator = By.id("user-name");
        By passwordFieldLocator = By.id("password");
        By loginButtonLocator = By.id("login-button");
        String host = "https://www.saucedemo.com/";

        //  setup webdriver
        WebDriver driver = new ChromeDriver();

        //  go to the main application page - arrange
        driver.get(host);

        //  enter your username and password - act
        WebElement usernameField = driver.findElement(usernameFieldLocator);
        usernameField.sendKeys(standardUsername);

        WebElement passwordField = driver.findElement(passwordFieldLocator);
        passwordField.sendKeys(password);

        //  click login button - act
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();

        //  verify successful login completion - assert
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current page URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains(inventoryUrlPart));

        driver.quit();
    }

    //  shouldShowAnErrorForLockedOutUser
    @Test
    public void shouldShowAnErrorForLockedOutUser() {

        //  HOMEWORK TO DO

    }

    //  shouldAddSauceLabsBackpackIntoCartFromCatalog
    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromCatalog() {
        String standardUsername = "standard_user";
        String password = "secret_sauce";
        String inventoryUrlPart = "inventory.html";
        By usernameFieldLocator = By.id("user-name");
        By passwordFieldLocator = By.id("password");
        By loginButtonLocator = By.id("login-button");
        By addToCartBackpackButtonLocator = By.id("add-to-cart-sauce-labs-backpack");
        By removeBackpackButtonLocator = By.id("remove-sauce-labs-backpack");
        By cartIconLocator = By.className("shopping_cart_link");
        String host = "https://www.saucedemo.com/";

        //  setup webdriver
        WebDriver driver = new ChromeDriver();

        //  go to the main application page - arrange
        driver.get(host);

        //  enter your username and password - act
        WebElement usernameField = driver.findElement(usernameFieldLocator);
        usernameField.sendKeys(standardUsername);

        WebElement passwordField = driver.findElement(passwordFieldLocator);
        passwordField.sendKeys(password);

        //  click login button - act
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();


        WebElement addToCartBackpackButton = driver.findElement(addToCartBackpackButtonLocator);
        System.out.println("Button text before adding to cart: " + addToCartBackpackButton.getText());
        addToCartBackpackButton.click();

        WebElement removeBackpackButton = driver.findElement(removeBackpackButtonLocator);
        System.out.println("Button text after adding to cart: " + removeBackpackButton.getText());

        Assert.assertTrue(removeBackpackButton.isDisplayed());

        WebElement cartIcon = driver.findElement(cartIconLocator);
        cartIcon.click();

        driver.quit();
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
