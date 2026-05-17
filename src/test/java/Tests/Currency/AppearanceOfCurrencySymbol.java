package Tests.Currency;

import Pages.CurrencyPage;
import Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppearanceOfCurrencySymbol extends BaseTest {
    @Test(description = "OC_CURR_TC_02 - Verify selected currency is displayed in header")
    public void verifySelectedCurrencyDisplayedInHeader() {

        CurrencyPage currencyPage = new CurrencyPage(driver);

        currencyPage.selectCurrency("Euro");

        Assert.assertTrue(
                currencyPage.getHeaderCurrencyText().contains("€") ||
                        currencyPage.getHeaderCurrencyText().contains("Euro"),
                "Selected currency should appear in header"
        );
    }
}
