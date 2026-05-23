package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By emailField     = By.id("input-email");
    private final By continueButton = By.cssSelector("input[value='Continue']");
    private final By successAlert   = By.cssSelector(".alert-success");
    private final By errorAlert     = By.cssSelector(".alert-danger");

    // ── Constructor ──────────────────────────────────────────────────────────
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void submitEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(continueButton).click();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public boolean isSuccessAlertDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isErrorAlertDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
            return true;
        } catch (Exception e) { return false; }
    }
}
