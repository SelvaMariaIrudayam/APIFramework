package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
   //path of feature file
   features = "src/test/java/Features/PlaceValidations.feature",
   //path of step definition file
   glue = "stepDefinitions",
 // tags= "@DeletePlace",
   
   plugin = {"pretty", "html:target/report.html","pretty","json:target/report.json"},
   
   stepNotifications=true 
		)
public class TestRunner1 {

}
