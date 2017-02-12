package com.mennyei.publicweb.match.dto;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.mennyei.core.match.domain.event.lineup.LineUp;

import lombok.Data;
import lombok.Singular;

@Data
public class MatchDetailesResource extends ResourceSupport {

	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;
	
	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;
	
}
