package com.qa.places.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.places.base.BaseTest;
import com.qa.places.client.RestClient;
import com.qa.places.pojo.UpdateDelete;

import io.restassured.response.Response;

public class DeletePlaceTest extends BaseTest{

	@BeforeMethod
	public void initialSetUp()
	{
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void deletePlace(ITestContext context)
	{		
		String placeID=(String)context.getSuite().getAttribute("place_id");
		UpdateDelete u = new UpdateDelete(placeID);
		
		Response deleteResponse = restClient.deleteCallResponse("json", false, u, deleteEndPoint, true);
		System.out.println("~~~~~");
		deleteResponse.prettyPrint();
		String resp= deleteResponse.asString();
		
		String deleteMsg=	JsonPath.read(resp, "$.status");
		System.out.println("msg---->"+deleteMsg);
		
		Assert.assertEquals(deleteMsg, "OK");
	}
}
