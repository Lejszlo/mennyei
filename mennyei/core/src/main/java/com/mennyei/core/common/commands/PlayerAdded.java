package com.mennyei.core.common.commands;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.player.events.PlayerEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Builder
@Value
@AllArgsConstructor
public class PlayerAdded implements PlayerEvent {

    private Player player;

    private String aggregateId;
}
