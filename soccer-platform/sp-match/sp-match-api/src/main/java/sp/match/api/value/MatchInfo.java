package sp.match.api.value;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
@EqualsAndHashCode(of = {"matchDate", "homeClubId", "awayClubId"})
public class MatchInfo {
	@NonNull
	private LocalDateTime matchDate;

	private boolean played;
	
	private int fans;

	private MatchResultDetails matchResultDetails;

	@NonNull
	private String homeClubId;

	@NonNull
	private String awayClubId;
	
	public static MatchInfoBuilder builder(String homeClubId, String awayClubId, LocalDateTime matchDate) {
		return hiddenBuilder().homeClubId(homeClubId).awayClubId(awayClubId).matchDate(matchDate);
	}

}
