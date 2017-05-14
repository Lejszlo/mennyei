package com.sp.core.backend.event.player;

import com.sp.core.backend.value.player.Player;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Value
@AllArgsConstructor
public class PlayerAdded implements PlayerEvent {

    private Player player;

//    private String aggregateId;
}
