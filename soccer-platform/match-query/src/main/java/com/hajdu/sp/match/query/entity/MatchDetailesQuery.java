package com.hajdu.sp.match.query.entity;

import com.hajdu.sp.match.lib.value.lineup.LineUp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;


@Builder(builderMethodName="hiddenBuilder")
@Data
@AllArgsConstructor
public class MatchDetailesQuery {

	@DBRef
	private MatchDocument matchDocument;
	
	private int totalYellowCardAmount;
	
	private int totalRedCardAmount;
	
	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;
	
	public static MatchDetailesQueryBuilder builder(MatchDocument matchDocument) {
		return hiddenBuilder().matchDocument(matchDocument);
	}
	
}
