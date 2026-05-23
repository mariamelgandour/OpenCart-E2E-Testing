package pages.wishlist;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WishlistPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By wishlistHeaderBtn = By.xpath("//*[@id=\"wishlist-total\"]/span");
    private final By emailField        = By.id("input-email");
    private final By passwordField     = By.id("input-password");
    private final By loginButton       = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input[1]");
    private final By deleteBtn         = By.xpath("//div[@id='content']//table/tbody/tr[1]/td[6]/a");
    private final By wishlistTable     = By.xpath("//div[@id='content']//table");
    private final By tableRows        = By.xpath("//*[@id='content']/div[1]/table/tbody/tr");

    // ── Constructor ──────────────────────────────────────────────────────────
    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void clickWishlistHeader() {
        driver.findElement(wishlistHeaderBtn).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void clickDeleteButton() {
        driver.findElement(deleteBtn).click();
    }

    public boolean isWishlistTablePresent() {
        return !driver.findElements(wishlistTable).isEmpty();
    }

    public boolean isDeleteBtnPresent() {
        return !driver.findElements(deleteBtn).isEmpty();
    }

    public void scrollAndClickLastProduct() {
        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.isEmpty()) throw new RuntimeException("Wishlist is empty — no products found");

        WebElement link = rows.get(rows.size() - 1).findElement(By.xpath("td[1]/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }
}
