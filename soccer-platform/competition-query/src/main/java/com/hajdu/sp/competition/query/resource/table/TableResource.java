package com.hajdu.sp.competition.query.resource.table;

import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class TableResource extends ResourceSupport {

    List<MatchDocumentResource> matches;

}
