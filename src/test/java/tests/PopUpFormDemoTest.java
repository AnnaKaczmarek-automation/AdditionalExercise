package tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.PopupFormDemoPage;
import pages.PopupFormPage;

public class PopUpFormDemoTest extends TestBase {
    HomePage homePage;
    PopupFormDemoPage popupFormDemoPage;
    PopupFormPage popupFormPage;
    Logger log = LoggerFactory.getLogger("PopUpFormDemoTest");

    @Test
    public void fillingPopupFormTest(){
        homePage = new HomePage(driver);
        popupFormDemoPage = new PopupFormDemoPage(driver);
        popupFormPage = new PopupFormPage(driver);

        homePage.closeCookiesPopup();
        log.info("Cookies popup is closed");
        homePage.clickOnPopupFormBtn();
        log.info("PopupForm button is chosen");
        popupFormDemoPage.clickOnCreateBtn();
        log.info("Create button button is chosen");
        popupFormPage.setName(System.getProperty("firstName"));
        log.info("Name is set Name field");
        popupFormPage.chooseDate(System.getProperty("year"), System.getProperty("month"), System.getProperty("day"));
        log.info("Date is chosen");
        popupFormPage.chooseChef();
        log.info("Chef option is chosen");
        popupFormPage.chooseMeals();
        log.info("Meals ale chosen");
        popupFormPage.chooseBonusMeal();
        log.info("Bonus meal is chosen");
        popupFormPage.verifyPopupText();
        log.info("Popup message is correct");
    }
}
