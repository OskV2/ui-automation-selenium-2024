package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage extends BasePage {
    private final By itemName = By.className("inventory_details_name");
    private final By itemPrice = By.className("inventory_details_price");
    private final By itemsCountBadge = By.className("shopping_cart_badge");
    private final String addToCartButton = "//*[text()='Add to cart']";


    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        findElementBy(By.xpath((addToCartButton))).click();
    }

    public int cartItemsCount() {
        return Integer.parseInt(findElementBy(itemsCountBadge).getText());
    }

}
