package component.account;

import pages.account.RegisterPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegisterTest extends BaseTest {

    private static final String PRIVACY_WARNING = "Warning: You must agree to the Privacy Policy!";

    private RegisterPage registerPage;

    @BeforeMethod
    public void openRegisterPage() {
        new HomePage(driver).goToRegister();
        registerPage = new RegisterPage(driver);
    }

    @Test(priority = 1)
    public void testSuccessfulRegistration() {
        registerPage.fillFirstName("Test");
        registerPage.fillLastName("User");
        registerPage.fillEmail(uniqueEmail());
        registerPage.fillTelephone("01012345678");
        registerPage.fillPassword("Test@1234");
        registerPage.fillConfirmPassword("Test@1234");
        registerPage.checkAgree();
        registerPage.clickContinue();

        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/success"));
        Assert.assertTrue(registerPage.isSuccessDisplayed());
    }

    @Test(priority = 2)
    public void testRegistrationWithEmptyFields() {
        registerPage.clickContinue();

        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/register"));
        Assert.assertTrue(registerPage.getPrivacyPolicyErrorText().contains(PRIVACY_WARNING));
    }

    private static String uniqueEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
    }
}
