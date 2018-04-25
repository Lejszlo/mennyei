package com.sp.organizer.query.viewer.club.service;

import com.sp.organizer.api.resource.ClubDocumentResource;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.viewer.club.resource.ClubDocumentResourceAssemblerSupport;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClubDocumentService {
    private final ClubQueryMongoRepository clubQueryMongoRepository;
    private final ClubDocumentResourceAssemblerSupport clubDocumentResourceAssemblerSupport;

    @Autowired
    public ClubDocumentService(ClubQueryMongoRepository clubQueryMongoRepository, ClubDocumentResourceAssemblerSupport clubDocumentResourceAssemblerSupport) {
        this.clubQueryMongoRepository = clubQueryMongoRepository;
        this.clubDocumentResourceAssemblerSupport = clubDocumentResourceAssemblerSupport;
    }

    public List<ClubDocumentResource> findClubs(List<String> clubIds) {
        String firstId = clubIds.remove(0);
        clubIds.add(firstId.substring(1));
        String lastId = clubIds.remove(clubIds.size() - 2);
        clubIds.add(lastId.substring(0, lastId.length()-1));
        ArrayList<ClubDocument> clubDocuments = Lists.newArrayList(clubQueryMongoRepository.findAll(clubIds));
        return clubDocumentResourceAssemblerSupport.toResources(clubDocuments);
    }

}
