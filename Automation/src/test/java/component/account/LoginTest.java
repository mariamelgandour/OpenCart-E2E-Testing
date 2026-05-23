package component.account;

import pages.account.LoginPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        new HomePage(driver).goToLogin();
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
        loginPage.loginWith("m@m.m", "test1234");

        Assert.assertTrue(loginPage.isMyAccountHeadingDisplayed(),
                "My Account heading should be visible");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/account"),
                "URL should contain account route");
    }

    @Test(priority = 2)
    public void testLoginWithInvalidCredentials() {
        loginPage.loginWith("  ", "  ");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error alert should be visible");
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "User should stay on login page");
    }
}