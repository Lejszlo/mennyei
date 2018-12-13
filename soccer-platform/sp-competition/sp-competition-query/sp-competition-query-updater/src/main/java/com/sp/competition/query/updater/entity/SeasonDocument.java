package com.sp.competition.query.updater.entity;

import lombok.*;
import sp.common.Interval;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SeasonDocument {

    @NotNull
	private String id;

	@NotNull
	private String competitionDocumentId;

	@NonNull
	private String name;

	@Singular
	private List<StageDocument> stages;

	@NotNull
	private Interval interval;

}
