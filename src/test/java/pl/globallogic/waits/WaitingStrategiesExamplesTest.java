package pl.globallogic.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

/*

4 Types of waits
Thread.sleep(1000)
Implicit wait - driver.manage.timeouts.implicitlyWait(Duration)
Explicit wait - new WebDriverWait().until(one of the ExpectedConditions object)
Fluent wait

*/

public class WaitingStrategiesExamplesTest {

    protected WebDriver driver;
    protected final String PLAYGROUND_BASE = "https://bonigarcia.dev/selenium-webdriver-java/";
    protected final String WEB_FORM = "web-form.html";

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();

    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadImplicitly() {
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement landscape = driver.findElement(By.id("landscape"));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadExplicitly() {
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExpectedCondition<WebElement> ourCondition = wd -> wd.findElement(By.id("landscape"));
        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadFluently() {
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilCalculatorResultWillBeAvailable() {
        // 5 + 5 == 10
        String expectedResult = "10";
        By screenLocator = By.className("screen");
        driver.get(PLAYGROUND_BASE + "/slow-calculator.html");
        driver.findElement(By.xpath("//span[text()='5']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='5']")).click();
        WebElement equalsButton = driver.findElement(By.xpath("//span[text()='=']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(equalsButton).click().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(screenLocator, expectedResult));
        String actualResult = driver.findElement(screenLocator).getText();
        Assert.assertEquals(expectedResult, actualResult);
    }

}
