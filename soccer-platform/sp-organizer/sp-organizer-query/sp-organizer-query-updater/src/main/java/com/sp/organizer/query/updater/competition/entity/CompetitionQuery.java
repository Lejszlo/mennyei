package com.sp.organizer.query.updater.competition.entity;

import com.sp.core.query.configurations.Interval;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import value.competition.CompetitionInfo;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
@Builder(builderMethodName="hiddenBuilder")
@Data
public class CompetitionQuery {

	@Id
	private String id;

	@Singular
	private List<StageQuery> stages;
	
	private CompetitionInfo competitionInfo;

	@NotNull
	private Interval interval;

	public static CompetitionQuery.CompetitionQueryBuilder builder(CompetitionInfo competitionInfo) {
		return hiddenBuilder().competitionInfo(competitionInfo);
	}
}
