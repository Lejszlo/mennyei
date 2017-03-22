package com.mennyei.publicweb.competition.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mennyei.publicweb.match.dto.MatchQuery;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Builder(builderMethodName="hiddenBuilder")
@Data
public class TurnQuery implements Comparable<TurnQuery> {

	private int index;
	
	@Singular
	@DBRef
	private List<MatchQuery> matches;
	
	public static TurnQueryBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}

	@Override
	public int compareTo(TurnQuery turnQuery) {
		if(index < turnQuery.getIndex()) {
			return -1;
		}
		if(index > turnQuery.getIndex()) {
			return 1;
		}
		return 0;
	}
	
}
