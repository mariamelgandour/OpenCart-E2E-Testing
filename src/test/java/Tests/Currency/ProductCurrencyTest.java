package Tests.Currency;

import Utils.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ProductCurrencyTest extends BaseTest {

     @Test
    public void verifyProductPriceCurrencyUpdate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);


        WebElement dropdown = driver.findElement(By.id("form-currency"));
        actions.moveToElement(dropdown).perform();
        Thread.sleep(1000);
        dropdown.click();
        WebElement dollarBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='form-currency']/div/ul/li[3]/button")));


        actions.moveToElement(dollarBtn).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = 'lightblue';", dollarBtn);
        Thread.sleep(1500);


        dollarBtn.click();
        Thread.sleep(1500);
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '$')]")).isDisplayed());
    }
}