package com.hajdu.sp.competition.update.resource;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.rule.StageRuleSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
