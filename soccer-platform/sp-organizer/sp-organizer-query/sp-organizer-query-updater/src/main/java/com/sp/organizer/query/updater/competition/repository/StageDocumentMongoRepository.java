package com.sp.organizer.query.updater.competition.repository;

import com.sp.organizer.query.updater.competition.entity.StageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StageDocumentMongoRepository extends MongoRepository<StageDocument, String>  {

    @Query("{ $and: [ {'competitionDocumentId' : ?0}, {'id' : ?1} ]}")
    StageDocument findByCompetitionIdAndStageIndex(@Param("competitionDocumentId") String competitionId, @Param("id") String stageId);

}
