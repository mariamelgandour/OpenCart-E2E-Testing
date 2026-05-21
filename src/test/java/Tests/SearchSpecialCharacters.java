package tests;

import pages.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchSpecialCharacters extends BaseTest {

    @Test(description = "TC-035 - Search with special characters only returns no results message")
    public void searchWithSpecialCharacters() {

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Enter special characters only and submit
        searchPage.searchFor("@#$%^&*()");

        // Step 2: Assert no results message is displayed
        Assert.assertTrue(
                searchPage.isNoResultsMessageDisplayed(),
                "Special characters-only search should return no results message"
        );
    }
}