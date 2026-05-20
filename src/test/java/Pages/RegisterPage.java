package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName          = By.id("input-firstname");
    private final By lastName           = By.id("input-lastname");
    private final By email              = By.id("input-email");
    private final By telephone          = By.id("input-telephone");
    private final By password           = By.id("input-password");
    private final By confirmPassword    = By.id("input-confirm");
    private final By agreeCheckbox      = By.name("agree");
    private final By continueButton     = By.cssSelector("input[value='Continue']");
    private final By successHeading     = By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]");
    private final By privacyPolicyError = By.cssSelector(".alert-danger");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillFirstName(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).sendKeys(value);
    }

    public void fillLastName(String value)        { driver.findElement(lastName).sendKeys(value); }
    public void fillEmail(String value)           { driver.findElement(email).sendKeys(value); }
    public void fillTelephone(String value)       { driver.findElement(telephone).sendKeys(value); }
    public void fillPassword(String value)        { driver.findElement(password).sendKeys(value); }
    public void fillConfirmPassword(String value) { driver.findElement(confirmPassword).sendKeys(value); }

    public void checkAgree() {
        wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox));
        driver.findElement(agreeCheckbox).click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.findElement(continueButton).click();
    }

    public boolean isSuccessDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successHeading));
            return true;
        } catch (Exception e) { return false; }
    }

    public String getPrivacyPolicyErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(privacyPolicyError));
        return driver.findElement(privacyPolicyError).getText();
    }
}
