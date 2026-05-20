package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CurrencyPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By currencyDropdown =
            By.xpath("//button[contains(@class,'dropdown-toggle')]");
    private By currencySymbol =
            By.xpath("//button[contains(@class,'dropdown-toggle')]//strong");
    private By firstProductPrice =
            By.xpath("(//span[contains(@class,'price-new')])[1]");
    By dollarOption = By.xpath("//*[@id=\"form-currency\"]/div/ul/li[3]/button");
    By productPrice = By.xpath("...");
    // Constructor
    public CurrencyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void selectCurrency(String currency) {

        Actions actions = new Actions(driver);


        driver.findElement(currencyDropdown).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By currencyOption = By.xpath("//button[contains(text(),'" + currency + "')]");

        actions.moveToElement(driver.findElement(currencyOption))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();


        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                currencySymbol,
                "€"
        ));
    }
    // GETTERS
    public String getCurrencySymbol() {
        return driver.findElement(currencySymbol).getText();
    }

    public String getFirstProductPrice() {
        return driver.findElement(firstProductPrice).getText();
    }
    public String getHeaderCurrencyText() {
        return driver.findElement(By.xpath("//button[contains(@class,'dropdown-toggle')]")).getText();
    }
    public void hover(By locator) {

        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(locator))
                .perform();
    }
    public void waitForElementVisible(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void changeCurrencyToDollar() {
        driver.findElement(currencyDropdown).click();
        driver.findElement(dollarOption).click();
    }


}