package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    public WebDriver     driver;
    public WebDriverWait wait;

    protected static ExtentReports       extent;
    protected static ExtentTest          test;
    protected static ExtentSparkReporter spark;

    @BeforeSuite
    public void setupReport() {
        new java.io.File(System.getProperty("user.dir") + "/Reports").mkdirs();

        extent = new ExtentReports();
        spark  = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/Reports/ExtentReport.html");

        spark.config().setReportName("OpenCart Automation Test Results");
        spark.config().setDocumentTitle("Execution Report");
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester 1",    "Mariam Elgandour");
        extent.setSystemInfo("Tester 2",    "Mohamed Ahmed");
        extent.setSystemInfo("Environment", "QA");
    }

    @BeforeMethod
    public void setUp(Method method) {
        if (extent == null) setupReport();

        test   = extent.createTest(method.getName());
        driver = new ChromeDriver();
        wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE -> test.log(Status.FAIL,
                    "FAILED: " + result.getName() + "\n" + result.getThrowable());
            case ITestResult.SUCCESS -> test.log(Status.PASS,
                    "PASSED: " + result.getName());
            case ITestResult.SKIP    -> test.log(Status.SKIP,
                    "SKIPPED: " + result.getName());
        }
        if (extent != null) extent.flush();
        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        if (extent != null) extent.flush();
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}