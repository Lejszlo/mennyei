package com.sp.player.backend.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import value.Player;

@Builder
@Value
@AllArgsConstructor
public class AddPlayerCommand extends PlayerCommand {

    private Player player;

}
