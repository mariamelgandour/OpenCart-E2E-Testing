package Tests.WishList;

import Utils.BaseTest;
import Pages.WishlistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class WishlistNavigationTest extends BaseTest {

    @Test
    public void verifyClickingWishlistItemNavigatesToProductDetails() throws InterruptedException {
        WishlistPage wishlistPage = new WishlistPage(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        wishlistPage.clickWishlistHeader();
        wishlistPage.login("mariamelgandour9@gmail.com", "Mariam1552023@");


        wait.until(ExpectedConditions.urlContains("wishlist"));


        By tableLocator = By.xpath("//div[@id='content']//table");

        if (!driver.findElements(tableLocator).isEmpty()) {
            System.out.println("Product table exists, accessing the last product");
            System.out.println("Waiting for 5 seconds so you can see the click action");
            Thread.sleep(1000);
            wishlistPage.scrollAndClickProduct();



            System.out.println("Click performed! Waiting for another 5 seconds so you can see the product page before closing...");
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL Is " + currentUrl);

            if (currentUrl.contains("route=product/product")) {
                System.out.println("Success: The URL confirms that we have navigated to the product details page!");
            } else {
                System.out.println("Warning: We are still on the Wishlist page or a different page!");
            }


            WebElement cartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart")));
            Assert.assertTrue(cartBtn.isDisplayed(), "Movement To Product Details Failed");
            System.out.println("Movement To Product Details Done Successfully");

        } else {
            System.out.println("Wishlist Empty and Can't Reach TO any Products");
        }
    }
}