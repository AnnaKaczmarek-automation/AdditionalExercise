package pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage{

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;


    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        actions = new Actions(driver);
    }




    //    private static  PopupFormDemoPage popupFormDemoPage = new PopupFormDemoPage();
    Logger log = LoggerFactory.getLogger("BasePage.class");


    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void waitForVisibility(WebElement element) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsClickable(WebElement element) {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void scrollUntilVisible(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!isDisplayed(element)) {
            js.executeScript("window.scrollBy(0,250)");
            log.info("Page is scrolled down by 250px");
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForInvisibilityOfElement(WebElement element) {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public String getWebElementText(WebElement element) {

        String value = element.getText();
        return value;
    }

    public void verifyIfElementsEquals(String expected, String actual) {
        Assert.assertEquals(expected, actual);
    }

    public void highlightElements(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 5px solid red;')");
        try{
            Thread.sleep(1500);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
