package component.catalog;

import pages.catalog.ComparisonPage;
import pages.catalog.NavigationPage;
import pages.catalog.ProductPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ComparisonTest extends BaseTest {

    private NavigationPage navigationPage;
    private ProductPage    productPage;

    @BeforeMethod
    public void navigateToTablets() {
        navigationPage = new NavigationPage(driver);
        productPage    = new ProductPage(driver);
        navigationPage.hoverOverMainCategory("Tablets");
        navigationPage.clickOnSubCategory("Tablets");
    }

    @Test(priority = 1)
    public void testAddProductToCompare() {
        productPage.addProductToCompare("Samsung Galaxy Tab 10.1");
        Assert.assertTrue(productPage.getSuccessAlertText().contains("Success: You have added"));
    }

    @Test(priority = 2)
    public void testNavigateToComparePageViaAlert() {
        productPage.addProductToCompare("Samsung Galaxy Tab 10.1");
        ComparisonPage comparePage = productPage.clickCompareLinkInAlert();
        Assert.assertTrue(comparePage.getComparePageTitle().contains("Product Comparison"));
    }

    @Test(priority = 3)
    public void testRemoveProductFromComparisonTable() {
        productPage.addProductToCompare("Samsung Galaxy Tab 10.1");
        productPage.clickCompareLinkInAlert();

        ComparisonPage comparisonPage = new ComparisonPage(driver);
        comparisonPage.removeProductFromCompare();

        Assert.assertTrue(comparisonPage.getEmptyPageMessage()
                .contains("You have not chosen any products to compare."));
    }
}
