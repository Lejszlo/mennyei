package com.mennyei.publicweb.competition.dto.table;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.mennyei.publicweb.competition.dto.CompetitionQuery;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Builder(builderMethodName = "hiddenBuilder")
@Data
public class TableQuery {

	@Id
	private String id;

	private String competitionId;

	private String competitionName;

	private String stageName;

	private int promotion;

	private int relegation;

	@Singular
	private List<TableRowQuery> rows;

	public static TableQueryBuilder builder(CompetitionQuery competitionQuery, String stageName) {
		return hiddenBuilder().competitionId(competitionQuery.getId())
				.competitionName(competitionQuery.getCompetitionInfo().getName()).stageName(stageName)
				.promotion(competitionQuery.getCompetitionRuleSet().getPromotion())
				.relegation(competitionQuery.getCompetitionRuleSet().getRelegation());
	}
}
