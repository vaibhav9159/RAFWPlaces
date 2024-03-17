package com.qa.places.client;

import java.util.Map;
import java.util.Properties;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static RequestSpecBuilder reqSpecBuilder;
	private Properties prop;
	private String baseURI;

	/// generic utility to handle request content types
	private void setRequestContentType(String contentType) {
		switch (contentType) {
		case "json":
			reqSpecBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			reqSpecBuilder.setContentType(ContentType.XML);
			break;
		case "multipart":
			reqSpecBuilder.setContentType(ContentType.MULTIPART);
			break;
		case "text":
			reqSpecBuilder.setContentType(ContentType.TEXT);
			break;
		default:
			System.out.println("pls pass valid value");
		}
	}

	/// handle auth for every req
	private boolean addAuth = false;

	private void handleAuth() {
		if (!addAuth) {
			reqSpecBuilder.addQueryParam("key", prop.getProperty("value"));
			reqSpecBuilder.addHeader("Authorization", prop.getProperty("authToken"));
			addAuth = true;
		}
	}

	/// to fetch the values for prop and base uri when the object of this class is
	/// created inside test class
	public RestClient(Properties prop, String baseURI) {
		reqSpecBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	//////////testing create default constructor to handle circuits service as we are not passing prop or base uri from prop file
	public RestClient() {
		reqSpecBuilder = new RequestSpecBuilder();
	}
 
	
	///// create post request with body only
	private RequestSpecification createReq(String contentType, boolean includeAuth, Object reqBody) {
		reqSpecBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			handleAuth();
		}
		if (reqBody != null) {
			reqSpecBuilder.setBody(reqBody);
		}
		return reqSpecBuilder.build();
	}

	//// create post request with headers
	private RequestSpecification createReq(String contentType, boolean includeAuth, Object reqBody,
			Map<String, String> HeadersMap) {
		reqSpecBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			handleAuth();
		}
		if (reqBody != null) {
			reqSpecBuilder.setBody(reqBody);
		}
		reqSpecBuilder.addHeaders(HeadersMap);
		return reqSpecBuilder.build();
	}

	//// capture response for post request
	public Response postCallResponse(String contentType, boolean includeAuth, Object reqBody, String endPoint,
			boolean log) {
		RequestSpecification httpRequest = given(createReq(contentType, includeAuth, reqBody));
		if (log) {
			httpRequest.log().all();
			Response response = httpRequest.post(endPoint);
			return response;
		}
		return httpRequest.post(endPoint);
	}

////capture response for post request with headers
	public Response postCallResponse(String contentType, boolean includeAuth, Object reqBody,
			Map<String, String> HeadersMap, String endPoint, boolean log) {
		RequestSpecification httpRequest = given(createReq(contentType, includeAuth, reqBody, HeadersMap));
		if (log) {
			httpRequest.log().all();
			Response response = httpRequest.post(endPoint);
			return response;
		}
		return httpRequest.post(endPoint);
	}

/////// create get request plain with only auth
	private RequestSpecification createReq( boolean includeAuth) {
		reqSpecBuilder.setBaseUri(baseURI);
//		setRequestContentType(contentType);
		if (includeAuth) {
			handleAuth();
		}
		return reqSpecBuilder.build();
	}

/////// create get request with headers
	private RequestSpecification createReq(String contentType, boolean includeAuth, Map<String, String> HeadersMap) {
		reqSpecBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			handleAuth();
		}
		if (HeadersMap != null) {
			reqSpecBuilder.addHeaders(HeadersMap);
		}
		return reqSpecBuilder.build();
	}
	
/////// create get request with headers + query params
	private RequestSpecification createReq(String contentType, boolean includeAuth, Map<String, String> HeadersMap,
			Map<String, Object> QueryParams) {
		reqSpecBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			handleAuth();
		}
		if (HeadersMap != null) {
			reqSpecBuilder.addHeaders(HeadersMap);
		}

		if (QueryParams != null) {
			reqSpecBuilder.addQueryParams(QueryParams);
		}
		return reqSpecBuilder.build();
	}

///////////// get call plain
	public Response getCallResponse(boolean includeAuth, String getEndPoint, boolean log) {
		RequestSpecification httpRequest = given(createReq(includeAuth));
		if (log) {
			Response response = httpRequest.log().all().get(getEndPoint);
			return response;
		}
		return httpRequest.get(getEndPoint);
	}

////////// get call response headers + query params
	public Response getCallResponse(String contentType, boolean includeAuth, Map<String, String> HeadersMap,
			Map<String, Object> QueryParams, String getEndPoint, boolean log) {
		RequestSpecification httpRequest = given(createReq(contentType, includeAuth, HeadersMap, QueryParams));
		if (log) {
			Response response = httpRequest.log().all().get(getEndPoint);
			return response;
		}
		return httpRequest.get(getEndPoint);
	}

//////////	get call response + headers 
	public Response getCallResponse(String contentType, boolean includeAuth, Map<String, String> HeadersMap,
			String getEndPoint, boolean log) {
		RequestSpecification httpRequest = given(createReq(contentType, includeAuth, HeadersMap));
		if (log) {
			Response response = httpRequest.log().all().get(getEndPoint);
			return response;
		}
		return httpRequest.get(getEndPoint);
	}

//////////////  DELETE call response
	public Response deleteCallResponse(String contentType, boolean includeAuth, Object reqBody, String deleteEndPoint,
			boolean log) {
		RequestSpecification httpRequest = given(createReq(contentType, includeAuth, reqBody));
		if (log) {
			Response response = httpRequest.log().all().delete(deleteEndPoint);
			return response;
		}
		return httpRequest.delete(deleteEndPoint);
	}

//////// create update+create request with query params+headers+body
	private RequestSpecification createReq(String contentType, boolean includeAuth, Object reqBody,
			Map<String, String> QueryParamMap,Map<String, String> HeadersMap) {
		reqSpecBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			handleAuth();
		}
		if (reqBody != null) {
			reqSpecBuilder.setBody(reqBody);
		}
		reqSpecBuilder.addQueryParams(QueryParamMap);
		return reqSpecBuilder.build();
	}
	
//////////////UPDATE call response
	public Response updateCallResponse(String contentType, boolean includeAuth, Object reqBody,
			Map<String, String> QueryParamMap,Map<String, String> HeadersMap, String updateEndPoint, boolean log) {
		RequestSpecification httpRequest = given(createReq(contentType, includeAuth, reqBody,QueryParamMap,HeadersMap));
		if (log) {
			Response response = httpRequest.log().all().put(updateEndPoint);
			return response;
		}
		return httpRequest.delete(updateEndPoint);
	}

}
