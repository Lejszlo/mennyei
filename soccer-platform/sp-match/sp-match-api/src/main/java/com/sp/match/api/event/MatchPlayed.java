package com.sp.match.api.event;

import com.sp.match.api.value.MatchResultDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.HomeClubId;
import com.sp.organizer.api.value.competition.CompetitionId;
import com.sp.organizer.api.value.competition.StageId;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements MatchEvent {
	private int fans;
	
	private boolean played;

	private CompetitionId competitionId;

	private MatchResultDetails matchResultDetailes;

	private StageId stageId;

    @NonNull
    private HomeClubId homeClubId;

    @NonNull
    private AwayClubId awayClubId;
	
	public static MatchPlayedBuilder builder(HomeClubId homeClubId, AwayClubId awayClubId, MatchResultDetails matchResultDetailes, CompetitionId competitionId, StageId stageId) {
		return hiddenBuilder()
                .homeClubId(homeClubId)
                .awayClubId(awayClubId)
                .matchResultDetailes(matchResultDetailes)
                .competitionId(competitionId)
                .stageId(stageId);
	}


}
