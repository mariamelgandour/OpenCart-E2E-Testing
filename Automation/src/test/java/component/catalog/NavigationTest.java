package component.catalog;

import pages.catalog.NavigationPage;
import pages.catalog.ProductPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(priority = 1)
    public void testNavigateToSubCategory() {
        NavigationPage nav = new NavigationPage(driver);
        nav.hoverOverMainCategory("Desktops");
        nav.clickOnSubCategory("Mac (1)");

        Assert.assertTrue(driver.getTitle().contains("Mac"));
    }

    @Test(priority = 2)
    public void testClickShowAllLink() {
        NavigationPage nav = new NavigationPage(driver);
        nav.hoverOverMainCategory("Desktops");
        nav.clickOnSubCategory("Show All Desktops");

        Assert.assertTrue(driver.getTitle().contains("Desktops"));
    }

    @Test(priority = 3)
    public void testProductCountVerification() {
        NavigationPage nav     = new NavigationPage(driver);
        ProductPage    product = new ProductPage(driver);

        nav.hoverOverMainCategory("Desktops");
        nav.clickOnSubCategory("Mac (1)");

        Assert.assertEquals(product.getActualProductsCount(), 1, "Product count mismatch!");
    }
}
