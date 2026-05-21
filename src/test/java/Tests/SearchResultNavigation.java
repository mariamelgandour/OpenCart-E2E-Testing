package tests;

import pages.SearchPage;
import pages.ProductPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultNavigation extends BaseTest {

    @Test(description = "TC-007 - Clicking a search result navigates to the correct product detail page")
    public void searchResultNavigatesToProductPage() {

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Search for "iPhone"
        searchPage.searchFor("iPhone");

        // Step 2: Click the first result image
        searchPage.clickFirstResult();

        // Step 3: Switch to ProductPage and assert correct URL
        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(
                productPage.getCurrentUrl(),
                "https://awesomeqa.com/ui/index.php?route=product/product&product_id=40&search=iPhone",
                "URL does not match the expected iPhone product page URL"
        );

        // Step 4: Assert the product title is correct
        Assert.assertEquals(
                productPage.getProductTitle(),
                "iPhone",
                "Product title on detail page does not match expected"
        );
    }
}