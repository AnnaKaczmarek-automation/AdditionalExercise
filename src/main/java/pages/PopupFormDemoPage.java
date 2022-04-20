package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopupFormDemoPage extends BasePage {

    @FindBy(xpath = "//button[text()='Create']")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@id='Menu']")
    private WebElement menu;

    public PopupFormDemoPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreateBtn() {
        waitForVisibility(createBtn);
        clickOnElement(createBtn);
    }
}
