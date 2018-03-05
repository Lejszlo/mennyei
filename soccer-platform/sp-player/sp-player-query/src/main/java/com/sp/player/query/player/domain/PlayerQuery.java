package com.sp.player.query.player.domain;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import com.sp.organizer.api.value.competition.CompetitionId;

@Document
@Builder
@Data
public class PlayerQuery {

    @Id
    private String id;

    private String name;

    private Integer number;
    
    private Integer age;

    private String birthday;
    
    private String nationality;

    private String clubId;
    
    @Singular
    private Map<CompetitionId, PlayerMatchStatisticData> playerMatchStatisticDatas;
}
