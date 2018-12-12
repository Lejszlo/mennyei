package sp.competition.api.resource;

import com.sp.core.query.configurations.Interval;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class TurnDocumentResource extends ResourceSupport {
    private int index;

    private Interval interval;
}
