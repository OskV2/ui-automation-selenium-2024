package pl.globallogic.saucelabs;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pl.globallogic.CartPage;
import pl.globallogic.CatalogPage;
import pl.globallogic.DetailsPage;
import pl.globallogic.LandingPage;

public class SauceDemoBaseTest {

    WebDriver driver;
    protected String host = "https://www.saucedemo.com/";

    protected LandingPage landingPage;
    protected CatalogPage catalogPage;
    protected DetailsPage detailsPage;
    protected CartPage cartPage;

    @BeforeMethod
    public void testSetup() {
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(2000, 0));
        driver.manage().window().maximize();
        landingPage = new LandingPage(driver,host);
        catalogPage = new CatalogPage(driver);
        detailsPage = new DetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void testCleanUp() {
        driver.quit();
    }
}
