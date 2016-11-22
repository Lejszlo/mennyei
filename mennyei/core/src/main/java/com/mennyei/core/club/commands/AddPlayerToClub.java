package com.mennyei.core.club.commands;

import lombok.Builder;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Builder
@Value
public class AddPlayerToClub extends ClubCommand {

    private String playerId;

}
