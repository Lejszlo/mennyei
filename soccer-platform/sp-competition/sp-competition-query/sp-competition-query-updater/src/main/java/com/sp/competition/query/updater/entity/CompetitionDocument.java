package com.sp.competition.query.updater.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sp.competition.api.value.CompetitionInfo;

import java.util.List;

@Document
@Builder(builderMethodName="hiddenBuilder")
@Data
public class CompetitionDocument {

	@Id
	private String id;

	@Singular
	private List<SeasonDocument> seasons;
	
	private CompetitionInfo competitionInfo;

	public static CompetitionDocument.CompetitionDocumentBuilder builder(CompetitionInfo competitionInfo) {
		return hiddenBuilder().competitionInfo(competitionInfo);
	}
}
