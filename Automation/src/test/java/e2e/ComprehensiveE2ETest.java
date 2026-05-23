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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * ============================================================
 * E2E Scenario: Comprehensive OpenCart Full Journey
 * ============================================================
 *
 * STEP 1  – Currency switch (Euro → USD)
 * STEP 2  – Navigate via top nav (Tablets category)
 * STEP 3  – Add product to Compare list & verify
 * STEP 4  – Remove product from Comparison page
 * STEP 5  – Register a brand-new account
 * STEP 6  – Logout after registration
 * STEP 7  – Forgot-password flow (valid email)
 * STEP 8  – Login with the new account
 * STEP 9  – Search for a product (HTC Touch HD)
 * STEP 10 – Add searched product to cart & verify alert
 * STEP 11 – View wishlist (redirect-to-login guard check)
 * STEP 12 – Go to checkout & complete full guest checkout flow
 * STEP 13 – Verify order confirmation page
 * ============================================================
 */
public class ComprehensiveE2ETest extends BaseTest {


    private static final String SEARCH_PRODUCT = "HTC Touch HD";
    private static final String PASSWORD       = "Test@1234";
    private static final String EXISTING_EMAIL = "m@m.m";

    private void log(String msg) {
        System.out.println("[E2E] " + msg);
        if (test != null) test.info(msg);
    }


    @Test
    public void testComprehensiveOpenCartJourney() throws InterruptedException {

        String newEmail = "e2e_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";

        HomePage         home       = new HomePage(driver, wait);
        CurrencyDropdown currency   = new CurrencyDropdown(driver);
        NavigationPage   navPage    = new NavigationPage(driver);
        ProductPage      productPage= new ProductPage(driver);
        CheckoutPage     checkout   = new CheckoutPage(driver, wait);

        log("STEP 1 – Currency switch");

        currency.selectCurrency("Euro");
        Assert.assertTrue(currency.getCurrencySymbol().contains("€"),
                "STEP 1 FAILED: Euro symbol not shown after switch");
        Assert.assertTrue(currency.getFirstProductPrice().contains("€"),
                "STEP 1 FAILED: Product price not updated to Euro");

        currency.selectCurrency("US Dollar");
        Assert.assertTrue(currency.getCurrencySymbol().contains("$"),
                "STEP 1 FAILED: USD symbol not shown after switching back");

        log("STEP 1 PASSED ✓");


        log("STEP 2 – Top-nav navigation to Tablets");

        navPage.hoverOverMainCategory("Tablets");
        Thread.sleep(1000); // ← زود pause عشان الـ submenu يظهر

        navPage.clickOnSubCategory("Tablets");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=product/category"),
                "STEP 2 FAILED: Did not navigate to Tablets category. URL: " + driver.getCurrentUrl());

        log("STEP 2 PASSED ✓");


        log("STEP 3 – Add product to Compare list");

        productPage.addProductToCompare("Samsung Galaxy Tab 10.1");
        String compareAlert = productPage.getSuccessAlertText();
        Assert.assertTrue(compareAlert.contains("Success: You have added"),
                "STEP 3 FAILED: Compare success alert not shown. Got: " + compareAlert);

        log("STEP 3 PASSED ✓");


        log("STEP 4 – Navigate to Compare page and remove item");

        ComparisonPage comparePage = productPage.clickCompareLinkInAlert();
        Assert.assertTrue(comparePage.getComparePageTitle().contains("Product Comparison"),
                "STEP 4 FAILED: Not on Product Comparison page");

        comparePage.removeProductFromCompare();
        Assert.assertTrue(comparePage.getEmptyPageMessage()
                        .contains("You have not chosen any products to compare"),
                "STEP 4 FAILED: Compare page not empty after removal");

        log("STEP 4 PASSED ✓");


        log("STEP 5 – Register new account: " + newEmail);

        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        home.goToRegister();

