package com.qa.places.base;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.places.client.RestClient;
import com.qa.places.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	
	public static final String addEndPoint = "/maps/api/place/add/json";
	public static final String getEndPoint = "/maps/api/place/get/json";
	public static final String updateEndPoint = "/maps/api/place/update/json";
	public static final String deleteEndPoint = "/maps/api/place/delete/json/";
	
//	public static final String circuitsBASEURI = "http://ergast.com";
	public static final String CIRCUITS_EndPoint = "/api/f1";
	
	protected ConfigurationManager configManager;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseURI;
	protected String circuitsBASEURI;
	
	@Parameters ({"circuitsBASEURI"}) 
	@BeforeClass
	public void setup(@Optional()String circuitsBASEURI) //// reading circuits base URI from TestNg file, have to mark it as optional
	{
		RestAssured.filters(new AllureRestAssured());
		
		configManager = new ConfigurationManager();
		prop = configManager.readPropFile();
		baseURI = prop.getProperty("places_BaseURI");  /// reading places baseURI from prop file 
		this.circuitsBASEURI=circuitsBASEURI;			//// reading circuits base URI from TestNg file	
	   
	}

}
