package com.sp.match.api.value;

import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.HomeClubId;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
@EqualsAndHashCode(of = {"matchDate", "homeClubId", "awayClubId"})
public class MatchInfo {

	@NonNull
	private String matchDate;

	private boolean played;
	
	private int fans;

	private MatchResultDetails matchResultDetails;

	@NonNull
	private HomeClubId homeClubId;

	@NonNull
	private AwayClubId awayClubId;
	
	public static MatchInfoBuilder builder(HomeClubId homeClubId, AwayClubId awayClubId, LocalDateTime matchDate) {
		return hiddenBuilder()
				.homeClubId(homeClubId)
				.awayClubId(awayClubId)
				.matchDate(matchDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
	}

}
