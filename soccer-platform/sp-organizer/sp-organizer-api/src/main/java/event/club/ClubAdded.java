package event.club;

import lombok.AllArgsConstructor;
import lombok.Value;
import value.club.ClubInfo;

@Value
@AllArgsConstructor
public class ClubAdded implements ClubEvent {

	private ClubInfo clubInfo;
	
}
