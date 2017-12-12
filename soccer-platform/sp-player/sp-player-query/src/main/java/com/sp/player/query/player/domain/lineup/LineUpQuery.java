package com.sp.player.query.player.domain.lineup;

import com.sp.player.query.player.domain.PlayerQuery;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


import lombok.Data;
import sp.match.api.value.lineup.LineUpType;

@Data
public class LineUpQuery {

    @Id
    private String id;

	@DBRef
	private PlayerQuery playerQuery;
	
	private int shirtNumber;
	
	private LineUpType lineUpType;

	private String matchId;
}
