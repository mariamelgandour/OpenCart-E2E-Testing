package component.checkout;

import pages.checkout.CheckoutPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulOrderPlacementAsGuest() throws InterruptedException {
        HomePage     home     = new HomePage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        home.searchForProduct("HTC Touch HD");
        home.addProductToCart();
        home.goToCheckoutPage();

        checkout.selectGuestCheckoutAndContinue();
        checkout.fillBillingDetails(
                "Maryam", "Saleh", "maryam.test@example.com",
                "0123456789", "123 Test Street", "Mansourah", "11511",
                "Egypt", "Ad Daqahliyah"
        );
        checkout.completeCheckoutSteps();
        checkout.clickConfirmOrder();

        Assert.assertTrue(checkout.isOrderHeaderDisplayed());
        Assert.assertTrue(checkout.isOrderTextDisplayed());
        Thread.sleep(4000);
    }

    @Test(priority = 2)
    public void verifyTermsAndConditionsIsRequired() throws InterruptedException {
        HomePage     home     = new HomePage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        home.searchForProduct("HTC Touch HD");
        home.addProductToCart();
        home.goToCheckoutPage();

        checkout.selectGuestCheckoutAndContinue();
        checkout.fillBillingDetails(
                "Maryam", "Saleh", "maryam.test@example.com",
                "0123456789", "123 Test Street", "Mansourah", "11511",
                "Egypt", "Ad Daqahliyah"
        );
        checkout.deliveryMethodAgreement();
        checkout.clickPaymentMethodContinueWithoutAgreeing();

        Assert.assertTrue(checkout.getTermsWarningText()
                .contains("Warning: You must agree to the Terms & Conditions!"));
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void verifySystemBlocksCheckoutWhenQuantityExceedsStock() throws InterruptedException {
        HomePage     home     = new HomePage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        home.searchForProduct("HTC Touch HD");
        home.addProductToCart();
        checkout.goToShoppingCart();
        checkout.updateProductQuantity("1000");

        Assert.assertTrue(checkout.getStockWarningText()
                .contains("Products marked with *** are not available in the desired quantity or not in stock!"));

        checkout.clickOnCheckout();

        Assert.assertFalse(driver.getCurrentUrl().contains("route=checkout/checkout"),
                "BUG: System allowed proceeding to Checkout with quantity exceeding stock!");
        Thread.sleep(2000);
    }
}
