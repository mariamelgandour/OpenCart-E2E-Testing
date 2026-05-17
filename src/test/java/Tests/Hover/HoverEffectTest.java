package Tests.Hover;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class HoverEffectTest extends BaseTest {

    @Test
    public void verifyHoverOnCategoriesSimple() {

        List<By> categories = Arrays.asList(
                By.xpath("//*[@id='menu']/div[2]/ul/li[3]/a"),
                By.xpath("//*[@id='menu']/div[2]/ul/li[5]/a"),
                By.xpath("//*[@id='menu']/div[2]/ul/li[1]/a")
        );

        for (By locator : categories) {

            WebElement element = driver.findElement(locator);

            String name = element.getText();
            System.out.println("Testing: " + name);

            // ✔ simple hover (ONLY THIS)
            new org.openqa.selenium.interactions.Actions(driver)
                    .moveToElement(element)
                    .pause(java.time.Duration.ofSeconds(1))
                    .perform();


            int menuCount = driver.findElements(
                    By.xpath("//ul[contains(@class,'dropdown-menu')]")
            ).size();

            System.out.println("Menu count: " + menuCount);

            Assert.assertTrue(menuCount > 0,
                    "Submenu should appear for: " + name);
        }
    }
}