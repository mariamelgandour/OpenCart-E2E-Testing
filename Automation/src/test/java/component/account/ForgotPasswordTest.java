package component.account;

import pages.account.ForgotPasswordPage;
import pages.account.LoginPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void openForgotPasswordPage() {
        new HomePage(driver).goToLogin();
        new LoginPage(driver).goToForgotPassword();
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test(priority = 1)
    public void testForgotPasswordWithRegisteredEmail() {
        forgotPasswordPage.submitEmail("test@example.com");
        Assert.assertTrue(forgotPasswordPage.isSuccessAlertDisplayed(),
                "Success alert should be visible");
    }

    @Test(priority = 2)
    public void testForgotPasswordWithUnregisteredEmail() {
        forgotPasswordPage.submitEmail("notregistered@test.com");
        Assert.assertTrue(forgotPasswordPage.isErrorAlertDisplayed(),
                "Error alert should be visible");
    }
}
