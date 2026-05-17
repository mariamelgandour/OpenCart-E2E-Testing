package Tests.Currency;

import Pages.CurrencyPage;
import Utils.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CurrencyHoverTest extends BaseTest {

    @Test(description = "Verify visual hover effect on currency selector")
    public void verifyCurrencyHoverEffect() {

        CurrencyPage currencyPage = new CurrencyPage(driver);

        By currencyDropdown =
                By.xpath("//button[contains(@class,'dropdown-toggle')]");

        // Step 1: اقرأ اللون قبل الـ hover
        String beforeColor = driver.findElement(currencyDropdown)
                .getCssValue("color");

        // Step 2: Hover
        currencyPage.hover(currencyDropdown);

        // Step 3: اقرأ اللون بعد الـ hover
        String afterColor = driver.findElement(currencyDropdown)
                .getCssValue("color");

        // Step 4: Assertion
        Assert.assertNotEquals(
                afterColor,
                beforeColor,
                "Hover effect should change visual style (color)"
        );
    }
}