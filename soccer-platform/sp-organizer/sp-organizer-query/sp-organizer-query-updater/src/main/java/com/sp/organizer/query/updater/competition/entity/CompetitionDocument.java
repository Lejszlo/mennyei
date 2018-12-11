package com.sp.organizer.query.updater.competition.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.sp.organizer.api.value.competition.CompetitionInfo;

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
