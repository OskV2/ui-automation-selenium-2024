package pl.globallogic.saucelabs;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.globallogic.drivermanager.DriverManager;
import pl.globallogic.pages.CartPage;
import pl.globallogic.pages.CatalogPage;
import pl.globallogic.pages.DetailsPage;
import pl.globallogic.pages.LandingPage;

public class SauceDemoBaseTest {

    WebDriver driver;
    protected String host = "https://www.saucedemo.com/";

    protected LandingPage landingPage;
    protected CatalogPage catalogPage;
    protected DetailsPage detailsPage;
    protected CartPage cartPage;

    @BeforeMethod
    public void testSetup() {
//        driver = DriverManager.getDriver(System.getProperty("browser"));

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");  //  Browser window will not appear in headless mode, but test will be done
//        opt.addArguments("-private");

        driver = new ChromeDriver(opt);

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
