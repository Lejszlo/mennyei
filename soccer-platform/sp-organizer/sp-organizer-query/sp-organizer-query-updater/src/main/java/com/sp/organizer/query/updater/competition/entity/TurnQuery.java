package com.sp.organizer.query.updater.competition.entity;

import java.util.Comparator;
import java.util.List;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.query.updater.match.entity.MatchQuery;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class TurnQuery implements Comparable<TurnQuery> {

	@NotNull
	private int index;
	
	@DBRef
	@Singular
	private List<MatchQuery> matches;

	@NotNull
	private Interval interval;

	public static TurnQueryBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}

	@Override
	public int compareTo(TurnQuery that) {
		return Comparator
				.comparing(TurnQuery::getIndex)
				.compare(this, that);
	}

}
