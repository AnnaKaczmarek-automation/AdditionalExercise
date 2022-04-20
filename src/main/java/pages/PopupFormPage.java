package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PopupFormPage extends BasePage {
    public PopupFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='createDinnerName-awed']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='createDinnerDate']")
    private WebElement dateInput;

    @FindBy(xpath = "//div[@class='awe-lookup-field awe-field']/div[contains(@class,'awe-elcont')]/button[@class='awe-btn awe-openbtn awe-lkpbtn']")
    private WebElement searchChefBtn;

    @FindBy(xpath = "//div[@class='awe-multilookup-field awe-field']/div[contains(@class,'awe-elcont')]/button[@class='awe-btn awe-openbtn awe-lkpbtn']")
    private WebElement searchMealBtn;

    @FindBy(xpath = "//button[@id='createDinnerBonusMealId-awed']/div[@class='o-slbtn']/i[@class='o-caret']")
    private WebElement bonusMealBtn;

    @FindBy(xpath = "//button[@class='awe-btn o-cmbtn o-mnxt']")
    private WebElement nextButton;

    @FindBy(xpath = "//button[@class='awe-btn o-cmbtn o-mprv']")
    private WebElement previousButton;

    @FindBy(xpath = "//button[@id='createDinnerDatecy-awed']/div[@class='o-cptn']")
    private WebElement displayedYear;

    @FindBy(xpath = "//button[@id='createDinnerDatecm-awed']/div[@class='o-cptn']")
    private WebElement displayedMonth;

    @FindBy(css = "tr:nth-child(2), tr:nth-child(3), tr:nth-child(4), tr:nth-child(5), tr:nth-child(6), tr:nth-child(7)")
    private List<WebElement> listOfWeeks;

    @FindBy(xpath = "//td[contains(@class,'o-day o-mday ')]")
    private List<WebElement> listOfDays;

    @FindBy(xpath = "//td[@class='o-day o-mday o-enb']")
    private WebElement days;

    @FindBy(xpath = "//button[@id='createDinnerDatecy-awed']/div[@class='o-slbtn']/i[@class='o-caret']")
    private WebElement yearMenu;

    @FindBy(xpath = "//button[@id='createDinnerDatecm-awed']/div[@class='o-slbtn']/i[@class='o-caret']")
    private WebElement monthMenu;

    @FindBy(xpath = "//div[@class='o-pmc o-pu o-dtpp']")
    private WebElement datePicker;

    @FindBy(xpath = "//div[@id='createDinnerChef-awepw']")
    private WebElement chefOptions;

    @FindBy(css = "ul li[class=awe-li]")
    private List<WebElement> chefsList;

    @FindBy(xpath = "//div[@data-i='createDinnerChef-awepw']/div[@class='o-pbtns']/button[text()='OK']")
    private WebElement chefsOkBtn;

    @FindBy(xpath = "//div[@class='awe-display']/div[@class='awe-caption']")
    private WebElement chefNameField;

    @FindBy(xpath = "//ul[@class='awe-ajaxlist awe-lookup-list awe-srl']/li[@class='awe-li']")
    private List<WebElement> ingredientsList;

    @FindBy(xpath = "//ul[@class='awe-ajaxlist awe-lookup-list awe-srl']/li[@class='awe-li awe-morecont']/div[@class='awe-morebtn']")
    private WebElement moreBtn;

    @FindBy(xpath = "//div[@class='awe-list awe-selcont']")
    private WebElement boxForMeals;

    @FindBy(xpath = "//div[@data-i='createDinnerMeals-awepw']/div[@class='o-pbtns']/button[text()='OK']")
    private WebElement mealsOkBtn;

    @FindBy(xpath = "//button[@id='createDinnerBonusMealId-awed']/div[@class='o-slbtn']/i[@class='o-caret']")
    private WebElement openingBonusMealBtn;

    @FindBy(xpath = "//div[@class='o-menu o-pu open']/div[@class='o-itsc']")
    private WebElement bonusMealOptions;

    @FindBy(xpath = "//li[@class='o-itm o-v o-ditm']")
    private List<WebElement> bonusMealsList;

    @FindBy(xpath = "//div[@data-i='createDinner']/div[@class='o-pbtns']/button[@class='awe-btn awe-okbtn o-pbtn']")
    private WebElement mainOkBtn;

    @FindBy(xpath = "//ul[@class='awe-ajaxlist awe-lookup-list awe-srl']/li[@class='awe-li']/button[@class='awe-movebtn awe-btn']")
    private List<WebElement> ingredientArrowsList;


    public void setName(String firstName) {
        waitForVisibility(nameInput);
        highlightElements(nameInput);
        nameInput.sendKeys(firstName);
    }

    public void openDataPicker() {
        waitForVisibility(dateInput);
        clickOnElement(dateInput);
    }

    public void chooseDate(String year, String month, String day) {
        openDataPicker();
        findYearInCalendar(year);
        findMonthInCalendar(month);
        findDayInCalendar(day);
    }

    public void findYearInCalendar(String year) {
        String displayedYearText = displayedYear.getText();
        yearMenu.click();
        WebElement expectedYear = driver.findElement(By.xpath("//li[@class='o-itm o-v o-ditm'][text()='" + year + "']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", expectedYear);
        waitForVisibility(expectedYear);
        clickOnElement(expectedYear);
    }

    public void findMonthInCalendar(String month) {
        clickOnElement(monthMenu);
        WebElement expectedMonth = driver.findElement(By.xpath("//li[contains(@class,'o-itm o-v ')][text()='" + month + "']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(expectedMonth).perform();
        clickOnElement(expectedMonth);
    }

    public void findDayInCalendar(String day) {
        for (WebElement dayOnTheList : listOfDays) {
            if (dayOnTheList.getText().equals(day)) {
                waitUntilElementIsClickable(dayOnTheList);
                clickOnElement(dayOnTheList);
                break;

            }
        }
    }

    public void chooseChef() {
        waitUntilElementIsClickable(searchChefBtn);
        clickOnElement(searchChefBtn);
        waitForVisibility(chefOptions);
        waitForVisibilityOfElements(chefsList);
        waitForVisibility(chefsList.get(getRandomListIndex(chefsList)));
        WebElement expectedChef = chefsList.get(getRandomListIndex(chefsList));
        waitForVisibility(expectedChef);
        String expectedValue = expectedChef.getText();
        expectedChef.click();
        clickOnElement(chefsOkBtn);
        wait.until(ExpectedConditions.textToBePresentInElement(chefNameField, expectedValue));
        String actualValue = getWebElementText(chefNameField);
        verifyIfElementsEquals(expectedValue, actualValue);
    }

    public void chooseMeals() {
        waitUntilElementIsClickable(searchMealBtn);
        clickOnElement(searchMealBtn);
        waitForVisibilityOfElements(ingredientsList);
        int i = 0;
        while (i < 3) {

            int randomIndex = getRandomListIndex(ingredientsList);
            log.info(randomIndex + " is random index");

            if(ingredientsList.get(randomIndex).isDisplayed()){
                clickOnElement(ingredientArrowsList.get(randomIndex));
                log.info(ingredientsList.get(randomIndex).getText() + " was chosen");
                List<WebElement> ingredientsList = driver.findElements(By.xpath("//ul[@class='awe-ajaxlist awe-lookup-list awe-srl']/li[@class='awe-li']"));
            }

            if (!ingredientsList.get(randomIndex).isDisplayed()) {
                clickOnElement(moreBtn);
                waitForVisibility(ingredientsList.get(randomIndex));
                clickOnElement(ingredientArrowsList.get(randomIndex));
            }
            log.info("size of list is: " + ingredientsList.size());
            List<WebElement> ingredientsList = driver.findElements(By.xpath("//ul[@class='awe-ajaxlist awe-lookup-list awe-srl']/li[@class='awe-li']"));
            i++;
        }
        waitUntilElementIsClickable(mealsOkBtn);
        clickOnElement(mealsOkBtn);
    }

    public void chooseBonusMeal() {
        waitForVisibility(openingBonusMealBtn);
        clickOnElement(openingBonusMealBtn);
        waitForVisibility(bonusMealOptions);
        WebElement randomBonusMeal = bonusMealsList.get(getRandomListIndex(bonusMealsList));
        clickOnElement(randomBonusMeal);
        waitForVisibility(mainOkBtn);
        clickOnElement(mainOkBtn);
    }

    public void verifyPopupText() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualText = getTextFromAlert();
        String expectedText = System.getProperty("alertText");
        verifyIfElementsEquals(expectedText, actualText);
        acceptAlert();
    }
}
