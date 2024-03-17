package com.qa.places.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateDelete {

	
	private String place_id;
	private String address;
	private String key;
	
	public UpdateDelete(String place_id)
	{
		this.place_id=place_id;
	}
}
