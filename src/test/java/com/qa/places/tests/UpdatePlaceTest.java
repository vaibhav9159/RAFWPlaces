package com.qa.places.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.places.base.BaseTest;
import com.qa.places.client.RestClient;
import com.qa.places.pojo.UpdateDelete;
import com.qa.places.utils.JsonPathValidator;

import io.restassured.response.Response;

public class UpdatePlaceTest extends BaseTest{

	@BeforeMethod
	public void initialSetUp()
	{
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void updatePlace(ITestContext context)
	{		
		String placeID=(String)context.getSuite().getAttribute("place_id");
		UpdateDelete u = new UpdateDelete(placeID, "jankipuram", "qaclick123");
		Map<String,String> queryParams = new HashMap<String,String>();
		
		Response updateResponse = restClient.updateCallResponse("json", true, u, queryParams, null, updateEndPoint, true);
		System.out.println("~~~~~");
		updateResponse.prettyPrint();
		
		JsonPathValidator js = new JsonPathValidator();
		String updateMsg=	js.read(updateResponse, "$.msg");

		System.out.println("msg---->"+updateMsg);
		
		Assert.assertEquals(updateMsg, "Address successfully updated");
	}
}
