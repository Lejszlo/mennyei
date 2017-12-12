package com.sp.organizer.query.updater.club.handler;

import com.sp.core.backend.ClubUrlNameUtil;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;
import event.club.ClubAdded;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import com.sp.organizer.query.updater.club.entity.ClubQuery;
import value.club.ClubInfo;

@EventSubscriber
@Component
public class ClubEventHandler {

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
