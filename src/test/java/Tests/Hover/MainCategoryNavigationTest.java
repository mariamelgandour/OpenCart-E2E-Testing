package Tests.Hover;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class MainCategoryNavigationTest extends BaseTest {

    @Test
    public void verifyNavigationOnCategoriesWithoutSubcategories() {

        List<By> categories = Arrays.asList(
                By.xpath("//*[@id='menu']/div[2]/ul/li[4]/a"),
                By.xpath("//*[@id='menu']/div[2]/ul/li[5]/a"),
                By.xpath("//*[@id='menu']/div[2]/ul/li[7]/a")
        );

        String homeUrl = driver.getCurrentUrl();

        for (By locator : categories) {

            WebElement category = driver.findElement(locator);

            String name = category.getText();
            System.out.println("Clicking on: " + name);


            category.click();


            try { Thread.sleep(1500); } catch (Exception e) {}


            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            Assert.assertNotEquals(currentUrl, homeUrl,
                    "Category should navigate to another page: " + name);


            driver.navigate().back();

            try { Thread.sleep(1500); } catch (Exception e) {}


            homeUrl = driver.getCurrentUrl();
        }
    }
}
