package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopupFormDemoPage extends BasePage {
    public PopupFormDemoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Create']")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@id='Menu']")
    private WebElement menu;

    public void clickOnCreateBtn() {
        clickOnElement(createBtn);
    }
}
