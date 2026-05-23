package component.header;

import pages.catalog.ProductPage;
import pages.catalog.SearchPage;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(priority = 1)
    public void searchWithValidKeyword() {
        SearchPage search = new SearchPage(driver);
        search.searchFor("MacBook");

        Assert.assertTrue(search.getCurrentUrl().contains("search=MacBook"));
        Assert.assertTrue(search.isResultCard1Displayed());
        Assert.assertTrue(search.isResultCard2Displayed());
        Assert.assertTrue(search.isResultCard3Displayed());
    }

    @Test(priority = 2)
    public void searchInProductDescriptions() {
        SearchPage search = new SearchPage(driver);
        search.searchFor("display");
        search.checkSearchInDescriptions();
        search.clickSearchPageButton();

        Assert.assertTrue(search.isResultCard1Displayed());

        search.clickResultCard1();
        Assert.assertTrue(new ProductPage(driver).getDescriptionText().toLowerCase().contains("display"));
    }

    @Test(priority = 3)
    public void searchWithNoResults() {
        SearchPage search = new SearchPage(driver);
        search.searchFor("zzznoproduct999");

        Assert.assertTrue(search.isNoResultsMessageDisplayed());
        Assert.assertEquals(search.getNoResultsMessageText(),
                "There is no product that matches the search criteria.");
    }

    @Test(priority = 4)
    public void searchResultNavigatesToProductPage() {
        SearchPage search = new SearchPage(driver);
        search.searchFor("iPhone");
        search.clickFirstResult();

        ProductPage product = new ProductPage(driver);
        Assert.assertEquals(product.getCurrentUrl(),
                "https://awesomeqa.com/ui/index.php?route=product/product&product_id=40&search=iPhone");
        Assert.assertEquals(product.getProductTitle(), "iPhone");
    }

    @Test(priority = 5, description = "[Known Bug] Whitespace search returns products instead of no-results")
    public void searchWithWhitespaceInput() {
        SearchPage search = new SearchPage(driver);
        search.searchFor("   ");

        Assert.assertFalse(search.isNoResultsMessageDisplayed(),
                "KNOWN BUG: Whitespace-only search returns all products");
    }

    @Test(priority = 6)
    public void searchWithSpecialCharacters() {
        SearchPage search = new SearchPage(driver);
        search.searchFor("@#$%^&*()");

        Assert.assertTrue(search.isNoResultsMessageDisplayed());
    }
}
