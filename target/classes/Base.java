package academy.resources;

import academy.tests.ValidateTitle;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * mvn test -Dbrowser=chrome
 * */


public class Base {

    protected static WebDriver driver;
    protected static Properties properties;
    static Logger log = Logger.getLogger(ValidateTitle.class);


    public static Logger initializeLogger(Object name) {
        return Logger.getLogger((Class) name);
    }

    public WebDriver initializeDriver() throws IOException {

        properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/data.properties");
        properties.load(fis);
        String browser = properties.getProperty("browser");

        switch (browser) {
            case "chrome" : {
                ChromeOptions options  = new ChromeOptions();
                DesiredCapabilities caps = new DesiredCapabilities();
                options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
                caps.setCapability(ChromeOptions.CAPABILITY,options);
//                options.addArguments("--disable-popup-blocking");
//                options.addArguments("-incognito");

                System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver(options);
                break;
            }

            case "firefox" : {
                System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            }
            case "opera" : {
                System.setProperty("webdriver.opera.driver","src/main/resources/drivers/operadriver3.exe");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                OperaOptions options = new OperaOptions();
                options.setBinary("src/main/resources/drivers/operadriver3.exe");
                capabilities.setCapability(OperaOptions.CAPABILITY, options);
                driver = new org.openqa.selenium.opera.OperaDriver();
                break;
            }
            case "ie" : {
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
                System.setProperty("webdriver.ie.driver","src/main/resources/drivers/iedriver.exe");
                driver = new InternetExplorerDriver(capabilities);
             break;
            }
            case "edge" : {
                System.setProperty("webdriver.edge.driver","src/main/resources/drivers/msedgedriver.exe");

                driver = new EdgeDriver();
            }
            default: {
                System.out.println("Incorrect browser. Please choice" +
                        "edge, firefox, ie, opera e chrome.");
            }
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().refresh();
        return driver;
    }

    LocalDateTime date = LocalDateTime.now();

    public String takeSnapShot(ITestResult result) {
        String destinationFile  = ("C:\\Users\\anicolle\\eclipse-workspace\\FrameworkProject\\FrameworkProject\\src\\main\\resources\\screenshots\\" +
                result.getName()+"_"+result.getEndMillis()+".png");;
        try {
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(file, new File(destinationFile));
            System.out.println("*******Screenshot captured********");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return destinationFile;
    }


}
