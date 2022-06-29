package academy.stepDefinitions;

import academy.pageObjects.LandingPage;
import academy.pageObjects.LoginTestPage;
import academy.pageObjects.PortalHomePage;
import academy.resources.Base;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.Assert;

import java.io.IOException;

//@RunWith(Cucumber.class)
public class StepDefinitions extends Base {

    private LandingPage landingPage;
    private  LoginTestPage loginPage;

    @Given("initialize the browser with chrome")
    public void initialize_the_browser_with_chrome() throws IOException {
        driver = initializeDriver();
    }


    @When("^user enters (.+) and (.+) and logs in$")
    public void user_enters_and_and_logs_in(String username, String password) throws Throwable {
//        LoginTestPage loginPage = landingPage.getLogin();
        loginPage.getUser().sendKeys(username);
        loginPage.getPassword().sendKeys(password);
        loginPage.getLoginButton().click();
    }


    @Then("^verify that user is succesfully logged in$")
    public void verify_that_user_is_succesfully_logged_in() throws Throwable {
        PortalHomePage portalHomePage = new PortalHomePage(driver);
        Assert.assertTrue(portalHomePage.getSearchBox().isDisplayed());
    }

    @And("^navigate to \"([^\"]*)\" site$")
    public void navigate_to_something_site(String url) throws Throwable {
       driver.get(url);
    }

    @And("^click on Login link in home page to land on Secure sign in page$")
    public void click_on_login_link_in_home_page_to_land_on_secure_sign_in_page()  {
        landingPage = new LandingPage(driver);
        if(landingPage.getPopUpsSize() > 0) {
            landingPage.getPopup().click();
        }
        loginPage = landingPage.getLogin();
    }

    @And("^close browser$")
    public void close_browser()  {
        driver.close();
    }

}
