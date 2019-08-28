package com.hajdu.sp.competition.update.value.match;

import com.hajdu.sp.competition.update.value.club.AwayClubId;
import com.hajdu.sp.competition.update.value.club.HomeClubId;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"matchDate", "homeClubId", "awayClubId"})
public class MatchInfo {

	private String matchDate;

	@NonNull
	private HomeClubId homeClubId;

	@NonNull
	private AwayClubId awayClubId;

}
