package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class WebDriverTest {

    WebDriver driver;

    @Before
    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        takeScreenshot("D://screen1.jpg");// Initialize the WebDriver instance here
        // For example, using ChromeDriver:
        // driver = new ChromeDriver();
    }

    @Test
    public void testWebDriver() throws Exception {
        driver.get("https://www.example.com");
        // Add your test logic here
        // For example, you can assert the title of the page:
        String title = driver.getTitle();
        assertEquals("Example Domain", title);
        takeScreenshot("D://screen2.jpg"); // Take a screenshot after loading the page
        Thread.sleep(3000);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and end the session
        }
    }


    public void takeScreenshot(String path) {
        File src  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File file = new File(path);
        try{
            FileUtils.copyFile(src, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
