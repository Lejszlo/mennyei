package com.mennyei.publicweb.match.dto;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;
import lombok.Singular;

@Data
public class MatchDetailesResource extends ResourceSupport {

	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;
	
	@Singular
	private List<LineUpResource> homeStarters;
	
	@Singular
	private List<LineUpResource> awayStarters;
	
	@Singular
	private List<LineUpResource> homeSubstitution;
	
	@Singular
	private List<LineUpResource> awaySubstitution;
	
}
