package com.mennyei.publicweb.match.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mennyei.core.match.domain.event.lineup.LineUp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Builder(builderMethodName="hiddenBuilder")
@Data
@AllArgsConstructor
public class MatchDetailesQuery {

	@DBRef
	private MatchQuery matchQuery;
	
	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;
	
	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;
	
	public static MatchDetailesQueryBuilder builder(MatchQuery matchQuery) {
		return hiddenBuilder().matchQuery(matchQuery);
	}
	
}
