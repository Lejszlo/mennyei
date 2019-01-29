package com.hajdu.sp.match.lib.value;

import com.hajdu.sp.club.lib.value.AwayClubId;
import com.hajdu.sp.club.lib.value.HomeClubId;
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
