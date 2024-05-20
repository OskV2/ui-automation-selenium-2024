package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.function.Predicate;

public class LandingPage extends BasePage {
    private final String host;
    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessage = By.tagName("h3");

    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;


    private final String url = "inventory.html";
    private Predicate<WebDriver> loginCriteria = driver -> driver.getCurrentUrl().contains(url);



    public LandingPage(WebDriver driver, String host) {
        super(driver);
        this.host = host;
        PageFactory.initElements(driver, this);
    }

    public void visit() {
        super.visit(this.host);
    }

    public void setLoginCriteria(Predicate<WebDriver> loginCriteria) {
        this.loginCriteria = loginCriteria;
    }

    public void loginWith(String username, String password) {
        logger.info("Performing log in for '{}' user with password '{}'", username, password);
//        type(usernameFieldLocator, username);
//        type(passwordFieldLocator, password);
//        click(loginButtonLocator);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public boolean isUserLoggedInSuccessfully() {
        logger.info("Verify successful login");
        return loginCriteria.test(driver);
    }

    public String getErrorMessage() {
        return findElementBy(errorMessage).getText();
    }
}
