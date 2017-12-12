package com.sp.organizer.query.viewer.club.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.club.entity.ClubQuery;
import com.sp.organizer.query.viewer.club.controller.ClubQueryController;

@Component
public class ClubQueryResourceAssemblerSupport extends ResourceAssemblerSupport<ClubQuery, ClubQueryResource> {

    public ClubQueryResourceAssemblerSupport() {
        super(ClubQueryController.class, ClubQueryResource.class);
    }

    @Override
    public ClubQueryResource toResource(ClubQuery clubQuery) {
        ClubQueryResource clubQueryResource = createResourceWithId(clubQuery.getId(), clubQuery);
        return clubQueryResource;
    }
}
