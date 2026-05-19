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
            System.out.println(" جدول المنتجات موجود، جاري الوصول لآخر منتج...");
            System.out.println(" سأنتظر 5 ثواني لكي تري الضغطة بعينك...");
            Thread.sleep(1000);
            wishlistPage.scrollAndClickProduct();

            // الانتظار قليلاً للتأكد من فتح الصفحة

            System.out.println(" الضغط تم! سأنتظر 5 ثواني أخرى لكي تري صفحة المنتج قبل القفل...");
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println(">> الرابط الحالي بعد الضغط هو: " + currentUrl);

            if (currentUrl.contains("route=product/product")) {
                System.out.println(" نجاح: الرابط يؤكد أننا انتقلنا لصفحة تفاصيل المنتج!");
            } else {
                System.out.println(" تحذير: لا نزال في صفحة الـ Wishlist أو صفحة أخرى!");
            }

            // 5. التحقق النهائي من وجود زر الإضافة للسلة في صفحة المنتج
            WebElement cartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart")));
            Assert.assertTrue(cartBtn.isDisplayed(), " لم يتم الانتقال لصفحة المنتج!");
            System.out.println(" نجاح: تم الوصول لصفحة التفاصيل بنجاح!");

        } else {
            System.out.println(" Wishlist فاضية ولا توجد منتجات للضغط عليها.");
        }
    }
}