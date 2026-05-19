package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import Utils.BaseTest;
import org.openqa.selenium.WebElement;

public class WishlistPage {
    private WebDriver driver;
    private BaseTest baseTest;

    // 1. Locators
    private By wishlistHeaderBtn = By.xpath("//*[@id=\"wishlist-total\"]/span");
    private By emailField = By.xpath("//*[@id=\"input-email\"]");
    private By passwordField = By.xpath("//*[@id=\"input-password\"]");
    private By loginButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input[1]");

    // XPath مرن لأول زرار حذف في الجدول مهما كان المنتج
    private By deleteBtn = By.xpath("//div[@id='content']//table/tbody/tr[1]/td[6]/a");

    // XPath
    private By productImgLink = By.xpath("//div[@id='content']//table/tbody/tr[1]/td[1]/a/img");

    public WishlistPage(WebDriver driver, BaseTest baseTest) {
        this.driver = driver;
        this.baseTest = baseTest;
    }

    // 2. Actions
    public void clickWishlistHeader() {
        driver.findElement(wishlistHeaderBtn).click();
        System.out.println("تم الضغط على الـ Wishlist والانتقال لصفحة الـ Login...");
        baseTest.waitSeconds(2);
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        System.out.println("تم كتابة البيانات والضغط على Login...");
        baseTest.waitSeconds(2);
    }

    public void clickDeleteButton() {
        driver.findElement(deleteBtn).click();
        System.out.println("تم الضغط على زرار الحذف (X) لأول منتج متاح...");
        baseTest.waitSeconds(2);
    }

    public void clickProductImage() {
        driver.findElement(productImgLink).click();
        System.out.println("تم الضغط على صورة أول منتج متاح للانتقال لصفحة التفاصيل...");
        baseTest.waitSeconds(2);
    }
    public void scrollAndClickProduct() {
        String xpathToLink = "//*[@id='content']/div[1]/table/tbody/tr[16]/td[1]/a";
        WebElement link = driver.findElement(By.xpath(xpathToLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        baseTest.waitSeconds(1);

        System.out.println(">> جاري الضغط الآن على المنتج رقم 16 في القائمة...");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);

        System.out.println("✅ تم تنفيذ أمر الضغط بنجاح على المنتج.");
    }
}