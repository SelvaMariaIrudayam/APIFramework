package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
//use properties file to have all our global variables and values
	static RequestSpecification req;
	
	public RequestSpecification requestSpecifications() throws IOException {
		if(req==null) {
		PrintStream logStream=new PrintStream(new FileOutputStream("loggging.txt"));
	 req = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(logStream))
				.addFilter(ResponseLoggingFilter.logResponseTo(logStream))
				.setContentType("application/json").addQueryParam("key","qaclick123").build();
		return req;
		}
		return req;
	}
	
	public  static String getGlobalProperty(String key) throws IOException {
		Properties prop=new Properties();
		//read file
		FileInputStream fis= new FileInputStream("C:\\Users\\srajam\\workspace2\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		String val = prop.getProperty(key);
		return val;
	}
	 
	public String getJsonValue(Response result,String key) {
		String jString=result.asString();
		JsonPath js=new JsonPath(jString);
		 String actualValue = js.getString(key);
		 //System.out.println(actualValue);
		 return actualValue;
		 
	}
}
