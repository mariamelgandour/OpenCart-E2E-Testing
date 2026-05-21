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
    private By deleteBtn = By.xpath("//div[@id='content']//table/tbody/tr[1]/td[6]/a");
    private By productImgLink = By.xpath("//div[@id='content']//table/tbody/tr[1]/td[1]/a/img");

    public WishlistPage(WebDriver driver, BaseTest baseTest) {
        this.driver = driver;
        this.baseTest = baseTest;
    }

    // 2. Actions
    public void clickWishlistHeader() {
        driver.findElement(wishlistHeaderBtn).click();
        System.out.println("Wishlist Clicked and Move to Login Page");
        baseTest.waitSeconds(2);
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        System.out.println("Fields are Filled With Data and Press on Login Button");
        baseTest.waitSeconds(2);
    }

    public void clickDeleteButton() {
        driver.findElement(deleteBtn).click();
        System.out.println("Clickable are Done in X  Button to First Product");
        baseTest.waitSeconds(2);
    }

    public void clickProductImage() {
        driver.findElement(productImgLink).click();
        System.out.println("Image Are Clicked To Move to Details Product Page ");
        baseTest.waitSeconds(2);
    }

    public void scrollAndClickProduct() {

        WebElement productLink = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[2]/a"));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
        productLink.click();
    }
}