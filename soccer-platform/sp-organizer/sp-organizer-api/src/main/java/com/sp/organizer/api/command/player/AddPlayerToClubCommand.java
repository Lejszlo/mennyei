package com.sp.organizer.api.command.player;

import com.sp.organizer.api.command.club.ClubCommand;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
@AllArgsConstructor
public class AddPlayerToClubCommand extends ClubCommand {

    @NonNull
    private String clubId;

	@NonNull
    private String playerId;

}
