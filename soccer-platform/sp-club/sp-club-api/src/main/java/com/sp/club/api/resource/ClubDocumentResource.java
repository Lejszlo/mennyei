package com.sp.club.api.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClubDocumentResource extends ResourceSupport {
    private String clubId;

    private String name;

    private String fullName;

}
