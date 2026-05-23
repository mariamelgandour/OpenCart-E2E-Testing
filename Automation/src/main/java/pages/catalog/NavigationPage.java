package pages.catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;
    private final Actions       actions;
    private final JavascriptExecutor js;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By navMenu    = By.xpath("//ul[contains(@class,'nav')]/li/a");
    private final By subMenuDropdown = By.xpath("//ul[contains(@class,'dropdown-menu')]");

    // ── Constructor ──────────────────────────────────────────────────────────
    public NavigationPage(WebDriver driver) {
        this.driver  = driver;
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js      = (JavascriptExecutor) driver;
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void hoverOverMainCategory(String name) {
        WebElement el = getMainCategoryElement(name);
        actions.moveToElement(el).pause(Duration.ofSeconds(1)).perform();
    }

    public void clickOnSubCategory(String name) {
        By subLink = By.xpath("//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='" + name + "']" +
                              " | //ul[contains(@class,'nav')]//a[normalize-space()='" + name + "']");
        wait.until(ExpectedConditions.elementToBeClickable(subLink)).click();
    }

    public List<WebElement> getAllMainCategories() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(navMenu));
    }

    public boolean isSubMenuVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(subMenuDropdown)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public String getCssValueOfCategory(String name, String property) {
        return getMainCategoryElement(name).getCssValue(property);
    }

    public void resetHover() {
        actions.moveToElement(driver.findElement(By.tagName("body")))
               .pause(Duration.ofMillis(300)).perform();
        js.executeScript("document.activeElement.blur();");
    }

    // ── Private helpers ───────────────────────────────────────────────────────
    private WebElement getMainCategoryElement(String name) {
        By locator = By.xpath("//ul[contains(@class,'nav')]/li/a[normalize-space()='" + name + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
