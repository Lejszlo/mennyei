package com.mennyei.publicweb.match.dto.lineup;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mennyei.core.match.domain.event.lineup.LineUpType;
import com.mennyei.publicweb.club.dto.PlayerQuery;

import lombok.Data;

@Data
public class LineUpQuery {
	
	@DBRef
	private PlayerQuery playerQuery;
	
	private int shirtNumber;
	
	private LineUpType lineUpType;
}
