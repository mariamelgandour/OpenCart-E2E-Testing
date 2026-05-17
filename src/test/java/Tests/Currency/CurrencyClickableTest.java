package Tests.Currency;

import Pages.CurrencyPage;
import Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CurrencyClickableTest extends BaseTest {
    @Test(description = "OC_CURR_TC_01 - Verify currency selector is clickable and responsive")
    public void verifyCurrencyDropdownIsClickable() {

        CurrencyPage currencyPage = new CurrencyPage(driver);

        // Step 1: Click dropdown (Act)
        currencyPage.selectCurrency("Euro");

        // Step 2: Validate UI response (Assert 1)
        Assert.assertTrue(
                currencyPage.getCurrencySymbol().contains("€"),
                "Currency symbol should be Euro"
        );

        // Step 3: Validate price updated (Assert 2)
        Assert.assertTrue(
                currencyPage.getFirstProductPrice().contains("€"),
                "Product price should update to Euro"
        );
    }
}
