package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='PopupForm']")
    private WebElement popupFormBtn;
    @FindBy(xpath = "//div[@id='cookieMsg']")
    private WebElement cookieMsg;

    @FindBy(xpath = "//button[@id='btnCookie']")
    private WebElement okBtn;

    @FindBy(xpath = "//div[@class='awe-itc']/ a")
    private List<WebElement> menuList;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void clickOnPopupFormBtn() throws InterruptedException {

        scrollUntilVisible(driver, popupFormBtn);
        Thread.sleep(5000);
        clickOnElement(popupFormBtn);
    }

    public void closeCookiesPopup() {
        waitUntilElementIsClickable(cookieMsg);
        clickOnElement(okBtn);
        waitForInvisibilityOfElement(cookieMsg);
    }
}
