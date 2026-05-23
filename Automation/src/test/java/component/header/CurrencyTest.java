package component.header;

import pages.home.CurrencyDropdown;
import utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CurrencyTest extends BaseTest {

    @Test(priority = 1)
    public void verifyCurrencyDropdownIsClickable() {
        CurrencyDropdown currency = new CurrencyDropdown(driver);
        currency.selectCurrency("Euro");

        Assert.assertTrue(currency.getCurrencySymbol().contains("€"));
        Assert.assertTrue(currency.getFirstProductPrice().contains("€"));
    }

    @Test(priority = 2)
    public void verifySelectedCurrencyDisplayedInHeader() {
        CurrencyDropdown currency = new CurrencyDropdown(driver);
        currency.selectCurrency("Euro");

        String headerText = currency.getHeaderCurrencyText();
        Assert.assertTrue(headerText.contains("€") || headerText.contains("Euro"));
    }

    @Test(priority = 3)
    public void verifyCurrencyHoverEffect() {
        CurrencyDropdown currency    = new CurrencyDropdown(driver);
        By               dropdownBy = currency.getDropdownToggleLocator();

        String beforeColor = driver.findElement(dropdownBy).getCssValue("color");
        currency.hover(dropdownBy);
        String afterColor  = driver.findElement(dropdownBy).getCssValue("color");

        Assert.assertNotEquals(afterColor, beforeColor,
                "Hover effect should change visual style (color)");
    }

    @Test(priority = 4)
    public void verifyProductPriceCurrencyUpdate() throws InterruptedException {
        WebDriverWait      localWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions            actions   = new Actions(driver);
        JavascriptExecutor js        = (JavascriptExecutor) driver;

        WebElement dropdown = driver.findElement(By.id("form-currency"));
        actions.moveToElement(dropdown).perform();
        Thread.sleep(1000);
        dropdown.click();

        WebElement dollarBtn = localWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='form-currency']/div/ul/li[3]/button")));
        actions.moveToElement(dollarBtn).perform();
        js.executeScript("arguments[0].style.backgroundColor = 'lightblue';", dollarBtn);
        Thread.sleep(1500);

        dollarBtn.click();
        Thread.sleep(1500);
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '$')]")).isDisplayed());
    }
}
