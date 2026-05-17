package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CategoryPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    // Locators

    private By mainCategories =
            By.xpath("//ul[contains(@class,'nav')]/li/a");

    private By subMenu =
            By.xpath("//ul[contains(@class,'dropdown-menu')]");



    public List<WebElement> getAllMainCategories() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(mainCategories)
        );
    }



    private By mainCategory(String name) {
        return By.xpath("//ul[contains(@class,'nav')]/li/a[normalize-space()='" + name + "']");
    }

    public WebElement getMainCategory(String name) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(mainCategory(name))
        );
    }


    public void hoverOnElement(WebElement element) {
        actions.moveToElement(element)
                .pause(Duration.ofSeconds(2))
                .perform();
    }

    public void hoverOnMainCategory(String name) {
        WebElement main = getMainCategory(name);
        hoverOnElement(main);
    }


    public void jsHover(WebElement element) {
        js.executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles:true}));",
                element
        );
    }

    public boolean isSubMenuVisible() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(subMenu)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForHoverEffect() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetHover() {

        // 1. Move away
        actions.moveToElement(driver.findElement(By.tagName("body")))
                .pause(Duration.ofMillis(300))
                .perform();

        // 2. Extra JS blur (important for sticky hover menus)
        js.executeScript("document.activeElement.blur();");

        // 3. small wait
        try {
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public String getCssValue(String name, String property) {
        return getMainCategory(name).getCssValue(property);
    }

    public String getClassAttribute(String name) {
        return getMainCategory(name).getAttribute("class");
    }
}