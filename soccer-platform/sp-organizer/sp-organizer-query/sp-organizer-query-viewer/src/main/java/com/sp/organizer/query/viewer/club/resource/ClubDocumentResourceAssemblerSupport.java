package com.sp.organizer.query.viewer.club.resource;

import com.sp.club.api.resource.ClubDocumentResource;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.viewer.club.controller.ClubDocumentDocumentController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ClubDocumentResourceAssemblerSupport extends ResourceAssemblerSupport<ClubDocument, ClubDocumentResource> {

    public ClubDocumentResourceAssemblerSupport() {
        super(ClubDocumentDocumentController.class, ClubDocumentResource.class);
    }

    @Override
    public ClubDocumentResource toResource(ClubDocument clubDocument) {
        ClubDocumentResource clubDocumentResource = createResourceWithId(clubDocument.getId(), clubDocument);
        clubDocumentResource.setClubId(clubDocument.getId());
        clubDocumentResource.setFullName(clubDocument.getFullName());
        clubDocumentResource.setName(clubDocument.getName());
        return clubDocumentResource;
    }
}
