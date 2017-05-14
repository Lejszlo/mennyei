package com.sp.organizer.backend.match.resource;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = false)
public class MatchDetailesResource extends ResourceSupport {

	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;

}
