package tests;

import configuration.BrowserEnvironment;
import configuration.EnvironmentProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class TestBase{
    protected static WebDriver driver;
    private static Logger log = LoggerFactory.getLogger("TestBase.class");
    protected static BrowserEnvironment browserEnvironment;
    protected static EnvironmentProperty environmentProperty;
    protected static BasePage basePage;


    @BeforeAll
    static void beforeAll() {
        environmentProperty = EnvironmentProperty.getInstance();
        log.info("***** Properties are set to system *****");
        browserEnvironment = new BrowserEnvironment();
        log.info("***** Browser properties are set to the system *****");
    }

    @BeforeEach
    void beforeEach(){
        driver = browserEnvironment.getDriver();
        basePage = new BasePage(driver);
    }


    @AfterAll
    static void tearDown() {
        driver.quit();
        log.info("***** Driver closed *****");
    }
}
