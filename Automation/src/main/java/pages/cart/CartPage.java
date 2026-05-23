package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class CartPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By textInput        = By.id("input-option208");
    private final By selectDropdown   = By.id("input-option217");
    private final By textAreaInput    = By.id("input-option209");
    private final By dateField        = By.id("input-option219");
    private final By timeField        = By.id("input-option221");
    private final By dateTimeField    = By.id("input-option220");
    private final By quantityField    = By.id("input-quantity");
    private final By addToCartButton  = By.id("button-cart");
    private final By successAlert     = By.cssSelector("#product-product > div.alert.alert-success.alert-dismissible");
    private final By removeProductBtn = By.xpath("//button[@data-original-title='Remove']");
    private final By emptyCartContent = By.xpath("//div[@id='content']/p");

    // ── Constructor ──────────────────────────────────────────────────────────
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
    }

    // ── Actions ───────────────────────────────────────────────────────────────
    public void fillAllRequiredOptions(String radioValue, String checkboxValue, String selectText,
                                       String text, String textareaText, String fileName,
                                       String date, String time, String dateTime, String qty) {

        // Radio
        By dynamicRadio = By.xpath("//input[@type='radio' and @value='" + radioValue + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dynamicRadio)).click();

        // Checkbox
        By dynamicCheckbox = By.xpath("//input[@type='checkbox' and @value='" + checkboxValue + "']");
        WebElement checkbox = driver.findElement(dynamicCheckbox);
        if (!checkbox.isSelected()) checkbox.click();

        // Text input
        WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(textInput));
        textField.clear();
        textField.sendKeys(text);

        // Select dropdown
        Select select = new Select(driver.findElement(selectDropdown));
        switch (selectText.toLowerCase()) {
            case "blue"   -> select.selectByVisibleText("Blue (+$3.60)");
            case "green"  -> select.selectByVisibleText("Green (+$1.20)");
            case "yellow" -> select.selectByVisibleText("Yellow (+$2.40)");
            default       -> select.selectByIndex(1);
        }

        // Textarea
        WebElement textArea = driver.findElement(textAreaInput);
        textArea.clear();
        textArea.sendKeys(textareaText);

        // File upload
        File tempFile = new File(fileName);
        By uploadButtonLocator = By.xpath("//button[contains(text(),'Upload') or contains(text(),'Choose File')]");
        wait.until(ExpectedConditions.elementToBeClickable(uploadButtonLocator)).click();

        try { tempFile.createNewFile(); } catch (Exception e) { e.printStackTrace(); }
        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(tempFile.getAbsolutePath());

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // Date / Time / DateTime
        fillAndClear(dateField, date);
        fillAndClear(timeField, time);
        fillAndClear(dateTimeField, dateTime);

        // Quantity
        WebElement qtyInput = driver.findElement(quantityField);
        qtyInput.clear();
        qtyInput.sendKeys(qty);
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void removeProductFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeProductBtn)).click();
    }

    // ── Assertions helpers ────────────────────────────────────────────────────
    public String getSuccessMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert)).getText();
    }

    public String getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartContent)).getText();
    }

    // ── Private helpers ───────────────────────────────────────────────────────
    private void fillAndClear(By locator, String value) {
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(value);
    }
}
