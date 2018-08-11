package com.sp.organizer.query.viewer.competition.resource.stage;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
public class StageDocumentResource extends ResourceSupport {

    private String name;

    private String stageId;

    private Interval interval;

    private StageRuleSet stageRuleSet;

    private List<TurnDocumentResource> turns;

}
