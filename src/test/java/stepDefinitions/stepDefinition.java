package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import GooglePOJO.AddPlace;
import GooglePOJO.location;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

//we  are separating our data from the code
//dynamically it will be passed to code
//base uri in common to all tests also content type and authentication also
//we can use it to get and delete APi's
//create utils under resources,reusable methods
//log all the response in util-using assFilter()reuest Loggingfilter logrequest(stream
public class stepDefinition extends Utils {
	  RequestSpecification req ;
	 ResponseSpecification res ;
	//RequestSpecification req1;
	Response result ;
	TestDataBuild testData=new TestDataBuild();
	//String placeId;
	JsonPath js;
	 static String placeId;//all scenarios will use the same placeId
	//inherits utils
	
	@Given("Add Place payload with {string} {string}{string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		 req=given().spec(requestSpecifications())//this is got from utils,we use logging in util addFilter methods
					.body(testData.addPlacePayLoad(name,language,address));
					 res = new ResponseSpecBuilder().expectContentType("application/json").build();
	   
	}

	
//	@Given("Add Place payload")
//	public void add_place_payload() throws IOException {
//		 req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//					.setContentType("application/json").addQueryParam("key","qaclick123").build();
		//Separate the request and response
//		 AddPlace newPlace=new AddPlace();
//			location newLocation=new location();
//			newLocation.setLat(-38.383494);
//			newLocation.setLng(30.383494);
//			newPlace.setAccuracy(50);
//			newPlace.setAddress("8821,calistoga springs way ");
//			newPlace.setLanguage("Germen");
//			newPlace.setName("JeffCheryl House");
//			newPlace.setWebsite("selvaAcdamy");
//			newPlace.setPhone_number("456789000");
//			List<String> myList=new ArrayList<>();
//			myList.add("shoe park");
//			myList.add("shop");
//			newPlace.setTypes(myList);
//			newPlace.setLocation(newLocation);
			
			
//			Response addPlaceRespone = given().log().all().queryParam("key","qaclick123" ).header("Content-Type", "application/json")
//					.body(newPlace).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
//					.extract().response();
	//		System.out.println("The Response of this request");
	//			System.out.println(addPlaceRespone .asPrettyString());
				//here RestAssured convert this POJO into JSON object and send it via the body to the request
			
				//here RestAssured convert this POJO into JSON object and send it via the body to the request
//			 req1=given().spec(requestSpecifications())//this is got from utils,we use logging in util addFilter methods
//			.body(testData.addPlacePayLoad());
//			 res = new ResponseSpecBuilder().expectContentType("application/json").build();
			 
	    
	//}

//	@When("User calls {string} with post http request")
//	public void user_calls_with_post_http_request(String string) {
//		 result = req1.when().post("/maps/api/place/add/json") 
//				.then().spec(res).log().all().assertThat().body("scope", equalTo("APP"))
//				.header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response();
//		 System.out.println(result.asPrettyString());
//	    
//	}
	
	//using ENUM we are making our testcases more generic
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String endpoint, String httpMethod) {
		//resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//create a constructor with the value of endpoint passed
		APIresources resourceAPI = APIresources.valueOf(endpoint);
		if(httpMethod.equalsIgnoreCase("Post"))
		result = req.when().post(resourceAPI.getResource());
		else if(httpMethod.equalsIgnoreCase("Get"))
		result = req.when().get(resourceAPI.getResource());
//				.then().spec(res).log().all().assertThat().body("scope", equalTo("APP"))
//			.header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response();
//		 System.out.println(result.asPrettyString());
	    
	}

	@Then("The response status code is {int}")
	public void the_response_status_code_is(Integer int1) {
		assertEquals(result.getStatusCode(),200);
		System.out.println("test passed1");
	//	Assert.assertEquals(result.getStatusCode(),200,"test failed");
	    
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		//String jstring=result.asString();
		// js=new JsonPath(jstring);
		//String actualValue = js.getString(key);
		String actualValue= getJsonValue(result,key);
		assertEquals(actualValue,value);
		System.out.println("Test passed2");
	//placeId=getJsonValue(result,"place_id");
	    

	}
	@Then("verify {string} created and maps to {string} using {string}")//place_id
	public void verify_created_and_maps_to_using(String key, String expectedName, String resource) throws IOException {
		//prepare request spec
				//query parameter
		placeId=getJsonValue(result,key);
		 //create get request api call 
				req=given().spec(requestSpecifications()).queryParam(key,placeId);
				
				user_calls_with_http_request(resource,"Get");
				
				String actualName = getJsonValue(result,"name");
				assertEquals(actualName,expectedName);
				System.out.println("The chain of post and get api's passed");
			
	}
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		 //construct the request specification
		//for post we need body not a query parameter
		//there is only one key value pair,we don't need pojo class
	JSONObject jsObj=new JSONObject();
		jsObj.put("place_id", placeId);
		System.out.println(jsObj.toJSONString());
//		String payLoad="{\r\n"
//				+ "\"place_id\":\"928b51f64aed18713b0d164d9be8d67f\"\r\n"
//				+ "}";
		req=given().spec(requestSpecifications()).body(jsObj.toJSONString()).log().all();
	   
	}
//	@Given("DeletePlace Payload")
//	public void deleteplace_Payload() throws IOException {
//	    // Write code here that turns the phrase above into concrete actions
//	   
//		res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
//	}

	
	

	   
	}


		




