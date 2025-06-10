package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

// ... other imports

public class AppTest {

    private static final Logger logger = Logger.getLogger(AppTest.class.getName());
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    public String captureScreenshot(String stepName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./screenshots/" + stepName + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(path);
        dest.getParentFile().mkdirs();
        FileUtils.copyFile(src, dest);
        return path;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.setCapability("credentials_enable_service", false);
        options.setCapability("profile.password_manager_enabled", false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }
    @Test
    public void testFields() throws Exception {
        test = extent.createTest("SauceDemo Test 1");
        ObjectIdentificationUtility objectIdentificationUtility = new ObjectIdentificationUtility(driver);

        test.info("Entering Username", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("username")).build());
        objectIdentificationUtility.getElementByName("Username").sendKeys("standard_user");

        test.info("Entering Password", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("password")).build());
        objectIdentificationUtility.getElementByName("Password").sendKeys("secret_sauce");

        test.info("Clicking SUBMIT", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("submit")).build());
        objectIdentificationUtility.getElementByName("SUBMIT").click();

        // Repeat for each step...
        // Example for "Selecting Name (A to Z)"
        test.info("Selecting Name (A to Z)", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("select_name")).build());
        new Select(objectIdentificationUtility.getElementByName("Name (A to Z)", "select")).selectByIndex(2);

        test.info("Clicking ADD TO CART for Sauce Labs Backpack", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("add_to_cart_backpack")).build());
        objectIdentificationUtility.getElementByName("ADD TO CART").click();

        Actions builder = new Actions(driver);
        test.info("Move to shopping cart and click", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("shopping_cart")).build());
        builder.moveToElement(objectIdentificationUtility.getElementByName("shopping_cart")).click().build().perform();

        test.info("Clicking CHECKOUT", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("checkout")).build());
        objectIdentificationUtility.getElementByName("CHECKOUT").click();

        test.info("Entering First Name", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("first_name")).build());
        objectIdentificationUtility.getElementByName("First Name").sendKeys("John");

        test.info("Entering Last Name", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("last_name")).build());
        objectIdentificationUtility.getElementByName("Last Name").sendKeys("Doe");

        test.info("Entering Postal Code", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("postal_code")).build());
        objectIdentificationUtility.getElementByName("Zip/Postal Code").sendKeys("12345");

        test.info("Clicking CONTINUE", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("continue")).build());
        objectIdentificationUtility.getElementByName("CONTINUE").click();

        test.info("Verifying item in cart", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("verify_cart")).build());
        objectIdentificationUtility.getElementByName("FINISH").click();


        test.info("Verifying order completion", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("order_complete")).build());
        assertTrue(driver.getPageSource().contains("THANK YOU FOR YOUR ORDER"));

        driver.close();
        driver.quit();
        extent.flush();
    }

    @Test
    public void testSecond() throws Exception {

        test = extent.createTest("SauceDemo Test 2");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().window().maximize();

        ObjectIdentificationUtility objectIdentificationUtility = new ObjectIdentificationUtility(driver);

        test.info("Entering Username", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("username")).build());
        objectIdentificationUtility.getElementByName("Username").sendKeys("standard_user");

        test.info("Entering Password", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("password")).build());
        objectIdentificationUtility.getElementByName("Password").sendKeys("secret_sauce");

        test.info("Clicking SUBMIT", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("submit")).build());
        objectIdentificationUtility.getElementByName("SUBMIT").click();

        // Repeat for each step...
        // Example for "Selecting Name (A to Z)"
        test.info("Selecting Name (A to Z)", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("select_name")).build());
        new Select(objectIdentificationUtility.getElementByName("Name (A to Z)", "select")).selectByIndex(2);

        test.info("Clicking ADD TO CART for Sauce Labs Backpack", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("add_to_cart_backpack")).build());
        objectIdentificationUtility.getElementByName("ADD TO CART").click();

        Actions builder = new Actions(driver);
        test.info("Move to shopping cart and click", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("shopping_cart")).build());
        builder.moveToElement(objectIdentificationUtility.getElementByName("shopping_cart")).click().build().perform();

        test.info("Clicking CHECKOUT", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("checkout")).build());
        objectIdentificationUtility.getElementByName("CHECKOUT").click();

        test.info("Entering First Name", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("first_name")).build());
        objectIdentificationUtility.getElementByName("First Name").sendKeys("John");

        test.info("Entering Last Name", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("last_name")).build());
        objectIdentificationUtility.getElementByName("Last Name").sendKeys("Doe");

        test.info("Entering Postal Code", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("postal_code")).build());
        objectIdentificationUtility.getElementByName("Zip/Postal Code").sendKeys("12345");

        test.info("Clicking CONTINUE", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("continue")).build());
        objectIdentificationUtility.getElementByName("CONTINUE").click();

        test.info("Verifying item in cart", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("verify_cart")).build());
        objectIdentificationUtility.getElementByName("FINISH").click();


        test.info("Verifying order completion", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("order_complete")).build());
        assertTrue(driver.getPageSource().contains("THANK YOU FOR YOUR ORDER"));

        driver.close();
        driver.quit();
        extent.flush();
    }
}