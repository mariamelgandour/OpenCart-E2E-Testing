package pages.catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComparisonPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By pageHeader      = By.cssSelector("#content h1");
    private final By removeBtn       = By.xpath("//a[contains(@class, 'btn-danger') or text()='Remove']");
    private final By emptyContentText = By.cssSelector("#content p");

    // ── Constructor ──────────────────────────────────────────────────────────
    public ComparisonPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void removeProductFromCompare() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public String getComparePageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).getText();
    }

    public String getEmptyPageMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyContentText)).getText();
    }
}
