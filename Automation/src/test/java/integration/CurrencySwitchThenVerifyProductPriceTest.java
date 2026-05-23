package integration;

import pages.home.CurrencyDropdown;
import pages.home.HomePage;
import pages.catalog.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CurrencySwitchThenVerifyProductPriceTest extends BaseTest {

    private static final String PRODUCT = "MacBook";

    @Test
    public void testCurrencySwitchThenSearchVerifyPrice() {
        CurrencyDropdown currency = new CurrencyDropdown(driver);

        currency.selectCurrency("Euro");

        Assert.assertTrue(currency.getCurrencySymbol().contains("€"),
                "Currency symbol in header should switch to €");
        Assert.assertTrue(currency.getFirstProductPrice().contains("€"),
                "Product prices on home page should show € after switch");

        new HomePage(driver, wait).searchForProduct(PRODUCT);

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.getCurrentUrl().contains("search=MacBook"),
                "Should land on search results page");
        Assert.assertTrue(driver.getPageSource().contains("€"),
                "Search results page should display prices in Euro");

        currency.selectCurrency("US Dollar");

        Assert.assertTrue(currency.getCurrencySymbol().contains("$"),
                "Currency should switch back to USD");
    }
}
