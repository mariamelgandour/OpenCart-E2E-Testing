package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;

    private final By guestCheckbox              = By.xpath("//input[@value='guest']");
    private final By checkoutOptionsContinueBtn = By.id("button-account");
    private final By firstNameField             = By.id("input-payment-firstname");
    private final By lastNameField              = By.id("input-payment-lastname");
    private final By emailField                 = By.id("input-payment-email");
    private final By telephoneField             = By.id("input-payment-telephone");
    private final By addressField               = By.id("input-payment-address-1");
    private final By cityField                  = By.id("input-payment-city");
    private final By postCodeField              = By.id("input-payment-postcode");
    private final By countryDropdown            = By.id("input-payment-country");
    private final By regionDropdown             = By.id("input-payment-zone");
    private final By billingContinueBtn         = By.id("button-guest");
    private final By shippingSection            = By.id("collapse-shipping-method");
    private final By deliveryMethodContinueBtn  = By.id("button-shipping-method");
    private final By termsCheckbox              = By.name("agree");
    private final By paymentMethodContinueBtn   = By.id("button-payment-method");
    private final By confirmOrderBtn            = By.id("button-confirm");
    private final By successHeader              = By.xpath("//h1[text()='Your order has been placed!']");
    private final By successText                = By.xpath("//p[contains(text(),'Your order has been successfully processed!')]");
    private final By termsWarningAlert          = By.cssSelector(".alert-danger");
    private final By quantityInput              = By.xpath("//div[@class='input-group btn-block']/input");
    private final By updateBtn                  = By.xpath("//button[@data-original-title='Update']");
    private final By stockWarningAlert          = By.cssSelector(".alert-danger");
    private final By checkoutBtn                = By.linkText("Checkout");
    private final By shoppingCartLink           = By.linkText("Shopping Cart");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
    }

    public void selectGuestCheckoutAndContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(guestCheckbox)).click();
        driver.findElement(checkoutOptionsContinueBtn).click();
    }

    public void fillBillingDetails(String fName, String lName, String email, String phone,
                                   String address, String city, String zip,
                                   String countryName, String regionName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(fName);
        driver.findElement(lastNameField).sendKeys(lName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(telephoneField).sendKeys(phone);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(postCodeField).sendKeys(zip);

        // ✅ FIX: انتظر الـ country dropdown يبقى enabled الأول
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(countryName);

        // ✅ FIX: انتظر الـ region يتحمل عن طريق innerHTML
        wait.until(drv -> {
            WebElement dropdown = drv.findElement(regionDropdown);
            return dropdown.getAttribute("innerHTML").contains(regionName);
        });

        new Select(driver.findElement(regionDropdown)).selectByVisibleText(regionName);

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingSection));
    }

    public void completeCheckoutSteps() {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryMethodContinueBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
        driver.findElement(paymentMethodContinueBtn).click();
    }

    public void clickConfirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
    }

    public void deliveryMethodAgreement() {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryMethodContinueBtn)).click();
    }

    public void clickPaymentMethodContinueWithoutAgreeing() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
    }

    public void updateProductQuantity(String qty) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput)).clear();
        driver.findElement(quantityInput).sendKeys(qty);
        driver.findElement(updateBtn).click();
    }

    public void goToShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
    }

    public void clickOnCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public boolean isOrderHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader)).isDisplayed();
    }

    public boolean isOrderTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successText)).isDisplayed();
    }

    public String getTermsWarningText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(termsWarningAlert)).getText();
    }

    public String getStockWarningText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(stockWarningAlert)).getText();
    }
}