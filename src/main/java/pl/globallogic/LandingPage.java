package pl.globallogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends Base {
    private final String host;
    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessage = By.tagName("h3");

    private final String IS_USER_LOGGED_IN_SIGN = "inventory.html";

    public LandingPage(WebDriver driver, String host) {
        super(driver);
        this.host = host;
    }

    public void visit() {
        getDriver().get(host);
    }

    public void loginWith(String username, String password) {
        findBy(usernameFieldLocator).sendKeys(username);
        findBy(passwordFieldLocator).sendKeys(password);
        findBy(loginButtonLocator).click();
    }

    public boolean isUserLoggedInSuccessfully() {
        return getDriver().getCurrentUrl().contains(IS_USER_LOGGED_IN_SIGN);
    }

    public String getErrorMessage() {
        return findBy(errorMessage).getText();
    }
}
