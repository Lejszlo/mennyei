package com.hajdu.sp.match.query.entity;

import com.hajdu.sp.club.lib.value.AwayClubId;
import com.hajdu.sp.club.lib.value.HomeClubId;
import com.hajdu.sp.match.lib.value.MatchResultDetails;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;

@Builder(builderMethodName="hiddenBuilder")
@Data
@Document
public class MatchDocument implements Comparable<MatchDocument> {
	
	private String id;

	private int fans;
	
	private String matchDate;
	
	private boolean played;
	
	@NonNull
	private HomeClubId homeClubId;
	
	@NonNull
	private AwayClubId awayClubId;
	
	private MatchResultDetails matchResultDetails;
	
	public static MatchDocumentBuilder builder(String matchId, HomeClubId homeClubId, AwayClubId awayClubId) {
		return hiddenBuilder().id(matchId).homeClubId(homeClubId).awayClubId(awayClubId);
	}
	
	@Override
	public int compareTo(MatchDocument that) {
		return Comparator.comparing(MatchDocument::getMatchDate).compare(this, that);
	}
}
