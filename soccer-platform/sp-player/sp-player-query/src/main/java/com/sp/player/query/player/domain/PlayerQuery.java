package com.sp.player.query.player.domain;

import com.sp.organizer.api.value.competition.CompetitionId;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

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
