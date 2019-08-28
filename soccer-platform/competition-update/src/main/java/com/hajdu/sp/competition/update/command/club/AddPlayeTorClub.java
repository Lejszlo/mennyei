package com.hajdu.sp.competition.update.command.club;

import com.hajdu.sp.competition.update.command.club.ClubCommand;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
@AllArgsConstructor
public class AddPlayeTorClub extends ClubCommand {

    @NonNull
    private String clubId;

	@NonNull
    private String playerId;

}
