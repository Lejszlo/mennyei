package com.mennyei.publicweb.competition.dto.stage;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mennyei.publicweb.competition.dto.table.TableQuery;
import com.mennyei.publicweb.competition.dto.turn.TurnQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class StageQuery {
	
	@NonNull
	private String name;
	
	@Singular
	private List<TurnQuery> turns;
	
	@DBRef
	private TableQuery tableQuery;
	
	public static StageQueryBuilder builder(String name) {
		return hiddenBuilder().name(name);
	}
	
	public Optional<TurnQuery> getTurnByIndex(int index) {
		return turns.stream().filter(t -> t.getIndex() == index).findFirst();
	}
	
}
