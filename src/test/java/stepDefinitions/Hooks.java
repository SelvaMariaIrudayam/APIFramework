package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	//call static variables using class.variablename
	//not with object name
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		stepDefinition step= new stepDefinition();
		//write a code that will give you place id
		//execute this code only when placeid is null
		if(stepDefinition.placeId==null) {
		step.add_place_payload_with("Shetty","French", "CoastLine,USA");
		step.user_calls_with_http_request("addPlaceAPI", "Post");
		step.verify_created_and_maps_to_using("place_id","Shetty","getPlaceAPI");
	}

}
}
