package Tests.WishList;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class WishlistClickable extends BaseTest {

    @Test
    public void verifyWishlistClickableAffordance() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        By wishlistLocator = By.xpath("//*[@id='wishlist-total']/span");
        WebElement wishlistBtn = wait.until(ExpectedConditions.elementToBeClickable(wishlistLocator));


        Assert.assertTrue(wishlistBtn.isDisplayed(), "Button UnVisible");
        wishlistBtn.click();
        System.out.println("Clickable On WishList Done Successfully");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("route=account/wishlist"),
                ExpectedConditions.urlContains("route=account/login")
        ));
   String currentUrl = driver.getCurrentUrl();
        System.out.println("Movement Done Successfully" + currentUrl);
        Assert.assertTrue(currentUrl.contains("wishlist") || currentUrl.contains("login"),
                "Don't Move To Wishlist");
    }
}