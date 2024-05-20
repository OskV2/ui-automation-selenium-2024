package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {
    private final By itemsCountBadge = By.className("shopping_cart_badge");
    private final By cartButton = By.className("shopping_cart_container");

    private final String ITEM_DETAILS = "//*[@id=\"item_%s_title_link\"]/div";
    private final String ITEM_SELECTOR = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";


    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String itemName) {
        click(By.xpath(ITEM_SELECTOR.formatted(itemName)));
    }

    //  This functions returns the text displayed on add to cart button for specific product
    //  The parameter is the name of the item whose button should be checked
    public String textDisplayedOnButton(String itemName) {
        return findElementBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).getText();
    }

    public int cartItemsCount() {
        return Integer.parseInt(findElementBy(itemsCountBadge).getText());
    }

    public void goToItemDetails(String itemId) { // number of item
        findElementBy(By.xpath(ITEM_DETAILS.formatted(itemId))).click();
    }

    public void goToCartPage() {
        findElementBy(cartButton).click();
    }
}
