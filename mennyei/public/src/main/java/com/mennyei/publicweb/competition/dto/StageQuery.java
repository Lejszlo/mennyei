package com.mennyei.publicweb.competition.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

@Data
@Builder(builderMethodName="hiddenBuilder")
public class StageQuery {
	
	@NonNull
	private String name;
	
	@Singular
	private List<TurnQuery> turns = new ArrayList<>();
	
	public static StageQueryBuilder builder(String name) {
		return hiddenBuilder().name(name);
	}
	
	public Optional<TurnQuery> getTurnByIndex(int index) {
		return turns.stream().filter(t -> t.getIndex() == index).findFirst();
	}
	
}
