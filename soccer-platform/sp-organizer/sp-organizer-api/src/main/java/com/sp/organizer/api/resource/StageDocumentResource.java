package com.sp.organizer.api.resource;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class StageDocumentResource extends ResourceSupport {

    private String name;

    private String stageId;

    private Interval interval;

    private StageRuleSet stageRuleSet;

    private List<TurnDocumentResource> turns;

}
