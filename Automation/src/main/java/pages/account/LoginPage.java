package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By emailField         = By.id("input-email");
    private final By passwordField      = By.id("input-password");
    private final By loginButton        = By.cssSelector("input[value='Login']");
    private final By errorMessage       = By.cssSelector(".alert-danger");
    private final By myAccountHeading   = By.xpath("//h2[contains(text(),'My Account')]");
    private final By forgotPasswordLink = By.linkText("Forgotten Password");

    // ── Constructor ──────────────────────────────────────────────────────────
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void loginWith(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void goToForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isMyAccountHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeading));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("route=account/login");
    }
}
