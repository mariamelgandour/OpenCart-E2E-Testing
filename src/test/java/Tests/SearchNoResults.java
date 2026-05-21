package tests;

import pages.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchNoResults extends BaseTest {

    @Test(description = "TC-006 - Search with non-existent keyword returns no results message")
    public void searchWithNoResults() {

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Enter a keyword that matches no products and submit
        searchPage.searchFor("zzznoproduct999");

        // Step 2: Assert the no results message is displayed
        Assert.assertTrue(
                searchPage.isNoResultsMessageDisplayed(),
                "No results message should be visible on the page"
        );

        // Step 3: Assert the exact message text is correct
        Assert.assertEquals(
                searchPage.getNoResultsMessageText(),
                "There is no product that matches the search criteria.",
                "No results message text does not match expected"
        );
    }
}