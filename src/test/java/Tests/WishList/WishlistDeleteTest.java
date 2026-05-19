package Tests.WishList;

import Utils.BaseTest;
import Pages.WishlistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistDeleteTest extends BaseTest {

    @Test
    public void verifyProductRemovalFromWishlistUpdatesUI() {
        WishlistPage wishlistPage = new WishlistPage(driver, this);


        wishlistPage.clickWishlistHeader();
        wishlistPage.login("mariamelgandour9@gmail.com", "Mariam1552023@");


        By firstDeleteBtn = By.xpath("//div[@id='content']//table/tbody/tr[1]/td[6]/a");


        if (!driver.findElements(firstDeleteBtn).isEmpty()) {
            wishlistPage.clickDeleteButton();


            By successAlert = By.cssSelector(".alert-success");
            WebElement alert = driver.findElement(successAlert);
            Assert.assertTrue(alert.isDisplayed(), " خطأ: رسالة نجاح الحذف لم تظهر!");
            Assert.assertTrue(alert.getText().contains("Success"), " خطأ: نص رسالة التأكيد غير صحيح!");
            System.out.println(" نجاح: تم حذف أول منتج متاح والـ UI اتحدثت!");
        } else {
            System.out.println("Wishlist فاضية تماماً، مفيش أي منتج عشان نمسحه.");
        }
    }
}