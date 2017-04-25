package com.mennyei.publicweb.club.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.club.events.ClubAdded;
import com.mennyei.core.club.events.PlayerAddedToClub;
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
public class ClubEventSubscriber {

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
		ClubQuery clubQuery = ClubQuery.builder().id(clubId).urlName(ClubUrlNameUtil.convertClubNameToUniqUrlFrendly(clubInfo.getFullName())).build();
		modelMapper.map(clubInfo, clubQuery);
		clubMongoRepository.save(clubQuery);
	}

	@EventHandlerMethod
	public void playerTransferred(DispatchedEvent<PlayerAddedToClub> dispatchedEvent) {
		PlayerAddedToClub event = dispatchedEvent.getEvent();
		ClubQuery targetClubQuery = clubMongoRepository.findOne(event.getClubId());
		PlayerQuery playerQuery = playerQueryMongoRepository.findOne(event.getPlayerId());
		targetClubQuery.getPlayers().add(playerQuery);
		clubMongoRepository.save(targetClubQuery);
	}
}
