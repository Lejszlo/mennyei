package com.sp.organizer.query.updater.competition.entity;

import java.util.Comparator;
import java.util.List;

import com.sp.core.query.configurations.Interval;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class TurnDocument implements Comparable<TurnDocument> {

	@NotNull
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
