package sp.competition.api.resource;

import com.sp.core.query.configurations.Interval;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class SeasonDocumentResource extends ResourceSupport {

    private String name;

    private String seasonId;

    private Interval interval;

    private List<StageDocumentResource> stages;

}
