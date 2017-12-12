package command.competition;

import lombok.AllArgsConstructor;
import lombok.Value;
import value.club.ClubInfo;

@Value
@AllArgsConstructor
public class AddClubCommand extends ClubCommand {

	private ClubInfo clubInfo;
	
}
