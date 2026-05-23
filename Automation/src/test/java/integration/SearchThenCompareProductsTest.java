package integration;

import pages.catalog.ComparisonPage;
import pages.catalog.NavigationPage;
import pages.catalog.ProductPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchThenCompareProductsTest extends BaseTest {

    @Test
    public void testNavigateThenCompareProducts() {
        NavigationPage nav = new NavigationPage(driver);
        nav.hoverOverMainCategory("Tablets");
        nav.clickOnSubCategory("Tablets");

        Assert.assertTrue(driver.getTitle().contains("Tablet"),
                "Should navigate to Tablets category");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCompare("Samsung Galaxy Tab 10.1");

        Assert.assertTrue(productPage.getSuccessAlertText().contains("Success: You have added"),
                "Product should be added to comparison list");

        ComparisonPage comparisonPage = productPage.clickCompareLinkInAlert();

        Assert.assertTrue(comparisonPage.getComparePageTitle().contains("Product Comparison"),
                "Should land on Product Comparison page");
        Assert.assertFalse(
                comparisonPage.getEmptyPageMessage().contains("You have not chosen any products"),
                "Comparison page should contain the added product");
    }
}
