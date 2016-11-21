package com.mennyei.publicweb.club.service;

import com.mennyei.core.team.events.ClubAdded;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubMongoRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lejsz on 2016. 11. 21..
 */

@EventSubscriber
@Component
public class ClubManagementWorkflow {

    @Autowired
    private ClubMongoRepository clubMongoRepository;

    @EventHandlerMethod
    public void create(DispatchedEvent<ClubAdded> dispatchedEvent) {
        ClubAdded event = dispatchedEvent.getEvent();
        String clubId = dispatchedEvent.getEntityId();
        ClubQuery competitionListQuery = ClubQuery.builder()
                .id(clubId)
                .build();
        clubMongoRepository.save(competitionListQuery);
    }
}
