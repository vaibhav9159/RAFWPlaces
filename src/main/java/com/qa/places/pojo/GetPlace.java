package com.qa.places.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qa.places.pojo.CreatePlace.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPlace {

///////// For get place response body pojo
		
		private Location location;

		@JsonProperty("accuracy")
		private int AccuracY;

		@JsonProperty("name")
		private String NamE;

		@JsonProperty("phone_number")
		private String Phone_numbeR;

		@JsonProperty("address")
		private String AddresS;

		@JsonProperty("types")
		private String typeS;

		@JsonProperty("website")
		private String WebsitE;

		@JsonProperty("language")
		private String LanguagE;
		
		
	//////inner class of get place
			@Data
			@NoArgsConstructor
			@AllArgsConstructor
			public static class Location {
				private double latitude;
				private double longitude;
			}

	}


