package com.sp.core.backend.value.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
public class MatchInfo {
	@NonNull
	private String matchDate;

	private boolean played;
	
	private int fans;

	private MatchResultDetailes matchResultDetailes;

	@NonNull
	private String homeClubId;

	@NonNull
	private String awayClubId;
	
	@NonNull
	private String competitionId;
	
	private String stageName;
	
	private int index;

	public static MatchInfoBuilder builder(String homeClubId, String awayClubId, String matchDate) {
		return hiddenBuilder().homeClubId(homeClubId).awayClubId(awayClubId).matchDate(matchDate);
	}
	
	public boolean containsClub(String clubId) {
		return clubId.equals(homeClubId) || awayClubId.equals(clubId);
	}

}
