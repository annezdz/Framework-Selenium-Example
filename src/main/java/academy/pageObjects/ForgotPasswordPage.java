package academy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {

    protected WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By email = By.cssSelector("#user_email");
    private final By sendButton = By.cssSelector("input[value='Send Me Instruction']");

    public WebElement getEmail() {
        return driver.findElement(email);
    }

    public WebElement buttonSend() {
        return driver.findElement(sendButton);
    }

}
