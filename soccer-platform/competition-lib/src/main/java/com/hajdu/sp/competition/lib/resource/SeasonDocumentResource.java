package com.hajdu.sp.competition.lib.resource;

import com.hajdu.sp.common.Interval;
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
