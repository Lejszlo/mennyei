package com.mennyei.publicweb.club.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.club.events.ClubAdded;
import com.mennyei.core.transfer.domain.Transfer;
import com.mennyei.core.transfer.events.PlayerTransferred;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.club.infrastructure.PlayerQueryMongoRepository;
import com.mennyei.publicweb.util.ClubUrlNameUtil;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

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

	@Autowired
	private ModelMapper modelMapper;

	@EventHandlerMethod
	public void create(DispatchedEvent<ClubAdded> dispatchedEvent) {
		ClubAdded event = dispatchedEvent.getEvent();
		String clubId = dispatchedEvent.getEntityId();
		ClubInfo clubInfo = event.getClubInfo();
		ClubQuery clubQuery = ClubQuery.builder().id(clubId)
				.urlName(ClubUrlNameUtil.convertClubNameToUniqUrlFrendly(clubInfo.getFullName())).build();
		modelMapper.map(clubInfo, clubQuery);
		clubMongoRepository.save(clubQuery);
	}

	@EventHandlerMethod
	public void playerTransferred(DispatchedEvent<PlayerTransferred> dispatchedEvent) {
		PlayerTransferred event = dispatchedEvent.getEvent();
		Transfer transfer = event.getTransfer();
		ClubQuery targetClubQuery = clubMongoRepository.findOne(transfer.getTargetTeamId());
		PlayerQuery playerQuery = playerQueryMongoRepository.findOne(transfer.getPlayerId());
		targetClubQuery.getPlayers().add(playerQuery);
		// ClubQuery sourceClubQuery =
		// clubMongoRepository.findOne(transfer.getSourceTeamId());
		// sourceClubQuery.getPlayers().remove(playerQueryMongoRepository.findOne(transfer.getPlayerId()));
		clubMongoRepository.save(targetClubQuery);
		// clubMongoRepository.save(sourceClubQuery);
	}
}
