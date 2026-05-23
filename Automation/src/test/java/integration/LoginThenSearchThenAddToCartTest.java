package integration;

import pages.account.LoginPage;
import pages.home.HomePage;
import pages.catalog.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginThenSearchThenAddToCartTest extends BaseTest {

    private static final String EMAIL    = "m@m.m";
    private static final String PASSWORD = "test1234";
    private static final String PRODUCT  = "HTC Touch HD";

    @Test
    public void testLoginThenSearchThenAddToCart() {
        HomePage home = new HomePage(driver, wait);
        home.goToLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(EMAIL, PASSWORD);

        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/account"),
                "User should be on account page after login");

        home.searchForProduct(PRODUCT);

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.getCurrentUrl().contains("search=HTC"),
                "Search results page should be shown");

        home.addProductToCart();

        String alertText = home.getSuccessAlertText();
        Assert.assertTrue(alertText.contains("Success") && alertText.contains("shopping cart"),
                "Product should be added to cart successfully");
    }
}
