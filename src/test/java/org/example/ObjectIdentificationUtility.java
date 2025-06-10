package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.fail;

public class ObjectIdentificationUtility {
    private static final Logger logger = Logger.getLogger(ObjectIdentificationUtility.class.getName());
    WebDriver driver;

    public ObjectIdentificationUtility(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getXpathForElement(String elementName) {
        logger.info("Searching for element: " + elementName);

        if(driver.findElements(By.xpath("//input[@type='submit' or @type='button'] | //*[contains(@value, '"+elementName+"')]")).size()>0){
            logger.info("XPath used: //input[@type='submit' or @type='button'] | //*[contains(@value, '"+elementName+"')]");
            return driver.findElements(By.xpath("//input[@type='submit' or @type='button'][contains(@value, '"+elementName+"')]"));
        }
        else if (driver.findElements(By.xpath("//*[@placeholder='" + elementName + "']")).size() > 0) {
            logger.info("XPath used: //*[@placeholder='" + elementName + "']");
            return driver.findElements(By.xpath("//*[@placeholder='" + elementName + "']"));
        }
        else if (driver.findElements(By.xpath("//*[contains(text(),'" + elementName + "')]/following-sibling::*")).size() > 0) {
            logger.info("XPath used: //*[contains(text(),'" + elementName + "')]/following-sibling::*");
            return driver.findElements(By.xpath("//*[contains(text(),'" + elementName + "')]/following-sibling::*"));
        }
        else if(driver.findElements(By.xpath("//button[contains(text(),'" + elementName + "')]")).size()>0){
            logger.info("XPath used: //button[contains(text(),'" + elementName + "')]");
            return driver.findElements(By.xpath("//button[contains(text(),'" + elementName + "')]"));
        }
        else if(driver.findElements(By.xpath("//*[@value='" + elementName + "']")).size()>0){
            logger.info("XPath used: //*[@value='" + elementName + "']");
            return driver.findElements(By.xpath("//*[@value='" + elementName + "']"));
        }
        else {
            if(driver.findElements(By.xpath("//*[@value='" + elementName + "'] | //*[contains(text(),'" + elementName + "')]")).size()==0) {
                logger.severe("Element with name '" + elementName + "' not found using XPath");
                fail("Element with name '" + elementName + " not found using XPath //*[@value='" + elementName + "'] | //*[contains(text(),'" + elementName + "'");
            }
            else{
                logger.info("XPath used: //*[@value='" + elementName + "'] | //*[contains(text(),'" + elementName + "')]");
                return driver.findElements(By.xpath("//*[@value='" + elementName + "'] | //*[contains(text(),'" + elementName + "')]"));
            }
        }
        return null;
    }

    public WebElement getElementByName(String elementName, String... action) {
        logger.info("Getting element by name: " + elementName);
        List<WebElement> ele = driver.findElements(By.xpath("//input[@type='submit' or @type='button'] | //*[contains(@value, '"+elementName+"')] | //*[@placeholder='" + elementName + "'] | //button[contains(text(),'" + elementName + "')] | //*[@value='" + elementName + "'] | //*[contains(@id,'" + elementName + "')] | //a[contains(text(),'" + elementName + "')]"));
        if(ele.size()>0){
            logger.info("Element found for: " + elementName);
            return ele.get(0);
        }
        else if (driver.findElements(By.xpath("//*[contains(text(),'" + elementName + "')]/following-sibling::*")).size() > 0) {
            if(action.length > 0 && action[0].equalsIgnoreCase("select")) {
                logger.info("XPath used: //*[contains(text(),'" + elementName + "')]/parent::*");
                return driver.findElements(By.xpath("//*[contains(text(),'" + elementName + "')]/parent::*")).get(0);
            }
            else{
                logger.info("XPath used: //*[contains(text(),'" + elementName + "')]/following-sibling::*");
                return driver.findElements(By.xpath("//*[contains(text(),'" + elementName + "')]/following-sibling::*")).get(0);
            }
        }
        else{
            logger.severe("Element with name '" + elementName + "' not found using XPath");
            fail("Element with name '" + elementName + "' not found using XPath");
        }
        return null;
    }

    public WebElement getElement(WebElement ele){
        logger.info("Waiting for element to be clickable");
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.elementToBeClickable(ele));
        return ele;
    }
}