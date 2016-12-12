package com.mennyei.core.competition.domain.season;

import java.util.List;

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
	
	@Singular
	private List<Turn> turns;
	
	public static StageBuilder builder(String name) {
		return hiddenBuilder().name(name);
	}
}
