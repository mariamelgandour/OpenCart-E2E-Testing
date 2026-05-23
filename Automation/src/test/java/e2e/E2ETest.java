package e2e;

import pages.account.ForgotPasswordPage;
import pages.account.LoginPage;
import pages.account.RegisterPage;
import pages.catalog.ComparisonPage;
import pages.catalog.NavigationPage;
import pages.catalog.ProductPage;
import pages.catalog.SearchPage;
import pages.checkout.CheckoutPage;
import pages.home.CurrencyDropdown;
import pages.home.HomePage;
import utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class E2ETest extends BaseTest {

    private static final String CHECKOUT_URL   = "https://awesomeqa.com/ui/index.php?route=checkout/checkout";
    private static final String SEARCH_PRODUCT = "HTC Touch HD";
    private static final String PASSWORD       = "Test@1234";
    private static final String EXISTING_EMAIL = "m@m.m";
    private static final String FIRST_NAME     = "Mohamed";
    private static final String LAST_NAME      = "Ahmed";
    private static final String PHONE          = "01099887766";

    private String generateEmail() {
        return "Mohamed_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
    }

    @Test
    public void testComprehensiveOpenCartJourney() throws InterruptedException {

        String       newEmail    = generateEmail();
        HomePage     home        = new HomePage(driver, wait);
        CurrencyDropdown currency = new CurrencyDropdown(driver);
        NavigationPage navPage   = new NavigationPage(driver);
        ProductPage  productPage = new ProductPage(driver);
        CheckoutPage checkout    = new CheckoutPage(driver, wait);

        // STEP 1 – Currency switch
        currency.selectCurrency("Euro");
        Assert.assertTrue(currency.getCurrencySymbol().contains("€"),
                "STEP 1: Euro symbol not shown");
        Assert.assertTrue(currency.getFirstProductPrice().contains("€"),
                "STEP 1: Product price not updated to Euro");
        currency.selectCurrency("US Dollar");
        Assert.assertTrue(currency.getCurrencySymbol().contains("$"),
                "STEP 1: USD symbol not shown after switch");

        // STEP 2 – Navigate to Tablets
        navPage.hoverOverMainCategory("Tablets");
        waitSeconds(1);
        navPage.clickOnSubCategory("Tablets");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=product/category"),
                "STEP 2: Did not navigate to Tablets. URL: " + driver.getCurrentUrl());

        // STEP 3 – Add to Compare
        productPage.addProductToCompare("Samsung Galaxy Tab 10.1");
        Assert.assertTrue(productPage.getSuccessAlertText().contains("Success: You have added"),
                "STEP 3: Compare alert not shown");

        // STEP 4 – Compare page & remove
        ComparisonPage comparePage = productPage.clickCompareLinkInAlert();
        Assert.assertTrue(comparePage.getComparePageTitle().contains("Product Comparison"),
                "STEP 4: Not on Comparison page");
        comparePage.removeProductFromCompare();
        Assert.assertTrue(comparePage.getEmptyPageMessage()
                        .contains("You have not chosen any products to compare"),
                "STEP 4: Compare page not empty after removal");

        // STEP 5 – Register
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        home.goToRegister();
        RegisterPage register = new RegisterPage(driver);
        register.fillFirstName(FIRST_NAME);
        register.fillLastName(LAST_NAME);
        register.fillEmail(newEmail);
        register.fillTelephone(PHONE);
        register.fillPassword(PASSWORD);
        register.fillConfirmPassword(PASSWORD);
        register.checkAgree();
        register.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/success"),
                "STEP 5: Registration failed. URL: " + driver.getCurrentUrl());

        // STEP 6 – Logout
        home.logout();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("route=account/logout") ||
                        driver.getCurrentUrl().contains("route=common/home"),
                "STEP 6: Logout failed. URL: " + driver.getCurrentUrl());

        // STEP 7 – Forgot Password
        home.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToForgotPassword();
        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.submitEmail(EXISTING_EMAIL);
        Assert.assertTrue(forgotPage.isSuccessAlertDisplayed(),
                "STEP 7: Forgot-password alert not shown");

        // STEP 8 – Login
        home.goToLogin();
        loginPage = new LoginPage(driver);
        loginPage.loginWith(newEmail, PASSWORD);
        Assert.assertTrue(loginPage.isMyAccountHeadingDisplayed(),
                "STEP 8: My Account heading not visible");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/account"),
                "STEP 8: Not on account page. URL: " + driver.getCurrentUrl());

        // STEP 9 – Search
        home.searchForProduct(SEARCH_PRODUCT);
        Assert.assertTrue(driver.getCurrentUrl().contains("search=HTC"),
                "STEP 9: Search failed. URL: " + driver.getCurrentUrl());
        Assert.assertTrue(new SearchPage(driver).isResultCard1Displayed(),
                "STEP 9: No results displayed");

        // STEP 10 – Add to cart
        home.addProductToCart();
        String cartAlert = home.getSuccessAlertText();
        Assert.assertTrue(cartAlert.contains("Success") && cartAlert.contains("shopping cart"),
                "STEP 10: Cart alert wrong. Got: " + cartAlert);
        waitSeconds(2);

        // STEP 11 – Wishlist click
        var wishlistBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wishlist-total']/span")));
        wishlistBtn.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("route=account/wishlist"),
                ExpectedConditions.urlContains("route=account/login")
        ));
        String wishlistUrl = driver.getCurrentUrl();
        Assert.assertTrue(wishlistUrl.contains("wishlist") || wishlistUrl.contains("login"),
                "STEP 11: Unexpected URL: " + wishlistUrl);

        // STEP 12 – Checkout
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        home.logout();
        home.searchForProduct(SEARCH_PRODUCT);
        waitSeconds(1);
        home.addProductToCart();
        waitSeconds(2);
        driver.get(CHECKOUT_URL);
        checkout.selectGuestCheckoutAndContinue();
        checkout.fillBillingDetails(
                FIRST_NAME, LAST_NAME, newEmail, PHONE,
                "456 Flow Street", "Giza", "12555", "Egypt", "Al Jizah"
        );
        checkout.completeCheckoutSteps();
        checkout.clickConfirmOrder();

        // STEP 13 – Order confirmation
        Assert.assertTrue(checkout.isOrderHeaderDisplayed(),
                "STEP 13: Order header not shown");
        Assert.assertTrue(checkout.isOrderTextDisplayed(),
                "STEP 13: Order confirmation text not shown");

        waitSeconds(3);
    }
}