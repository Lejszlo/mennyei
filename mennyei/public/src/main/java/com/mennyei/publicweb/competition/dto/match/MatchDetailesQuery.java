package com.mennyei.publicweb.competition.dto.match;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName="hiddenBuilder")
@Data
public class MatchDetailesQuery {

	private MatchQuery matchQuery;
	
	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;
	
	public static MatchDetailesQueryBuilder builder(MatchQuery matchQuery) {
		return hiddenBuilder().matchQuery(matchQuery);
	}
	
}
