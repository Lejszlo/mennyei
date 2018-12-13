package sp.competition.api.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import sp.common.Interval;
import sp.competition.api.value.StageId;
import sp.competition.api.value.rule.StageRuleSet;

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
