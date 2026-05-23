package component.cart;

import pages.cart.CartPage;
import pages.checkout.CheckoutPage;
import pages.home.HomePage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(priority = 1)
    public void verifyAddingSingleProductToCart() throws InterruptedException {
        new HomePage(driver, wait).addSingleProductToCart();

        Assert.assertTrue(
            new HomePage(driver, wait).getSuccessAlertText()
                .contains("Success: You have added MacBook to your shopping cart!"));
        Thread.sleep(3000);
    }

    @Test(priority = 2, description = "[Known Bug] Out-of-stock iPhone can still be added")
    public void verifyOutOfStockBehavior() {
        HomePage home = new HomePage(driver, wait);
        home.addIphoneToCartFromHome();

        Assert.assertTrue(home.getSuccessAlertText()
                .contains("Success: You have added iPhone to your shopping cart!"),
                "Behavior changed — recheck if bug was fixed.");
    }

    @Test(priority = 3)
    public void verifyRemoveProductFromCart() throws InterruptedException {
        HomePage     home     = new HomePage(driver, wait);
        CartPage     cart     = new CartPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        home.searchForProduct("HTC Touch HD");
        home.addProductToCart();
        checkout.goToShoppingCart();
        cart.removeProductFromCart();
        driver.navigate().refresh();

        Assert.assertEquals(cart.getEmptyCartMessage(), "Your shopping cart is empty!");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void verifyAddProductWithRequiredOptions() throws InterruptedException {
        HomePage home = new HomePage(driver, wait);
        CartPage cart = new CartPage(driver, wait);

        home.clickOnAppleCinema();
        cart.fillAllRequiredOptions(
                "6", "10", "Blue", "Maryam Test",
                "This is an automated test comment.",
                "maryam_document.txt", "2026-05-18", "10:00",
                "2026-05-18 10:00", "2"
        );
        cart.clickAddToCart();
        Thread.sleep(4000);

        Assert.assertTrue(cart.getSuccessMessageText()
                .contains("Success: You have added Apple Cinema 30\" to your shopping cart!"));
        Thread.sleep(4000);
    }
}
