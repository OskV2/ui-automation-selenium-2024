package pl.globallogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends Base {
    private final By firstItemPrice = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div");
    private final By firstItemRemove = By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/button");
    private final By itemsCountBadge = By.className("shopping_cart_badge");
    private final By itemNameLocator = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeFirstItem() {
        findBy(firstItemRemove).click();
    }

    public boolean isPriceDisplayedForFirstElement() {
        return findBy(firstItemPrice).isDisplayed();
    }

    public boolean isItemsCountBadgeDisplayed() {
        return getWait().until(
                ExpectedConditions.invisibilityOfElementLocated(itemsCountBadge)
        );
    }

    public ArrayList<String> listOfNamesOfProductsInCart() {
        List<WebElement> productsInCart = getDriver().findElements(itemNameLocator);
        ArrayList<String> productsInCartNames = new ArrayList<>();

        for (int i = 0; i < productsInCart.size(); i++) {
            productsInCartNames.add(productsInCart.get(i).getText());
        }

        return productsInCartNames;
    }
}
