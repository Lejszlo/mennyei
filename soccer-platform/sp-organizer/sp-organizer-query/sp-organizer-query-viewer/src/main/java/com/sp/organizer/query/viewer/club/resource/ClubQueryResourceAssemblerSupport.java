package com.sp.organizer.query.viewer.club.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.viewer.club.controller.ClubQueryController;

@Component
public class ClubQueryResourceAssemblerSupport extends ResourceAssemblerSupport<ClubDocument, ClubDocumentResource> {

    public ClubQueryResourceAssemblerSupport() {
        super(ClubQueryController.class, ClubDocumentResource.class);
    }

    @Override
    public ClubDocumentResource toResource(ClubDocument clubDocument) {
        ClubDocumentResource clubDocumentResource = createResourceWithId(clubDocument.getId(), clubDocument);
        clubDocumentResource.setFullName(clubDocument.getFullName());
        clubDocumentResource.setName(clubDocument.getName());
        return clubDocumentResource;
    }
}
