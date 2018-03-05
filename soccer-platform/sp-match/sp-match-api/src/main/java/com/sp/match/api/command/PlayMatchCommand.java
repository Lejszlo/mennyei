package com.sp.match.api.command;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;
import com.sp.match.api.value.event.MatchEvent;
import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.HomeClubId;
import com.sp.organizer.api.value.competition.CompetitionId;
import com.sp.organizer.api.value.competition.StageId;

import javax.validation.constraints.NotNull;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class PlayMatchCommand extends MatchCommand {

	private HomeClubId homeClubId;

	private AwayClubId awayClubId;

	private StageId stageId;

	@Singular
	private List<MatchEvent> homeClubevents;
	
	@Singular
	private List<MatchEvent> awayClubevents;

	@NotNull
	private CompetitionId competitionId;

	public static PlayMatchCommandBuilder builder(CompetitionId competitionId, HomeClubId homeClubId, AwayClubId awayClubId, StageId stageIndex) {
		return hiddenBuilder()
                .competitionId(competitionId)
                .awayClubId(awayClubId)
                .homeClubId(homeClubId)
                .stageId(stageIndex);
	}
}
