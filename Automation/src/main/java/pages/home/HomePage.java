package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver      driver;
    private final WebDriverWait  wait;

    // ── Locators ────────────────────────────────────────────────────────────
    private final By myAccount    = By.cssSelector("a[title='My Account']");
    private final By registerLink = By.linkText("Register");
    private final By loginLink    = By.linkText("Login");
    private final By logoutLink   = By.linkText("Logout");
    private final By searchInput  = By.xpath("//*[@id='search']/input");
    private final By searchButton = By.xpath("//*[@id='search']/span/button");
    private final By successAlert = By.cssSelector(".alert-success");
    private final By cartButton   = By.cssSelector("#cart > button");
    private final By checkoutLink = By.linkText("Checkout");

    // ── Product-specific locators ────────────────────────────────────────────
    private final By macbookCard  = By.xpath(
            "//div[contains(@class,'product-thumb')][.//a[contains(text(),'MacBook')]]");
    private final By macbookBtn   = By.xpath(
            "//div[contains(@class,'product-thumb')][.//a[contains(text(),'MacBook')]]" +
            "//button[contains(@onclick,'cart.add')]");
    private final By iphoneCard   = By.xpath(
            "//div[contains(@class,'product-thumb')][.//a[contains(text(),'iPhone')]]");
    private final By iphoneBtn    = By.xpath(
            "//div[contains(@class,'product-thumb')][.//a[contains(text(),'iPhone')]]" +
            "//button[contains(@onclick,'cart.add')]");
    private final By appleCinema  = By.xpath("//a[contains(text(),\"Apple Cinema 30\")]");

    // ── Constructor ─────────────────────────────────────────────────────────
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait != null ? wait : new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Navigation helpers ───────────────────────────────────────────────────
    public void openMyAccountMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
    }

    public void goToRegister() {
        openMyAccountMenu();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void goToLogin() {
        openMyAccountMenu();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void logout() {
        openMyAccountMenu();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void goToCheckoutPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLink)).click();
    }

    // ── Search ───────────────────────────────────────────────────────────────
    public void searchForProduct(String keyword) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

    // ── Cart actions ─────────────────────────────────────────────────────────
    public void addProductToCart() {
        By firstCard  = By.cssSelector(".product-layout:first-child");
        By firstBtn   = By.cssSelector(".product-layout:first-child button[onclick*='cart.add']");

        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(firstCard));
        new Actions(driver).moveToElement(card).pause(Duration.ofMillis(500)).perform();

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(firstBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void addSingleProductToCart() {
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(macbookCard));
        new Actions(driver).moveToElement(card).pause(Duration.ofMillis(500)).perform();
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(macbookBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void addIphoneToCartFromHome() {
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(iphoneCard));
        new Actions(driver).moveToElement(card).pause(Duration.ofMillis(500)).perform();
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(iphoneBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void clickOnAppleCinema() {
        wait.until(ExpectedConditions.elementToBeClickable(appleCinema)).click();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public String getSuccessAlertText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert)).getText();
        } catch (org.openqa.selenium.TimeoutException e) {
            return "";
        }
    }
}
