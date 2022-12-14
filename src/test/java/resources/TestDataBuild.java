package resources;

import java.util.ArrayList;
import java.util.List;

import GooglePOJO.AddPlace;
import GooglePOJO.location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name,String language, String address) {
		AddPlace newPlace=new AddPlace();
		location newLocation=new location();
		newLocation.setLat(-38.383494);
		newLocation.setLng(30.383494);
		newPlace.setAccuracy(50);
		newPlace.setAddress(address);
		newPlace.setLanguage(language);
		newPlace.setName(name);
		newPlace.setWebsite("selvaAcdamy");
		newPlace.setPhone_number("456789000");
		List<String> myList=new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		newPlace.setTypes(myList);
		newPlace.setLocation(newLocation);
		return newPlace;
	}
}
