package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Month;
import java.util.List;

public class PopupFormPage extends BasePage {

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

    public PopupFormPage(WebDriver driver) {
        super(driver);
    }


    public void setName(String firstName) {
        waitForVisibility(nameInput);
        nameInput.sendKeys(firstName);
    }

    public void openDataPicker() {
        waitForVisibility(dateInput);
        clickOnElement(dateInput);
    }

    public void clickNextButton() {
        nextButton.click();
        log.info("'Next' button is chosen");
    }

    public void clickPreviousButton() {
        waitForVisibility(previousButton);
        clickOnElement(previousButton);
    }

    public void chooseDate(String year, String month, String day) {
        openDataPicker();
        findYearInCalendar(year);
        findMonthInCalendar(month);
        findDayInCalendar(day);
    }

    public void findYearInCalendar(String year) {
        String displayedYearText = displayedYear.getText();
        log.info(String.valueOf(displayedYearText));

        yearMenu.click();
        WebElement expectedYear = driver.findElement(By.xpath("//li[@class='o-itm o-v o-ditm'][text()='" + year + "']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", expectedYear);
        waitForVisibility(expectedYear);
        clickOnElement(expectedYear);
    }

    public void findMonthInCalendar(String month) {
        String displayedMonthText = displayedMonth.getText();
//        int integerDisplayedMonthValue = changeMonthValuesIntoInteger(displayedMonthText);
        monthMenu.click();

        WebElement expectedMonth = driver.findElement(By.xpath("//li[contains(@class,'o-itm o-v o-ditm')][text()='" + month + "']"));

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView();", month);

        Actions actions = new Actions(driver);
        actions.moveToElement(expectedMonth).perform();
        waitForVisibility(expectedMonth);
        clickOnElement(expectedMonth);
    }


    public void waitUntilDataPickerIsClosed() {
        waitForInvisibilityOfElement(datePicker);
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

        int i = 0;
        while (i < 3) {
            waitForVisibilityOfElements(ingredientsList);
            int randomIndex = getRandomListIndex(ingredientsList);
            WebElement ingredient = ingredientsList.get(randomIndex);
            if (ingredient.isDisplayed()) {
                actions.dragAndDrop(ingredient,boxForMeals );
                log.info(ingredient.getText() +" was chosen");
            } else {
                waitForVisibility(moreBtn);
                clickOnElement(moreBtn);
                waitForVisibility(ingredient);
                actions.dragAndDrop(ingredient,boxForMeals );
                log.info(ingredient.getText() +" was chosen");
            }
            i++;
        }
        waitUntilElementIsClickable(mealsOkBtn);
        clickOnElement(mealsOkBtn);
    }

    public void chooseBonusMeal(){
        waitForVisibility(openingBonusMealBtn);
        clickOnElement(openingBonusMealBtn);
        waitForVisibility(bonusMealOptions);
        WebElement randomBonusMeal = bonusMealsList.get(getRandomListIndex(bonusMealsList));
        clickOnElement(randomBonusMeal);
        waitForVisibility(mainOkBtn);
        clickOnElement(mainOkBtn);
    }

   public void verifyPopupText(){
       String actualText = getTextFromAlert();
       String expectedText = System.getProperty("alertText");
       verifyIfElementsEquals(expectedText, actualText);
       acceptAlert();

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


    public void findDayInCalendar(String day) {
        for (WebElement week : listOfWeeks) {
            List<WebElement> allDaysInWeek = week.findElements(By.xpath("//td[contains(@class,'o-day o-mday ')]"));
            for (WebElement dayOnTheList : allDaysInWeek) {
                String dayValue = dayOnTheList.getText();
                System.out.println(dayValue);
                if (dayValue.equals(day)) {
                    waitForVisibility(dayOnTheList);
                    System.out.println("element is visible");
                    waitUntilElementIsClickable(dayOnTheList);
                    System.out.println("element is clickable");
                    dayOnTheList.click();
                    driver.navigate().refresh();
                    try {
                        dayOnTheList.click();
                    } catch (StaleElementReferenceException e) {
                        List<WebElement> allDaysInWeekRefreshed = week.findElements(By.xpath("//td[contains(@class,'o-day o-mday ')]"));
                        for (WebElement dayOnTheListRefreshed : allDaysInWeekRefreshed) {
                            String dayValueRefreshed = dayOnTheListRefreshed.getText();
                            if (dayValueRefreshed.equals(day)) {
                                waitUntilElementIsClickable(dayOnTheList);
                                dayOnTheList.click();
                            }

                        }
                    }
                }
            }
        }
    }
}