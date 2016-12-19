package com.mennyei.publicweb.competition.dto.table;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TableQuery {

	private String competitionId;
	
	private String competitionName;
	
	private String stageName;
	
	private List<TableRow> rows = new ArrayList<>();
	
}
