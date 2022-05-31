package academy.tests;

import academy.pageObjects.LandingPage;
import academy.resources.Base;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Listeners(academy.resources.Listeners.class)
public class ValidateTitle extends Base {

    private WebDriver driver;
    private LandingPage landingPage;
    static WebDriverWait wait;
    static Logger log ;


    @BeforeTest
    public void initialize() throws IOException {
        log = initializeLogger(ValidateTitle.class);
        PropertyConfigurator.configure("src/main/java/academy/resources/log4j.properties");
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(properties.getProperty("url"));
        log.info("Navigated to HomePage");
        wait = new WebDriverWait(driver, 10);
        landingPage =  new LandingPage(driver);
    }

    @Test
    public void validateAppTitle() {
//        driver.get(properties.getProperty("url"));
        //String title = landingPage.getTitle().getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='text-center'] h2")));
        String title = landingPage.getTitle().getText();
        //compare the text from the browser with actual text
//        wait.until(ExpectedConditions.elementToBeClickable(landingPage.getPopup())).click();
        Assert.assertEquals(title, "FEATURED COURSES");

        List<String> links =
                landingPage.getNavigationBar().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        for(int i = 0; i < landingPage.getNavigationBar().size(); i++) {
            Assert.assertEquals(
                    landingPage.getNavigationBar()
                    .get(i)
                    .getText(),links.get(i));
        }
        for(WebElement name : landingPage.getNavigationBar()) {
            Assert.assertTrue(name.isDisplayed());
        }
        log.info("Successfully validated Text message");

    }

    @Test
    public void validateHeader() {
        String text = landingPage.getHeaderTitle().getText();
        Assert.assertEquals(text, "Learn Hot tools like Selenium, Appium, JMeter, SoapUI,Database Testing and more.." );
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver = null;
    }





}
