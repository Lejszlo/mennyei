package com.hajdu.sp.competition.lib.resource;

import com.hajdu.sp.common.Interval;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.lib.value.rule.StageRuleSet;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class StageDocumentResource extends ResourceSupport {

    private String name;

    private StageId stageId;

    private Interval interval;

    private StageRuleSet stageRuleSet;

    private List<TurnDocumentResource> turns;

}
