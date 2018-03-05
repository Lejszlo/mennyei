package com.sp.organizer.query.viewer.club.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClubDocumentResource extends ResourceSupport {

    private String name;

    private String fullName;

}
