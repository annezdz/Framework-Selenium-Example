package academy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalHomePage {

    protected WebDriver driver;

    public PortalHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By searchbox = By.cssSelector("[id='user_email']");

    public WebElement getSearchBox() {
        return driver.findElement(searchbox);
    }



}
