package com.sp.competition.query.updater.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import sp.common.Interval;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class TurnDocument implements Comparable<TurnDocument> {

	private int index;
	
	@Singular
	private List<String> matcheIds;

	@NotNull
	private Interval interval;

	public static TurnDocumentBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}

	@Override
	public int compareTo(TurnDocument that) {
		return Comparator
				.comparing(TurnDocument::getIndex)
				.compare(this, that);
	}

}
