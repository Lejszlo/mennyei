package sp.competition.api.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import sp.common.Interval;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class SeasonDocumentResource extends ResourceSupport {

    private String name;

    private String seasonId;

    private Interval interval;

    private List<StageDocumentResource> stages;

}
