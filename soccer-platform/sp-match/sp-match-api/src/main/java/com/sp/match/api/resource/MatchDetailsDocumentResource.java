package com.sp.match.api.resource;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = false)
public class MatchDetailsDocumentResource extends ResourceSupport {

	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;

}
