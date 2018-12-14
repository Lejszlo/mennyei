package com.sp.match.api.value;

import com.sp.club.api.value.AwayClubId;
import com.sp.club.api.value.HomeClubId;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
