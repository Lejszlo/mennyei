package command.competition;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */

@Value
@AllArgsConstructor
public class RemovePlayerFromClub extends ClubCommand {

    @NonNull
    private String clubId;

    @NonNull
    private String playerId;
}
