package com.sp.organizer.query.updater.competition.entity;

import com.sp.core.query.configurations.Interval;
import lombok.*;

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
