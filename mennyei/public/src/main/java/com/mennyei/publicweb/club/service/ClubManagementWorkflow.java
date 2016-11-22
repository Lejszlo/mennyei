package com.mennyei.publicweb.club.service;

import com.mennyei.core.club.events.ClubAdded;
import com.mennyei.core.transfer.domain.Transfer;
import com.mennyei.core.transfer.events.PlayerTransferred;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.club.infrastructure.PlayerQueryMongoRepository;
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
    private ClubQueryMongoRepository clubMongoRepository;

    @Autowired
    private PlayerQueryMongoRepository playerQueryMongoRepository;

    @EventHandlerMethod
    public void create(DispatchedEvent<ClubAdded> dispatchedEvent) {
        ClubAdded event = dispatchedEvent.getEvent();
        String clubId = dispatchedEvent.getEntityId();
        ClubQuery competitionListQuery = ClubQuery.builder()
                .id(clubId)
                .shortName(event.getClubInfo().getShortName())
                .fullName(event.getClubInfo().getFullName())
                .build();
        clubMongoRepository.save(competitionListQuery);
    }

    @EventHandlerMethod
    public void playerTransferred(DispatchedEvent<PlayerTransferred> dispatchedEvent) {
        PlayerTransferred event = dispatchedEvent.getEvent();
        Transfer transfer = event.getTransfer();
        ClubQuery targetClubQuery = clubMongoRepository.findOne(transfer.getTargetTeamId());
        targetClubQuery.getPlayers().add(playerQueryMongoRepository.findOne(transfer.getPlayerId()));
        ClubQuery sourceClubQuery = clubMongoRepository.findOne(transfer.getSourceTeamId());
        sourceClubQuery.getPlayers().add(playerQueryMongoRepository.findOne(transfer.getPlayerId()));
        clubMongoRepository.save(targetClubQuery);
        clubMongoRepository.save(sourceClubQuery);
    }
}
