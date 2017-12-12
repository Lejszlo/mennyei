package value;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class Transfer {

	@NotNull
	private String transferDate;

	@NotNull
	private String sourceTeamId;

	@NotNull
	private String targetTeamId;

	@NotNull
	private String playerId;
}
