package com.hajdu.sp.competition.query.entity;

import com.hajdu.sp.common.Interval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TurnDocument implements Comparable<TurnDocument> {

	private int index;
	
	@Singular
	private List<String> matcheIds;

	@NotNull
	private Interval interval;

	@Override
	public int compareTo(TurnDocument that) {
		return Comparator
				.comparing(TurnDocument::getIndex)
				.compare(this, that);
	}

}
