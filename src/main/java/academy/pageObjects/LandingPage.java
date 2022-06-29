package academy.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage {

    protected WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginButton = By.xpath("//span[normalize-space()='Login']");

    private final By popUp = By.cssSelector(".sumo-form-wrapper.listbuilder-popup");

    private final By dismissPopUp = By.xpath("//button[normalize-space()='NO THANKS']");

    private final By title = By.cssSelector("div[class='text-center'] h2");

    private final By links = By.xpath("//nav/ul/li/a[1]");

    private final By headerTitle = By.cssSelector("div[class='col-sm-6 col-md-8 hidden-xs video-banner'] p");

    public LoginTestPage getLogin() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(loginButton));
       // driver.findElement(loginButton).click();
        return new LoginTestPage(driver);
    }

    public WebElement getHeaderTitle() {
        return driver.findElement(headerTitle);
    }

    public WebElement getPopup() {
        return driver.findElement(dismissPopUp);
    }

    public int getPopUpsSize() {
        return driver.findElements(dismissPopUp).size();
    }

    public WebElement getTitle() {
        return driver.findElement(title);
    }

    public List<WebElement> getNavigationBar() {
        return driver.findElements(links);
    }


}
