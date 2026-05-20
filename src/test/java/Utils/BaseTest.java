package Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public WebDriver driver;
    public String BASE_URL = "https://awesomeqa.com/ui/index.php?route=common/home";

    // متغيرات الـ Extent Report
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static ExtentSparkReporter spark;

    @BeforeSuite
    public void setupReport() {

        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/ExtentReport.html");
        //spark = new ExtentSparkReporter("Reports/ExtentReport.html");


        spark.config().setReportName("OpenCart Automation Test Results");
        spark.config().setDocumentTitle("Execution Report");

        extent.attachReporter(spark);


        extent.setSystemInfo("Tester", "Mariam Elgandour");
        extent.setSystemInfo("Tester", "Mohamed Ahmed");
        extent.setSystemInfo("Environment", "QC");
    }

    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        test = extent.createTest(method.getName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case FAILED: " + result.getName());
            test.log(Status.FAIL, "Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case SKIPPED: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
    @AfterSuite
    public void flushReport() {
        if (extent != null) {
            extent.flush();


            try {
                java.io.File reportFile = new java.io.File(System.getProperty("user.dir") + "/Reports/ExtentReport.html");
                if (reportFile.exists()) {
                    java.awt.Desktop.getDesktop().browse(reportFile.toURI());
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}