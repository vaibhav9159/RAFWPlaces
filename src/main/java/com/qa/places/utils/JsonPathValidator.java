package com.qa.places.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidator {

	public String resp(Response res)
	{
		return res.getBody().asString();
	}
	
	public <T> T read(Response response, String jsonPath)
	{
		
		//String res= response.getBody().asString();
		return JsonPath.read(resp(response), jsonPath);
	}
	
	public <T> List<T> readList(Response response, String jsonPath)
	{
		String res= response.getBody().asString();
		return JsonPath.read(res, jsonPath);
	}
	
	public <T> List<Map<T,T>> readListOfMaps(Response response, String jsonPath)
	{
		String res= response.getBody().asString();
		return JsonPath.read(res, jsonPath);
	}
	
}
