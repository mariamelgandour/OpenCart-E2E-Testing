package integration;

import pages.account.LoginPage;
import pages.checkout.CheckoutPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginThenAddToCartThenCheckoutTest extends BaseTest {

    private static final String EMAIL    = "m@m.m";
    private static final String PASSWORD = "test1234";
    private static final String PRODUCT  = "HTC Touch HD";

    @Test
    public void testLoginThenAddToCartThenCheckout() throws InterruptedException {
        HomePage home = new HomePage(driver, wait);
        home.goToLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(EMAIL, PASSWORD);

        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/account"),
                "User should be logged in");

        home.searchForProduct(PRODUCT);
        home.addProductToCart();

        Assert.assertTrue(home.getSuccessAlertText().contains("shopping cart"),
                "Product should be added to cart");

        home.goToCheckoutPage();

        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.fillBillingDetails(
                "Test", "User", "m@m.m",
                "01012345678", "123 Test St", "Cairo", "11511",
                "Egypt", "Al Qahirah"
        );
        checkout.completeCheckoutSteps();
        checkout.clickConfirmOrder();

        Assert.assertTrue(checkout.isOrderHeaderDisplayed(),
                "Order confirmation header should be visible");
        Assert.assertTrue(checkout.isOrderTextDisplayed(),
                "Order confirmation message should be visible");
        Thread.sleep(2000);
    }
}
