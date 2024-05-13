package pl.globallogic.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pl.globallogic.CatalogPage;
import pl.globallogic.LandingPage;

public class SauceDemoBaseTest {

    WebDriver driver;
    protected String host = "https://www.saucedemo.com/";

    @BeforeClass
    public void testSuiteSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        landingPage = new LandingPage(driver, host);
        catalogPage = new CatalogPage(driver);
    }

    @AfterMethod
    public void testCleanUp() {
        driver.quit();
    }

}
