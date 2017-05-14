package com.sp.organizer.backend.competition.domain.season;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Stage {
	
	@NonNull
	private String name;

	private boolean active = false;
	
	@Singular
	private List<Turn> turns = new LinkedList<>();
	
	public static StageBuilder builder(String name) {
		return hiddenBuilder().name(name);
	}
	
	public Optional<Turn> getTurnByIndex(int index) {
		return turns.stream().filter(t -> t.getIndex() == index).findFirst();
	}
}