        RegisterPage register = new RegisterPage(driver);
        register.fillFirstName("Comp");
        register.fillLastName("E2E");
        register.fillEmail(newEmail);
        register.fillTelephone("01099887766");
        register.fillPassword(PASSWORD);
        register.fillConfirmPassword(PASSWORD);
        register.checkAgree();
        register.clickContinue();

        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/success"),
                "STEP 5 FAILED: Registration did not succeed. URL: " + driver.getCurrentUrl());

        log("STEP 5 PASSED ✓");


        log("STEP 6 – Logout");

        home.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/logout")
                        || driver.getCurrentUrl().contains("route=common/home"),
                "STEP 6 FAILED: Logout did not complete. URL: " + driver.getCurrentUrl());

        log("STEP 6 PASSED ✓");


        log("STEP 7 – Forgot Password flow");

        home.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToForgotPassword();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.submitEmail(EXISTING_EMAIL);

        Assert.assertTrue(forgotPage.isSuccessAlertDisplayed(),
                "STEP 7 FAILED: Forgot-password success alert not shown for known email");

        log("STEP 7 PASSED ✓");


        log("STEP 8 – Login with new account");

        home.goToLogin();
        loginPage = new LoginPage(driver);
        loginPage.loginWith(newEmail, PASSWORD);

        Assert.assertTrue(loginPage.isMyAccountHeadingDisplayed(),
                "STEP 8 FAILED: My Account heading not visible after login");
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/account"),
                "STEP 8 FAILED: Not on account page after login. URL: " + driver.getCurrentUrl());

        log("STEP 8 PASSED ✓");


        log("STEP 9 – Search for: " + SEARCH_PRODUCT);

        home.searchForProduct(SEARCH_PRODUCT);
        Assert.assertTrue(driver.getCurrentUrl().contains("search=HTC"),
                "STEP 9 FAILED: Search did not execute. URL: " + driver.getCurrentUrl());

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.isResultCard1Displayed(),
                "STEP 9 FAILED: No search results displayed");

        log("STEP 9 PASSED ✓");


        log("STEP 10 – Add product to cart");

        home.addProductToCart();

        String cartAlert = home.getSuccessAlertText();
        Assert.assertTrue(cartAlert.contains("Success") && cartAlert.contains("shopping cart"),
                "STEP 10 FAILED: Add-to-cart alert missing or wrong. Got: " + cartAlert);

        Thread.sleep(1500);

        log("STEP 10 PASSED ✓");


        log("STEP 11 – Wishlist header click (logged-in redirect check)");

        WebElement wishlistBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wishlist-total']/span")));
        Assert.assertTrue(wishlistBtn.isDisplayed(),
                "STEP 11 FAILED: Wishlist button not visible");

        wishlistBtn.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("route=account/wishlist"),
                ExpectedConditions.urlContains("route=account/login")
        ));

        String wishlistUrl = driver.getCurrentUrl();
        Assert.assertTrue(wishlistUrl.contains("wishlist") || wishlistUrl.contains("login"),
                "STEP 11 FAILED: Unexpected URL after wishlist click: " + wishlistUrl);

        log("STEP 11 PASSED ✓");


        log("STEP 12 – Checkout flow");


        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        home.logout();


        home.searchForProduct(SEARCH_PRODUCT);
        Thread.sleep(1000);
        home.addProductToCart();
        Thread.sleep(1500);


        driver.get("https://awesomeqa.com/ui/index.php?route=checkout/checkout");

        checkout.selectGuestCheckoutAndContinue();

        checkout.fillBillingDetails(
                "Comp", "E2E", newEmail,
                "01099887766", "456 Flow Street", "Giza", "12555",
                "Egypt", "Al Jizah"
        );

        checkout.completeCheckoutSteps();
        checkout.clickConfirmOrder();

        log("STEP 12 PASSED ✓");

        log("STEP 13 – Verify order confirmation page");

        Assert.assertTrue(checkout.isOrderHeaderDisplayed(),
                "STEP 13 FAILED: 'Your order has been placed!' header not shown");
        Assert.assertTrue(checkout.isOrderTextDisplayed(),
                "STEP 13 FAILED: Order confirmation text not shown");

        log("STEP 13 PASSED ✓");
        log("══════════════════════════════════════════════════════");
        log("ALL 13 STEPS PASSED — Comprehensive E2E Journey done!");
        log("══════════════════════════════════════════════════════");

        Thread.sleep(3000);
    }
}