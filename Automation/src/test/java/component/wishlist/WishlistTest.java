package component.wishlist;

import pages.wishlist.WishlistPage;
import utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WishlistTest extends BaseTest {

    private static final String EMAIL    = "mariamelgandour9@gmail.com";
    private static final String PASSWORD = "Mariam1552023@";

    @Test(priority = 1)
    public void verifyWishlistClickableAffordance() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By wishlistLocator = By.xpath("//*[@id='wishlist-total']/span");

        WebElement btn = localWait.until(ExpectedConditions.elementToBeClickable(wishlistLocator));
        Assert.assertTrue(btn.isDisplayed());
        btn.click();

        localWait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("route=account/wishlist"),
                ExpectedConditions.urlContains("route=account/login")
        ));

        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("wishlist") || url.contains("login"));
    }

    @Test(priority = 2)
    public void verifyProductRemovalFromWishlist() {
        WishlistPage wishlist = new WishlistPage(driver);
        wishlist.clickWishlistHeader();
        wishlist.login(EMAIL, PASSWORD);

        if (wishlist.isDeleteBtnPresent()) {
            wishlist.clickDeleteButton();
            WebElement alert = driver.findElement(By.cssSelector(".alert-success"));
            Assert.assertTrue(alert.isDisplayed());
            Assert.assertTrue(alert.getText().contains("Success"));
        } else {
            System.out.println("Wishlist is empty — skipping deletion check");
        }
    }

    @Test(priority = 3)
    public void verifyClickingWishlistItemNavigatesToProductDetails() throws InterruptedException {
        WishlistPage  wishlist  = new WishlistPage(driver);
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wishlist.clickWishlistHeader();
        wishlist.login(EMAIL, PASSWORD);
        localWait.until(ExpectedConditions.urlContains("wishlist"));

        if (wishlist.isWishlistTablePresent()) {
            Thread.sleep(1000);
            wishlist.scrollAndClickLastProduct();
            Thread.sleep(3000);

            WebElement cartBtn = localWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("button-cart")));
            Assert.assertTrue(cartBtn.isDisplayed());
        } else {
            System.out.println("Wishlist is empty — cannot navigate to product");
        }
    }
}