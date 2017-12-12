package com.sp.organizer.query.viewer.competition.resource.stage;

import com.sp.core.query.configurations.Interval;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper=false)
public class StageQueryResource extends ResourceSupport {

    private Interval interval;

}
