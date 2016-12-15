package com.mennyei.core.competition.domain.match.domain;

import java.util.ArrayList;
import java.util.List;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Match {
	@NonNull
	private String matchDate;
	
	private boolean played;
	
	private int minute = 90;
	
	private Result result;
	
	@NonNull
	private String homeClubId;
	
	@NonNull
	private String awayClubId;
	
	private List<MatchEvent> events = new ArrayList<>();
	
	public static MatchBuilder builder(String homeClubId, String awayClubId, String matchDate) {
		return hiddenBuilder().homeClubId(homeClubId).awayClubId(awayClubId).matchDate(matchDate);
	}
	
	public boolean containsMatch(String clubId) {
		return clubId.equals(homeClubId) || awayClubId.equals(clubId);
	}
	
	public String whoIsTheOpponentOf(String clubId) {
		if(clubId.equals(homeClubId)) {
			return awayClubId;
		}
		return homeClubId;	
	}
	
	public boolean isAtHome(String clubId) {
		return clubId.equals(homeClubId);
	}
}
