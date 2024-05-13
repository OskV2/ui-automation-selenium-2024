package pl.globallogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By itemsCountBadge = By.className("shopping_cart_badge");

    private final String ITEM_SELECTOR = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement findBy(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public void addToCart(String itemName) {
        findBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).click();
    }

    public int cartItemsCount() {
        return Integer.parseInt(findBy(itemsCountBadge).getText());
    }
}
