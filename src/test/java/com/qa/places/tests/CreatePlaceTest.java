package com.qa.places.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.places.base.BaseTest;
import com.qa.places.client.RestClient;
import com.qa.places.pojo.CreatePlace;
import com.qa.places.pojo.CreatePlace.Location;

import io.restassured.response.Response;

public class CreatePlaceTest extends BaseTest{
	
//	public RestClient client;
	
	@BeforeMethod
	public void getInitialsetUp()
	{
		restClient = new RestClient(prop, baseURI);	 
	}
	
	@Test
	public void createPlace(ITestContext context)
	{	
		List<String> types = Arrays.asList("vande mataram", "jai hind");
		Location loc = new Location(26.862410, 81.020348);
		
		CreatePlace cp = new CreatePlace(loc, 60, "ganga ghat", "91369108", "Sitapur Rd,Lucknow", types, "https://bharatMataKiJai.co.in", "Hindi");
		
		//serialize
		Response createCall = restClient.postCallResponse("json", true, cp, addEndPoint, true);
		
		//printing response in console
		createCall.prettyPrint(); 	System.out.println(createCall.statusCode());
		
		//Deserialize
		ObjectMapper map = new ObjectMapper();
		try {
			CreatePlace createResponse=	map.readValue(createCall.getBody().asString(), CreatePlace.class);

		// assertions 	
			Assert.assertNotNull(createResponse.getId());	
			AssertJUnit.assertEquals("OK", createResponse.getStatus());
			AssertJUnit.assertEquals("APP",createResponse.getScope());
			Assert.assertNotNull(createResponse.getReference());
			
			String placeId = createResponse.getPlace_id(); System.out.println("place_id---> "+ placeId);
			Assert.assertNotNull(placeId);
			
	////////// set attribute		
			context.getSuite().setAttribute("place_id", placeId);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	
}
