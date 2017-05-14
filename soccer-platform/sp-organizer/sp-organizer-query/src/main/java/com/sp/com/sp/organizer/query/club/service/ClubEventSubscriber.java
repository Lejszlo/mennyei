package com.sp.organizer.backend.club.service;

import com.sp.core.backend.event.organizer.club.ClubAdded;
import com.sp.core.backend.value.club.ClubInfo;
import com.sp.organizer.backend.club.ClubUrlNameUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.club.infrastructure.ClubQueryMongoRepository;

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
}
