package com.sp.organizer.backend.competition.dto.table;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Document
@Builder
public class TableQuery {

	@Id
	private String id;

	@Singular
	private List<TableRowQuery> rows;
}
