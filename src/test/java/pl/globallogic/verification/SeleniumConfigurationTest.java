package pl.globallogic.verification;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumConfigurationTest {

    @Test
    public void shouldNavigateToGooglePageAndVerifyPageTitleChrome() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        String pageTitle = driver.getTitle();
        driver.quit();
        Assert.assertEquals("Google", pageTitle);
    }

    @Test
    public void shouldNavigateToGooglePageAndVerifyPageTitleFirefox() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");
        String pageTitle = driver.getTitle();
        driver.quit();
        Assert.assertEquals("Google", pageTitle);
    }
}