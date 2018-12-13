package com.sp.player.query.player.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    
}
