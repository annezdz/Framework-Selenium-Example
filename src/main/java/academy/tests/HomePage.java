package academy.tests;

import academy.pageObjects.ForgotPasswordPage;
import academy.pageObjects.LandingPage;
import academy.pageObjects.LoginTestPage;
import academy.resources.Base;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;


@Listeners(academy.resources.Listeners.class)
public class HomePage extends Base {

    private WebDriver driver;
    static WebDriverWait wait;
    static Logger log;
    private LandingPage landingPage;


    @BeforeTest
    public void initialize() throws IOException {
        log = initializeLogger(HomePage.class);
        PropertyConfigurator.configure("C:\\Users\\anicolle\\eclipse-workspace\\FrameworkProject\\FrameworkProject\\src\\main\\java\\academy\\resources\\log4j.properties");
        driver = initializeDriver();
        log.info("Driver is initialized");
        wait = new WebDriverWait(driver, 15);

    }

    @Test(dataProvider = "getData")
    public void basePageNavigation(String username, String password, String text) throws Exception {
        driver.get(properties.getProperty("url"));
        log.info("Navigated to HomePage");
        landingPage = new LandingPage(driver);
//
//     ***   ((JavascriptExecutor) driver).executeScript("arguments[0].click()", landingPage.getLogin());

//            landingPage.getLogin().click();
//            driver.navigate().to("https://rahulshettyacademy.com/sign_in/");

//        wait.until(ExpectedConditions.elementToBeClickable(landingPage.getLogin())).click();
//        landingPage.getLogin().click();
        //landingPage.getLogin().click();

//            landingPage.getPopup().click();
//            landingPage.getLogin().click();


//        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", landingPage.getPopup());
//        landingPage.getPopup().click();

       //*** LoginPage loginPage = new LoginPage(driver);
        LoginTestPage loginPage = landingPage.getLogin();
        loginPage.getUser().sendKeys(username);
        loginPage.getPassword().sendKeys(password);
//        System.out.println(text);
        log.info(text);
        loginPage.getLoginButton().click();
        log.info("Login Successfully");
        ForgotPasswordPage forgotPasswordPage = loginPage.getForgotPassword();
        forgotPasswordPage.getEmail().sendKeys("xxx");
        forgotPasswordPage.buttonSend().click();
    }

    @DataProvider
    public Object[][] getData() {
        //Row stands for how many different data types test should run
        //column stands for how many values per each test
        Object[][] data = new Object[2][3];
        //0th row
        data[0][0] = "nonrestricteduser@qw.com";
        data[0][1] = "123456";
        data[0][2] = "Restricted User";

        //1th row
        data[1][0] = "restricteduser@qw.com";
        data[1][1] = "456788";
        data[1][2] = "Non Restricted User";
        return data;
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
