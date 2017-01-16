package com.mennyei.publicweb.match.dto;

import java.util.ArrayList;
import java.util.List;

import com.mennyei.core.match.domain.event.lineup.LineUp;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Builder(builderMethodName="hiddenBuilder")
@Data
public class MatchDetailesQuery {

	private MatchQuery matchQuery;
	
	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;
	
	@Singular
	private List<LineUp> homeLineUps = new ArrayList<>();
	
	@Singular
	private List<LineUp> awayLineUps = new ArrayList<>();
	
	public static MatchDetailesQueryBuilder builder(MatchQuery matchQuery) {
		return hiddenBuilder().matchQuery(matchQuery);
	}
	
}
