package com.sp.match.query.updater.match.entity;

import com.sp.club.api.value.AwayClubId;
import com.sp.club.api.value.HomeClubId;
import com.sp.match.api.value.MatchResultDetails;
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
