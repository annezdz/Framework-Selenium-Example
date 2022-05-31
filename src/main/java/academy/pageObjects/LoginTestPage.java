package academy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTestPage {

    protected WebDriver driver;

    public LoginTestPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By user = By.cssSelector("#user_email");
    private final By password = By.cssSelector("#user_password");
    private final By logInButton = By.cssSelector("input[value='Log In']");
    private final By forgotPassword = By.cssSelector(".link-below-button");

    public WebElement getUser() {
        return driver.findElement(user);
    }

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    public WebElement getLoginButton() {
        return driver.findElement(logInButton);
    }

    public ForgotPasswordPage getForgotPassword() {
        driver.findElement(forgotPassword).click();
        return new ForgotPasswordPage(driver);
    }

}
