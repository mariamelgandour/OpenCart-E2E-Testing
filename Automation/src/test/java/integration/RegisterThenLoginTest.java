package integration;

import pages.account.LoginPage;
import pages.account.RegisterPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegisterThenLoginTest extends BaseTest {

    @Test
    public void testRegisterThenLogin() {
        String email    = "user" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        String password = "Test@1234";

        HomePage homePage = new HomePage(driver);
        homePage.goToRegister();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillFirstName("Test");
        registerPage.fillLastName("User");
        registerPage.fillEmail(email);
        registerPage.fillTelephone("01012345678");
        registerPage.fillPassword(password);
        registerPage.fillConfirmPassword(password);
        registerPage.checkAgree();
        registerPage.clickContinue();

        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/success"),
                "Registration should succeed");

        homePage.logout();
        homePage.goToLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(email, password);

        Assert.assertTrue(loginPage.isMyAccountHeadingDisplayed(),
                "User should be able to login with newly registered account");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/account"),
                "Should land on account page after login");
    }
}
