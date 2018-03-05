package com.sp.organizer.query.viewer.competition.resource.turn;

import com.sp.core.query.configurations.Interval;
import com.sp.match.api.resource.MatchDocumentResource;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class TurnDocumentResource extends ResourceSupport {
    private int index;

    private Interval interval;
}
