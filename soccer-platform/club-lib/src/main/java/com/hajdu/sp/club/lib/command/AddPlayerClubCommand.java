package com.hajdu.sp.club.lib.command;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
@AllArgsConstructor
public class AddPlayerClubCommand extends ClubCommand {

    @NonNull
    private String clubId;

	@NonNull
    private String playerId;

}
