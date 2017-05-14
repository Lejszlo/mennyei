package com.sp.organizer.backend.competition.dto.turn;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.sp.organizer.backend.match.dto.match.MatchQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
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
