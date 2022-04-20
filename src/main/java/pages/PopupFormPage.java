package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Month;
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
        if (StringUtils.isAlpha(firstName)) {
            nameInput.sendKeys(firstName);
        } else {
            log.info("Name input must be a string. Change requirements.");
            driver.quit();
        }

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
//        String displayedYearText = displayedYear.getText();
        yearMenu.click();

        if (Integer.parseInt(year) > 2006 && Integer.parseInt(year) < 2036) {
            WebElement expectedYear = driver.findElement(By.xpath("//li[@class='o-itm o-v o-ditm'][text()='" + year + "']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", expectedYear);
            clickOnElement(expectedYear);
        } else {
            log.info("Menu does not support years before 2006 and after 2036. Change requirements");
            driver.quit();
        }

    }

    public void findMonthInCalendar(String month) {
        clickOnElement(monthMenu);
        int desiredMonth = changeMonthValuesIntoInteger(month);
        if (desiredMonth > 0 && desiredMonth < 13) {
            WebElement expectedMonth = driver.findElement(By.xpath("//li[contains(@class,'o-itm o-v ')][text()='" + month + "']"));
//            String expectedMonthText = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
            Actions actions = new Actions(driver);
            actions.moveToElement(expectedMonth).perform();
            clickOnElement(expectedMonth);
        } else {
            log.info("There is no such month in calendar. Change requirements.");
            driver.quit();
        }
    }

    public void findDayInCalendar(String day) {
        if (Integer.parseInt(day) > 1 && Integer.parseInt(day) < 31)
            for (WebElement dayOnTheList : listOfDays) {
                if (dayOnTheList.getText().equals(day)) {
                    waitUntilElementIsClickable(dayOnTheList);
                    clickOnElement(dayOnTheList);
                    break;

                }
            }
        else {
            log.info("There is no such day in calendar. Change requirements");
            driver.quit();
        }
    }


    public Integer changeMonthValuesIntoInteger(String month) {
        int monthInt = 0;
        switch (month) {
            case "January":
                monthInt = Month.JANUARY.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "February":
                monthInt = Month.FEBRUARY.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "March":
                monthInt = Month.MARCH.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "April":
                monthInt = Month.APRIL.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "May":
                monthInt = Month.MAY.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "June":
                monthInt = Month.JUNE.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "July":
                monthInt = Month.JULY.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "August":
                monthInt = Month.AUGUST.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "September":
                monthInt = Month.SEPTEMBER.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "October":
                monthInt = Month.OCTOBER.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "November":
                monthInt = Month.NOVEMBER.getValue();
                log.info("value of month is " + monthInt);
                break;

            case "December":
                monthInt = Month.DECEMBER.getValue();
                log.info("value of month is " + monthInt);
                break;

            default:
                log.info("Wrong month name was provided");
        }
        return monthInt;
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

            if (ingredientsList.get(randomIndex).isDisplayed()) {
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
