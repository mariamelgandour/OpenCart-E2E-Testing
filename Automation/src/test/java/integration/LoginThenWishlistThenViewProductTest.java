package integration;

import pages.account.LoginPage;
import pages.wishlist.WishlistPage;
import utils.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginThenWishlistThenViewProductTest extends BaseTest {

    private static final String EMAIL    = "mariamelgandour9@gmail.com";
    private static final String PASSWORD = "Mariam1552023@";

    @Test
    public void testLoginThenWishlistThenViewProduct() throws InterruptedException {
        WishlistPage wishlist = new WishlistPage(driver);
        wishlist.clickWishlistHeader();

        wait.until(ExpectedConditions.urlContains("route=account/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/login"),
                "Unauthenticated user should be redirected to login");

        wishlist.login(EMAIL, PASSWORD);

        wait.until(ExpectedConditions.urlContains("route=account/wishlist"));
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/wishlist"),
                "After login, user should land on wishlist page");

        if (wishlist.isWishlistTablePresent()) {
            Thread.sleep(1000);
            wishlist.scrollAndClickLastProduct();
            Thread.sleep(2000);

            Assert.assertTrue(driver.getCurrentUrl().contains("route=product/product"),
                    "Should navigate to product detail page");
        } else {
            System.out.println("[INFO] Wishlist is empty — skipping product navigation step");
        }
    }
}
