package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    private final By myAccount    = By.cssSelector("a[title='My Account']");
    private final By registerLink = By.linkText("Register");
    private final By loginLink    = By.linkText("Login");
    private final By logoutLink   = By.linkText("Logout");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMyAccountMenu() {
        driver.findElement(myAccount).click();
    }

    public void goToRegister() {
        openMyAccountMenu();
        driver.findElement(registerLink).click();
    }

    public void goToLogin() {
        openMyAccountMenu();
        driver.findElement(loginLink).click();
    }

    public void logout() {
        openMyAccountMenu();
        driver.findElement(logoutLink).click();
    }
}
