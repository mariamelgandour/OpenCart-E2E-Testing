package tests;

import pages.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchWhitespaceInput extends BaseTest {

    @Test(description = "TC-034 - Search with whitespace-only input should return no results message (currently fails - known bug)")
    public void searchWithWhitespaceInput() {

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Enter whitespace-only input and submit
        searchPage.searchFor("   ");

        // Step 2: Assert no results message is displayed
        // NOTE: This test is expected to FAIL — known bug
        // Actual behavior: site returns all available products
        // Expected behavior: site should return no results message
        Assert.assertTrue(
                searchPage.isNoResultsMessageDisplayed(),
                "BUG: Whitespace-only search should return no results message but returns all products instead"
        );
        
    }
}