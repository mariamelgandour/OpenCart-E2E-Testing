package Tests.Hover;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class BreadcrumbTest extends BaseTest {

    @Test
    public void verifyBreadcrumbStructureAndNavigation() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // CASE 1

        WebElement main1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/a"));
        String main1Text = main1.getText().trim();
        main1.click();

        WebElement sub1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/div/div/ul/li[2]/a"));
        String sub1Text = sub1.getText().trim();

        if (sub1Text.contains("(")) {
            sub1Text = sub1Text.split("\\(")[0].trim();
        }
        sub1.click();
        WebElement breadcrumbElement1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='breadcrumb']"))
        );
        String breadcrumb1 = breadcrumbElement1.getText().toLowerCase();

        System.out.println("Breadcrumb 1 Actual: " + breadcrumb1);


        Thread.sleep(3000);

        Assert.assertTrue(breadcrumb1.contains(main1Text.toLowerCase()), "Breadcrumb missing main category 1");
        Assert.assertTrue(breadcrumb1.contains(sub1Text.toLowerCase()), "Breadcrumb missing subcategory 1");


        // CASE 2
        driver.get(driver.getCurrentUrl().split("index.php")[0]);

        WebElement main2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[3]/a"))
        );
        String main2Text = main2.getText().trim();
        main2.click();

        WebElement sub2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[3]/div/div/ul/li[2]/a"))
        );
        String sub2Text = sub2.getText().trim();

        if (sub2Text.contains("(")) {
            sub2Text = sub2Text.split("\\(")[0].trim();
        }
        sub2.click();

        WebElement breadcrumbElement2 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='breadcrumb']"))
        );
        String breadcrumb2 = breadcrumbElement2.getText().toLowerCase();

        System.out.println("Breadcrumb 2 Actual: " + breadcrumb2);


        Thread.sleep(3000);

        Assert.assertTrue(breadcrumb2.contains(main2Text.toLowerCase()), "Breadcrumb missing main category 2");
        Assert.assertTrue(breadcrumb2.contains(sub2Text.toLowerCase()), "Breadcrumb missing subcategory 2");
    }
}