package sp.competition.api.resource;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;
import sp.common.Interval;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class TurnDocumentResource extends ResourceSupport {
    private int index;

    private Interval interval;
}
