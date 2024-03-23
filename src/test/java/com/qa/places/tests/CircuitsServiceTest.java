package com.qa.places.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.places.base.BaseTest;
import com.qa.places.client.RestClient;
import com.qa.places.utils.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitsServiceTest extends BaseTest{

	
	@BeforeMethod
	public void getUserSetup()
	{
		restClient = new RestClient(prop,circuitsBASEURI);
	}
	
	@Test
	  public void getAllCicuitsTest() {
		  Response res = restClient.getCallResponse(true,CIRCUITS_EndPoint+"/2016/circuits.json",true);
		 
		  int statuscode= res.statusCode();
	//	  res.prettyPrint();
		  
		  JsonPathValidator js = new JsonPathValidator();
		 List<String> singlecountry= js.readList(res, "$.MRData.CircuitTable.Circuits.[?(@.circuitId=='sochi')].Location.country");
		 System.out.println("single country-->"+singlecountry);
		 Assert.assertEquals(singlecountry.get(0), "Russia");
		 Assert.assertEquals(statuscode, 200);
		  
	  }

}
