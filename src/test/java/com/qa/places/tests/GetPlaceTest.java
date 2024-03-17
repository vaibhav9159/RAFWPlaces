package com.qa.places.tests;

import java.util.HashMap;
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
import com.qa.places.pojo.GetPlace;

import io.restassured.response.Response;

public class GetPlaceTest extends BaseTest{

	
	@BeforeMethod
	public void getSetUp()
	{
		restClient = new RestClient(prop,baseURI);	
	}
	
	@Test
	public void fetchPlace(ITestContext context)
	{
		String placeID = (String)context.getSuite().getAttribute("place_id");
		System.out.println(placeID +" !!!!!!!!!!");
		Map<String,Object> placeIDQueryParam = new HashMap<String,Object>();
		placeIDQueryParam.put("place_id", placeID);
		
		Response getCallResponse = restClient.getCallResponse("json", true, null, placeIDQueryParam, getEndPoint, true);
		
		getCallResponse.prettyPrint();
		
		/////// Deserialize json body
		ObjectMapper map = new ObjectMapper();
		try {
			GetPlace GetPlaceResponse =  map.readValue(getCallResponse.getBody().asString(), GetPlace.class);
		
		//////assertions 
		Assert.assertEquals(GetPlaceResponse.getAccuracY(), 60);
		Assert.assertEquals(GetPlaceResponse.getNamE(), "ganga ghat");
		Assert.assertEquals(GetPlaceResponse.getPhone_numbeR(), "91369108");
		Assert.assertEquals(GetPlaceResponse.getAddresS(), "Sitapur Rd,Lucknow");
		Assert.assertEquals(GetPlaceResponse.getWebsitE(), "https://bharatMataKiJai.co.in");
		Assert.assertEquals(GetPlaceResponse.getLanguagE(), "Hindi");
		String t = (GetPlaceResponse.getTypeS());
		Assert.assertEquals(t,"vande mataram,jai hind");
		
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
	}
	
	
}
	
