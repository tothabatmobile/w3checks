package TestRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml"},
    features = "src/test/resources/features/",
    glue = {"com.steps"},
    monochrome = true)
public class Runner {

}