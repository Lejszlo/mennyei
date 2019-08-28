package com.hajdu.sp.competition.update.resource;

import com.hajdu.sp.competition.update.util.Interval;
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
