package pl.globallogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetailsPage extends Base {
    private final By itemName = By.className("inventory_details_name");
    private final By itemPrice = By.className("inventory_details_price");
    private final By itemsCountBadge = By.className("shopping_cart_badge");
    private final String addToCartButton = "//*[text()='Add to cart']";


    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        findBy(By.xpath((addToCartButton))).click();
    }

    public int cartItemsCount() {
        return Integer.parseInt(findBy(itemsCountBadge).getText());
    }

}
