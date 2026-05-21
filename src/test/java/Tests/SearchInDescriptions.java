package tests;

import pages.SearchPage;
import pages.ProductPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInDescriptions extends BaseTest {

    @Test(description = "TC-005 - Search in product descriptions returns products containing keyword in description")
    public void searchInProductDescriptions() {

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Type keyword in home screen search field and submit
        searchPage.searchFor("display");

        // Step 2: Check the "Search in product descriptions" checkbox
        searchPage.checkSearchInDescriptions();

        // Step 3: Click the search button on the search results page
        searchPage.clickSearchPageButton();

        // Step 4: Assert the first result card is displayed
        Assert.assertTrue(
                searchPage.isResultCard1Displayed(),
                "At least one product result card should be visible after searching in descriptions"
        );

        // Step 5: Click the first result card to go to product detail page
        searchPage.clickResultCard1();

        // Step 6: Assert the product description contains the keyword "display"
        ProductPage productPage = new ProductPage(driver);

        Assert.assertTrue(
                productPage.getDescriptionText().toLowerCase().contains("display"),
                "Product description should contain the keyword 'display'"
        );
    }
}