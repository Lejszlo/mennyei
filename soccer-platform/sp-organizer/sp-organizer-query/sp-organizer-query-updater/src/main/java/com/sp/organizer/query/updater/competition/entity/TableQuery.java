package com.sp.organizer.query.updater.competition.entity;

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
