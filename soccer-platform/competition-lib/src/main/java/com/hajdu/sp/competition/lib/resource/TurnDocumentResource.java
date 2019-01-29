package com.hajdu.sp.competition.lib.resource;

import com.hajdu.sp.common.Interval;
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
