package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[text()='First Name']/following-sibling ::*")
    private WebElement usernameField;

    @FindBy(xpath="//*[text()='Last Name']/following-sibling ::*")
    private WebElement passwordField;

    @FindBy(xpath="//*[text()='Address']/following-sibling ::*")
    private WebElement address;

    public HomePage login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(address));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)","");
        js.executeScript("arguments[0].scrollIntoView(true);", address);
        address.click();
        return PageFactory.initElements(driver, HomePage.class); // Assuming HomePage is another page object class
    }

    public void takeScreenshot(String path) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File(path);
        WebElement ele = driver.findElement(By.xpath("//*[text()='First Name']/following-sibling ::*"));
        String scr1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    public void enterFields(){
        usernameField.sendKeys("Username");
        passwordField.sendKeys("Password");
        address.sendKeys("Test");
    }

}
