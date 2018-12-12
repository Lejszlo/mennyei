package com.sp.organizer.query.updater.club.handler;

import com.sp.organizer.api.event.club.ClubCreated;
import com.sp.organizer.api.value.club.ClubInfo;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EventSubscriber
@Component
public class ClubEventHandler {

	@Autowired
	private ClubQueryMongoRepository clubMongoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@EventHandlerMethod
	public void create(DispatchedEvent<ClubCreated> dispatchedEvent) {
		ClubCreated event = dispatchedEvent.getEvent();
		String clubId = dispatchedEvent.getEntityId();
		ClubInfo clubInfo = event.getClubInfo();
		ClubDocument clubDocument = ClubDocument.builder().id(clubId).build();
		modelMapper.map(clubInfo, clubDocument);
		clubMongoRepository.save(clubDocument);
	}
}
