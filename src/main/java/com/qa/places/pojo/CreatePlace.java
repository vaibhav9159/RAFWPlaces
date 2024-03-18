package com.qa.places.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePlace {

	private  Location location;

	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	
	private List<String> types;
	
	private String website;
	private String language;
	
	/////response body params-->
	private String status;
	private String place_id;
	private String scope;
	private String reference;
	private String id;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	////inner class 
	public static class Location{
		private double lat;
		private double lng;
}
	
	/// specific constructor 
	
	public CreatePlace(Location location, int accuracy, String name, String phone_number, String address,
			List<String> types, String website, String language) {

		this.location = location;
		this.accuracy = accuracy;
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;
		this.types = types;
		this.website = website;
		this.language = language;
	}
}

