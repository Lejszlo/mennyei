package value.club;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubInfo {
	private String fullName;

	private String name;
}
