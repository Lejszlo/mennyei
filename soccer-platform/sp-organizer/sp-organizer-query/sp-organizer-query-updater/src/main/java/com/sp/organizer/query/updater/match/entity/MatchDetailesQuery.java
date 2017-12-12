package com.sp.organizer.query.updater.match.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import sp.match.api.value.lineup.LineUp;

import java.util.List;

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
