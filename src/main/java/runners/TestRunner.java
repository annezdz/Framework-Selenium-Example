package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\anicolle\\eclipse-workspace\\FrameworkProject\\FrameworkProject\\src\\main\\java\\academy\\features\\login.feature",
        glue="academy.stepDefinitions"

)

public class TestRunner extends AbstractTestNGCucumberTests {


}
