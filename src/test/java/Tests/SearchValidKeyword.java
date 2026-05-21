package tests;

import pages.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchValidKeyword extends BaseTest {

    @Test(description = "TC-001 - Search with valid product keyword returns matching product cards")
    public void searchWithValidKeyword() {

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Enter keyword and submit search
        searchPage.searchFor("MacBook");

        // Step 2: Assert URL contains the search keyword
        Assert.assertTrue(
                searchPage.getCurrentUrl().contains("search=MacBook"),
                "URL should contain 'search=MacBook' after searching"
        );

        // Step 3: Assert all 3 product result cards are displayed
        Assert.assertTrue(
                searchPage.isResultCard1Displayed(),
                "First product result card should be visible"
        );

        Assert.assertTrue(
                searchPage.isResultCard2Displayed(),
                "Second product result card should be visible"
        );

        Assert.assertTrue(
                searchPage.isResultCard3Displayed(),
                "Third product result card should be visible"
        );
    }
}
