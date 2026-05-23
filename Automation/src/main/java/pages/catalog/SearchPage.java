package pages.catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    private final WebDriver driver;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By searchInput          = By.xpath("//*[@id='search']/input");
    private final By searchButton         = By.xpath("//*[@id='search']/span/button");
    private final By resultCard1          = By.xpath("//*[@id='content']/div[3]/div[1]");
    private final By resultCard2          = By.xpath("//*[@id='content']/div[3]/div[2]");
    private final By resultCard3          = By.xpath("//*[@id='content']/div[3]/div[3]");
    private final By noResultsMessage     = By.xpath("//*[@id='content']/p[2]");
    private final By firstResultImage     = By.xpath("//*[@id='content']/div[3]/div/div/div[1]/a/img");
    private final By searchInDescCheckbox = By.xpath("//*[@id='content']/p[1]/label");
    private final By searchPageButton     = By.xpath("//*[@id='button-search']");

    // ── Constructor ──────────────────────────────────────────────────────────
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void searchFor(String keyword) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

    public void checkSearchInDescriptions() {
        driver.findElement(searchInDescCheckbox).click();
    }

    public void clickSearchPageButton() {
        driver.findElement(searchPageButton).click();
    }

    public void clickFirstResult() {
        driver.findElement(firstResultImage).click();
    }

    public void clickResultCard1() {
        driver.findElement(resultCard1).click();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public boolean isResultCard1Displayed() { return driver.findElement(resultCard1).isDisplayed(); }
    public boolean isResultCard2Displayed() { return driver.findElement(resultCard2).isDisplayed(); }
    public boolean isResultCard3Displayed() { return driver.findElement(resultCard3).isDisplayed(); }

    public boolean isNoResultsMessageDisplayed() {
        return !driver.findElements(noResultsMessage).isEmpty();
    }

    public String getNoResultsMessageText() {
        return driver.findElement(noResultsMessage).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
