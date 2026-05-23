package component.header;

import utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class NavigationHoverTest extends BaseTest {

    @Test(priority = 1)
    public void verifyHoverOnCategoriesShowsSubmenu() {
        List<By> categories = Arrays.asList(
                By.xpath("//*[@id='menu']/div[2]/ul/li[3]/a"),
                By.xpath("//*[@id='menu']/div[2]/ul/li[5]/a"),
                By.xpath("//*[@id='menu']/div[2]/ul/li[1]/a")
        );

        for (By locator : categories) {
            WebElement element = driver.findElement(locator);
            new Actions(driver).moveToElement(element).pause(Duration.ofSeconds(1)).perform();

            int menuCount = driver.findElements(
                    By.xpath("//ul[contains(@class,'dropdown-menu')]")).size();
            Assert.assertTrue(menuCount > 0,
                    "Submenu should appear for: " + element.getText());
        }
    }

    @Test(priority = 2)
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
            category.click();

            try { Thread.sleep(1500); } catch (Exception ignored) {}

            Assert.assertNotEquals(driver.getCurrentUrl(), homeUrl,
                    "Category should navigate to another page: " + name);

            driver.navigate().back();
            try { Thread.sleep(1500); } catch (Exception ignored) {}
            homeUrl = driver.getCurrentUrl();
        }
    }

    @Test(priority = 3)
    public void verifyBreadcrumbStructureAndNavigation() throws InterruptedException {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Case 1 — first main category
        WebElement main1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/a"));
        String main1Text = main1.getText().trim();
        main1.click();

        WebElement sub1 = driver.findElement(
                By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/div/div/ul/li[2]/a"));
        String sub1Text = sub1.getText().trim();
        if (sub1Text.contains("(")) sub1Text = sub1Text.split("\\(")[0].trim();
        sub1.click();

        String breadcrumb1 = localWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='breadcrumb']"))).getText().toLowerCase();
        Thread.sleep(3000);

        Assert.assertTrue(breadcrumb1.contains(main1Text.toLowerCase()));
        Assert.assertTrue(breadcrumb1.contains(sub1Text.toLowerCase()));

        // Case 2 — third main category
        driver.get(driver.getCurrentUrl().split("index.php")[0]);

        WebElement main2 = localWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/nav/div[2]/ul/li[3]/a")));
        String main2Text = main2.getText().trim();
        main2.click();

        WebElement sub2 = localWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/nav/div[2]/ul/li[3]/div/div/ul/li[2]/a")));
        String sub2Text = sub2.getText().trim();
        if (sub2Text.contains("(")) sub2Text = sub2Text.split("\\(")[0].trim();
        sub2.click();

        String breadcrumb2 = localWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='breadcrumb']"))).getText().toLowerCase();
        Thread.sleep(3000);

        Assert.assertTrue(breadcrumb2.contains(main2Text.toLowerCase()));
        Assert.assertTrue(breadcrumb2.contains(sub2Text.toLowerCase()));
    }
}
