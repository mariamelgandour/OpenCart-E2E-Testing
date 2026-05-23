package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CurrencyDropdown {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By dropdownToggle   = By.xpath("//button[contains(@class,'dropdown-toggle')]");
    private final By currencySymbol   = By.xpath("//button[contains(@class,'dropdown-toggle')]//strong");
    private final By firstProductPrice = By.xpath("(//span[contains(@class,'price-new')])[1]");
    private final By dollarOption     = By.xpath("//*[@id=\"form-currency\"]/div/ul/li[3]/button");

    private static final Map<String, String> CURRENCY_SYMBOLS = Map.of(
            "Euro",           "€",
            "Pound Sterling", "£",
            "US Dollar",      "$"
    );

    // ── Constructor ──────────────────────────────────────────────────────────
    public CurrencyDropdown(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void selectCurrency(String currencyName) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownToggle)).click();

        By option = By.xpath("//button[contains(text(),'" + currencyName + "')]");
        WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(option));
        new Actions(driver).moveToElement(optionEl).pause(Duration.ofMillis(500)).click().perform();

        String expectedSymbol = CURRENCY_SYMBOLS.getOrDefault(currencyName, "");
        if (!expectedSymbol.isEmpty()) {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(currencySymbol, expectedSymbol));
        }
    }

    public void changeCurrencyToDollar() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownToggle)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dollarOption)).click();
    }

    public void hover(By locator) {
        new Actions(driver).moveToElement(driver.findElement(locator)).perform();
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String getCurrencySymbol() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currencySymbol)).getText();
    }

    public String getFirstProductPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductPrice)).getText();
    }

    public String getHeaderCurrencyText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownToggle)).getText();
    }

    public By getDropdownToggleLocator() {
        return dropdownToggle;
    }
}
