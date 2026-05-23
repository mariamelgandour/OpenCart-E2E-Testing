package pages.catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By successAlert      = By.cssSelector(".alert-success");
    private final By compareAlertLink  = By.linkText("product comparison");
    private final By productThumbnails = By.cssSelector(".product-layout");
    private final By productTitle      = By.cssSelector("#content h1");
    private final By productDescription = By.cssSelector("#tab-description");

    // ── Constructor ──────────────────────────────────────────────────────────
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void addProductToCompare(String productName) {
        By compareBtn = By.xpath(
                "//h4/a[text()='" + productName + "']/ancestor::div[@class='product-thumb']" +
                "//button[contains(@onclick, 'compare.add')]");
        wait.until(ExpectedConditions.elementToBeClickable(compareBtn)).click();
    }

    public ComparisonPage clickCompareLinkInAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(compareAlertLink)).click();
        return new ComparisonPage(driver);
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public String getSuccessAlertText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert)).getText();
    }

    public int getActualProductsCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productThumbnails));
        List<WebElement> products = driver.findElements(productThumbnails);
        return products.size();
    }

    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
    }

    public String getDescriptionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productDescription)).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
