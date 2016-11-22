package com.mennyei.core.player.command;

import com.mennyei.core.player.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Builder
@Value
@AllArgsConstructor
public class AddPlayerCommand extends PlayerCommand {

    private Player player;

}
