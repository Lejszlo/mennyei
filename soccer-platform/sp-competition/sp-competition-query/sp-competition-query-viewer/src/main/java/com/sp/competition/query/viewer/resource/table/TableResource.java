package com.sp.competition.query.viewer.resource.table;

import com.sp.match.api.resource.MatchDocumentResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class TableResource extends ResourceSupport {

    List<MatchDocumentResource> matches;

}
