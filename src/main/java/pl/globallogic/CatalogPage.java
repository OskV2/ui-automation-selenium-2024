package pl.globallogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends Base {
    private final By itemsCountBadge = By.className("shopping_cart_badge");
    private final By cartButton = By.className("shopping_cart_container");

    private final String ITEM_DETAILS = "//*[@id=\"item_%s_title_link\"]/div";
    private final String ITEM_SELECTOR = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";


    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String itemName) {
        findBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).click();
    }

    //  Text displayed on add to cart button for specific product
    //  The parameter is the name of the item whose button should be checked
    public String textDisplayedOnButton(String itemName) {
        return findBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).getText();
    }

    public int cartItemsCount() {
        return Integer.parseInt(findBy(itemsCountBadge).getText());
    }

    public void goToItemDetails(String itemId) { // number of item
        findBy(By.xpath(ITEM_DETAILS.formatted(itemId))).click();
    }

    public void goToCartPage() {
        findBy(cartButton).click();
    }
}
