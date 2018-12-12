package com.sp.competition.query.updater.repository;

import com.sp.competition.query.updater.entity.StageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StageDocumentMongoRepository extends MongoRepository<StageDocument, String>  {

    @Query("{ $and: [ {'competitionDocumentId' : ?0}, {'id' : ?1} ]}")
    StageDocument findByCompetitionIdAndStageIndex(@Param("competitionDocumentId") String competitionId, @Param("id") String stageId);

}
