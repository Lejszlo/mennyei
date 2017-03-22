package com.mennyei.publicweb.competition.infrastructure;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;

@RepositoryRestResource(path="competitions")
public interface CompetitionMongoRepository extends MongoRepository<CompetitionQuery, String> {

	Collection<CompetitionQuery> findByClubsIn(ClubQuery clubQuery);
	
}
