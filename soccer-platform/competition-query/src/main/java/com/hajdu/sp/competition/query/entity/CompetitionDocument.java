package com.hajdu.sp.competition.query.entity;

import com.hajdu.sp.competition.lib.value.CompetitionInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
