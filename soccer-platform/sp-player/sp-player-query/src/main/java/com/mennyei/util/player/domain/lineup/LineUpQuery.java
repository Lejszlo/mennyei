package com.mennyei.util.player.domain.lineup;

import com.sp.core.backend.value.match.lineup.LineUpType;
import com.mennyei.util.player.domain.PlayerQuery;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


import lombok.Data;

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
